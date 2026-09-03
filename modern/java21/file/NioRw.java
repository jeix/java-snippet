package modern.java21.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class NioRw {

	private static final int SIZE_PER_RW = 256;

	public static byte[] nio_read(String file_name) throws IOException {
		try (var channel = FileChannel.open(Path.of(file_name), StandardOpenOption.READ)) {
			var result = new byte[checked_array_size(channel.size())];
			var buffer = ByteBuffer.allocateDirect(SIZE_PER_RW);
			var offset = 0;

			while (offset < result.length) {
				buffer.clear();
				var length = channel.read(buffer, offset);
				if (length < 0) break;
				if (length == 0) continue;

				buffer.flip();
				buffer.get(result, offset, length);
				offset += length;
			}
			return result;
		}
	}

	public static String nio_read_text(String file_name) throws IOException {
		return nio_read_text(file_name, StandardCharsets.UTF_8);
	}

	public static String nio_read_text(String file_name, String charset) throws IOException {
		return nio_read_text(file_name, Charset.forName(charset));
	}

	private static String nio_read_text(String file_name, Charset charset) throws IOException {
		return new String(nio_read(file_name), charset);
	}

	public static void nio_write(String file_name, byte[] buf) throws IOException {
		try (var channel = FileChannel.open(
				Path.of(file_name), StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)) {
			var buffer = ByteBuffer.allocateDirect(SIZE_PER_RW);
			var offset = 0;

			while (offset < buf.length) {
				var size_to_write = Math.min(buf.length - offset, SIZE_PER_RW);
				buffer.clear();
				buffer.put(buf, offset, size_to_write);
				buffer.flip();
				while (buffer.hasRemaining()) channel.write(buffer);
				offset += size_to_write;
			}
		}
	}

	public static void nio_write(String file_name, String s) throws IOException {
		nio_write(file_name, s, StandardCharsets.UTF_8);
	}

	public static void nio_write(String file_name, String s, String charset) throws IOException {
		nio_write(file_name, s, Charset.forName(charset));
	}

	private static void nio_write(String file_name, String s, Charset charset) throws IOException {
		nio_write(file_name, s.getBytes(charset));
	}

	public static byte[] nio_mapped_read(String file_name) throws IOException {
		try (var channel = FileChannel.open(Path.of(file_name), StandardOpenOption.READ)) {
			var size = checked_array_size(channel.size());
			if (size == 0) return new byte[0];

			MappedByteBuffer mem_map = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
			var result = new byte[size];
			mem_map.get(result);
			return result;
		}
	}

	public static String nio_mapped_read_text(String file_name) throws IOException {
		return nio_mapped_read_text(file_name, StandardCharsets.UTF_8);
	}

	public static String nio_mapped_read_text(String file_name, String charset) throws IOException {
		return nio_mapped_read_text(file_name, Charset.forName(charset));
	}

	private static String nio_mapped_read_text(String file_name, Charset charset) throws IOException {
		return new String(nio_mapped_read(file_name), charset);
	}

	public static void nio_mapped_write(String file_name, byte[] buf) throws IOException {
		try (var channel = FileChannel.open(
				Path.of(file_name), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING,
				StandardOpenOption.READ, StandardOpenOption.WRITE)) {
			if (buf.length == 0) return;

			MappedByteBuffer mem_map = channel.map(FileChannel.MapMode.READ_WRITE, 0, buf.length);
			mem_map.put(buf);
		}
	}

	public static void nio_mapped_write(String file_name, String s) throws IOException {
		nio_mapped_write(file_name, s, StandardCharsets.UTF_8);
	}

	public static void nio_mapped_write(String file_name, String s, String charset) throws IOException {
		nio_mapped_write(file_name, s, Charset.forName(charset));
	}

	private static void nio_mapped_write(String file_name, String s, Charset charset) throws IOException {
		nio_mapped_write(file_name, s.getBytes(charset));
	}

	private static int checked_array_size(long size) throws IOException {
		if (size > Integer.MAX_VALUE) {
			throw new IOException("File is too large for this example: " + size + " bytes");
		}
		return Math.toIntExact(size);
	}

	public static void main(String[] args) throws Exception {
		if (3 == args.length) {
			if ("b".equals(args[0])) {
				NioRw.nio_write(args[2], NioRw.nio_read(args[1]));
			} else if ("bm".equals(args[0])) {
				NioRw.nio_mapped_write(args[2], NioRw.nio_mapped_read(args[1]));
			} else if ("t".equals(args[0])) {
				NioRw.nio_write(args[2], NioRw.nio_read_text(args[1]));
			} else if ("tm".equals(args[0])) {
				NioRw.nio_mapped_write(args[2], NioRw.nio_mapped_read_text(args[1]));
			} else if ("tu".equals(args[0])) {
				NioRw.nio_write(args[2], NioRw.nio_read_text(args[1], "UTF-8"), "UTF-8");
			} else if ("tum".equals(args[0])) {
				NioRw.nio_mapped_write(args[2], NioRw.nio_mapped_read_text(args[1], "UTF-8"), "UTF-8");
			}
		} else {
			System.out.println("java NioRw mode infile outfile");
			System.out.println("  - mode");
			System.out.println("    b   binary");
			System.out.println("    bm  binary, mapped");
			System.out.println("    t   text");
			System.out.println("    tm  text, mapped");
			System.out.println("    tu  text, utf-8");
			System.out.println("    tum text, utf-8, mapped");
		}
	}
}

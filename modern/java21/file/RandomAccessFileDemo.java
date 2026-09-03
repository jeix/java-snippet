package modern.java21.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class RandomAccessFileDemo {

	private void test_java_nio_FileChannel_seek() {
		Path path = Path.of("raf_test.txt");
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (FileChannel channel = FileChannel.open(path,
				StandardOpenOption.CREATE, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
			write(channel, "123\n456\n789");
			print_file_pointer_offset(channel); // -> 11
			channel.position(0L);
			print_file_pointer_offset(channel); // -> 0
			write(channel, "276");
			print_file_pointer_offset(channel); // -> 3
			channel.position(channel.size() - 3);
			print_file_pointer_offset(channel); // -> 8
			write(channel, "438");
			print_file_pointer_offset(channel); // -> 11
			channel.position(4L);
			print_file_pointer_offset(channel); // -> 4
			write(channel, "951");
			print_file_pointer_offset(channel); // -> 7
			channel.position(0L);
			print_file_pointer_offset(channel); // -> 0
			ByteBuffer buf = ByteBuffer.allocate((int) channel.size());
			channel.read(buf);
			print_file_pointer_offset(channel); // -> 11
			System.out.write(buf.array(), 0, buf.position()); // -> 276 951 438
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void write(FileChannel channel, String s) throws IOException {
		channel.write(ByteBuffer.wrap(s.getBytes()));
	}

	private void print_file_pointer_offset(FileChannel channel) throws IOException {
		System.out.println("fp @ " + channel.position());
	}

	private void test_nothing() {
		System.out.println(":wq");
	}

	public void test() {
		test_java_nio_FileChannel_seek();
		test_nothing();
	}

	public static void main(String[] args) {
		RandomAccessFileDemo worker = new RandomAccessFileDemo();
		worker.test();
	}
}

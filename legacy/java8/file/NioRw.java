//package org.simple.util;
package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioRw {
	
	private static final int SIZE_PER_RW = 256;
	
	public static byte[] nio_read(String file_name) throws IOException {
		File f = new File(file_name);
		FileInputStream fis = new FileInputStream(f); // FileNotFoundException
		FileChannel channel = fis.getChannel();
		long size = channel.size(); // IOException
		byte[] buf = new byte[(int) size];
		ByteBuffer buffer = ByteBuffer.allocateDirect(SIZE_PER_RW);
		int offset = 0;
		while (offset < size) {
			buffer.clear();
			int length = channel.read(buffer, offset); // IOException
			buffer.flip();
			buffer.get(buf, offset, length);
			offset += length;
		}
		channel.close(); // IOException
		return buf;
	}
	public static String nio_read_text(String file_name) throws IOException {
		return new String(nio_read(file_name));
	}
	public static String nio_read_text(String file_name, String charset) throws IOException {
		return new String(nio_read(file_name), charset);
	}
	
	public static void nio_write(String file_name, byte[] buf) throws IOException {
		File f = new File(file_name);
		FileOutputStream fos = new FileOutputStream(f); // FileNotFoundException
		FileChannel channel = fos.getChannel();
		ByteBuffer buffer = ByteBuffer.allocateDirect(SIZE_PER_RW);
		int size = buf.length;
		int accum_write_size = 0;
		while (accum_write_size < size) {
			//System.out.println(buf.length);
			int size_to_write = size - accum_write_size;
			if (size_to_write > SIZE_PER_RW) size_to_write = SIZE_PER_RW;
			buffer.clear();
			buffer.put(buf, accum_write_size, size_to_write);
			buffer.flip();
			channel.write(buffer); // IOException
			accum_write_size += SIZE_PER_RW;
		}
		channel.close(); // IOException
	}
	public static void nio_write(String file_name, String s) throws IOException {
		nio_write(file_name, s.getBytes());
	}
	public static void nio_write(String file_name, String s, String charset) throws IOException {
		nio_write(file_name, s.getBytes(charset));
	}
	
	public static byte[] nio_mapped_read(String file_name) throws IOException {
		File f = new File(file_name);
		FileInputStream fis = new FileInputStream(f); // FileNotFoundException
		FileChannel channel = fis.getChannel();
		long size = channel.size(); // IOException
		MappedByteBuffer mem_map = channel.map(FileChannel.MapMode.READ_ONLY, 0, size); // IOException
		byte[] buf = new byte[(int) size];
		mem_map.get(buf);
		channel.close(); // IOException
		return buf;
	}
	public static String nio_mapped_read_text(String file_name) throws IOException {
		return new String(nio_mapped_read(file_name));
	}
	public static String nio_mapped_read_text(String file_name, String charset) throws IOException {
		return new String(nio_mapped_read(file_name), charset);
	}
	
	public static void nio_mapped_write(String file_name, byte[] buf) throws IOException {
		int size = buf.length;
		
		File f = new File(file_name);
		RandomAccessFile raf = new RandomAccessFile(f, "rw"); // FileNotFoundException
		FileChannel channel = raf.getChannel();
		MappedByteBuffer mem_map = channel.map(FileChannel.MapMode.READ_WRITE , 0, size); // IOException
		mem_map.put(buf);
		channel.close(); // IOException
	}
	public static void nio_mapped_write(String file_name, String s) throws IOException {
		nio_mapped_write(file_name, s.getBytes());
	}
	public static void nio_mapped_write(String file_name, String s, String charset) throws IOException {
		nio_mapped_write(file_name, s.getBytes(charset));
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

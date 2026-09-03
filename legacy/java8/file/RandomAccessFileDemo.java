package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
	
	private void test_java_io_RandomAccessFile() {
		File f = new File("raf_test.txt");
		if (f.exists()) f.delete();
		try {
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			raf.write("123\n456\n789".getBytes());
			print_file_pointer_offset(raf); // -> 11
			raf.seek(0L);
			print_file_pointer_offset(raf); // -> 0
			raf.write("276".getBytes());
			print_file_pointer_offset(raf); // -> 3
			raf.seek(raf.length()-3);
			print_file_pointer_offset(raf); // -> 8
			raf.write("438".getBytes());
			print_file_pointer_offset(raf); // -> 11
			raf.seek(4L);
			print_file_pointer_offset(raf); // -> 4
			raf.write("951".getBytes());
			print_file_pointer_offset(raf); // -> 7
			raf.seek(0L);
			print_file_pointer_offset(raf); // -> 0
			byte[] b = new byte[(int)raf.length()];
			int n_read = raf.read(b);
			print_file_pointer_offset(raf); // -> 11
			System.out.write(b, 0, b.length); // -> 276 951 438
			System.out.println();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		f.delete();
	}
	
	private void print_file_pointer_offset(RandomAccessFile raf) throws IOException {
		System.out.println("fp @ " + String.valueOf(raf.getFilePointer()));
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_java_io_RandomAccessFile();
		test_nothing();
	}
	
	public static void main(String[] args) {
		RandomAccessFileDemo worker = new RandomAccessFileDemo();
		worker.test();
	}
}

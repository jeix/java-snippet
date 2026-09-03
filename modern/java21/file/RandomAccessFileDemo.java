package modern.java21.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class RandomAccessFileDemo {

	private void test_java_io_RandomAccessFile() throws IOException {
		var path = Files.createTempFile("random-access-file-demo-", ".txt");
		try {
			try (var raf = new RandomAccessFile(path.toFile(), "rw")) {
				raf.write("123\n456\n789".getBytes(StandardCharsets.UTF_8));
				print_file_pointer_offset(raf); // -> 11
				raf.seek(0L);
				print_file_pointer_offset(raf); // -> 0
				raf.write("276".getBytes(StandardCharsets.UTF_8));
				print_file_pointer_offset(raf); // -> 3
				raf.seek(raf.length() - 3);
				print_file_pointer_offset(raf); // -> 8
				raf.write("438".getBytes(StandardCharsets.UTF_8));
				print_file_pointer_offset(raf); // -> 11
				raf.seek(4L);
				print_file_pointer_offset(raf); // -> 4
				raf.write("951".getBytes(StandardCharsets.UTF_8));
				print_file_pointer_offset(raf); // -> 7
				raf.seek(0L);
				print_file_pointer_offset(raf); // -> 0
				var content = new byte[Math.toIntExact(raf.length())];
				raf.readFully(content);
				print_file_pointer_offset(raf); // -> 11
				System.out.println(new String(content, StandardCharsets.UTF_8)); // -> 276 951 438
			}
		} finally {
			Files.deleteIfExists(path);
		}
	}

	private void print_file_pointer_offset(RandomAccessFile raf) throws IOException {
		System.out.println("fp @ " + raf.getFilePointer());
	}

	private void test_nothing() {
		System.out.println(":wq");
	}

	public void test() throws IOException {
		test_java_io_RandomAccessFile();
		test_nothing();
	}

	public static void main(String[] args) throws IOException {
		new RandomAccessFileDemo().test();
	}
}

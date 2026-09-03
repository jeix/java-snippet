package modern.java21.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ResourceAsStreamDemo {

	private void test_Class_getResourceAsStream() {
		Class<?> cls = getClass();
		System.out.println(1);
		print(cls.getResourceAsStream("/modern/java21/file/cfg/foo.txt")); // absolute
		System.out.println(2);
		print(cls.getResourceAsStream("modern/java21/file/cfg/foo.txt")); // package-relative: not found
		System.out.println(3);
		print(cls.getResourceAsStream("cfg/foo.txt")); // package-relative
	}

	private void test_ClassLoader_getResourceAsStream() {
		var class_loader = getClass().getClassLoader();
		System.out.println(4);
		print(class_loader.getResourceAsStream("/modern/java21/file/cfg/foo.txt")); // leading '/': not found
		System.out.println(5);
		print(class_loader.getResourceAsStream("modern/java21/file/cfg/foo.txt")); // classpath-root-relative
		System.out.println(6);
		print(class_loader.getResourceAsStream("cfg/foo.txt")); // classpath-root-relative: not found
	}

	private void print(InputStream input) {
		if (input == null) {
			new IllegalArgumentException("InputStream is null").printStackTrace();
			return;
		}

		try (var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
			String line;
			while ((line = reader.readLine()) != null) System.out.println(line);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private void test_nothing() {
		System.out.println(":wq");
	}

	public void test() {
		test_Class_getResourceAsStream();
		test_ClassLoader_getResourceAsStream();
		test_nothing();
	}

	public static void main(String[] args) {
		new ResourceAsStreamDemo().test();
	}
}

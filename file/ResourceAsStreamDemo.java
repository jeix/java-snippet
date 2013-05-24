package file;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class ResourceAsStreamDemo {
	
	private void test_Class_getResourceAsStream() {
		Class cls = getClass();
		System.out.println(1);
		print(cls.getResourceAsStream("/file/cfg/foo.txt")); // absoulute
		System.out.println(2);
		print(cls.getResourceAsStream("file/cfg/foo.txt")); // not start with '/' -- not works
		System.out.println(3);
		print(cls.getResourceAsStream("cfg/foo.txt")); // relative to package
	}
	
	private void test_ClassLoader_getResourceAsStream() {
		ClassLoader cl = getClass().getClassLoader();
		System.out.println(4);
		print(cl.getResourceAsStream("/file/cfg/foo.txt")); // absoulute -- not works
		System.out.println(5);
		print(cl.getResourceAsStream("file/cfg/foo.txt")); // not start with '/'
		System.out.println(6);
		print(cl.getResourceAsStream("cfg/foo.txt")); // relative to package -- not works
	}
	
	private void print(InputStream is) {
		try {
			if (null == is) throw new IllegalArgumentException("InputStream is null");
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return;
		}
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		try {
			while (null != (line = br.readLine())) {
				System.out.println(line);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try { br.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
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
		ResourceAsStreamDemo worker = new ResourceAsStreamDemo();
		worker.test();
	}
}

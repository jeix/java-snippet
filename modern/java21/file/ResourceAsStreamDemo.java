package modern.java21.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResourceAsStreamDemo {

    private void test_Class_getResourceAsStream() {
        Class cls = getClass();
        System.out.println(1);
        print(cls.getResourceAsStream("/file/cfg/foo.txt")); // absolute
        System.out.println(2);
        print(cls.getResourceAsStream("file/cfg/foo.txt")); // not start with '/' -- not works
        System.out.println(3);
        print(cls.getResourceAsStream("cfg/foo.txt")); // relative to package
    }

    private void test_ClassLoader_getResourceAsStream() {
        ClassLoader cl = getClass().getClassLoader();
        System.out.println(4);
        print(cl.getResourceAsStream("/file/cfg/foo.txt")); // absolute -- not works
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
        try (InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
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
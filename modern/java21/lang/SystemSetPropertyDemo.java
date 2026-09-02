package modern.java21.lang;

public class SystemSetPropertyDemo {
    
    private void test_System_setProperty() {
        String line_separator = System.getProperty("line.separator");
        System.setProperty("line.separator", "\n");
        System.out.print("foo");
        System.out.println(); // print "\r\n" (or whatever the original was)
        System.out.print("bar");
        System.out.print(System.getProperty("line.separator")); // print "\n"
        System.setProperty("line.separator", line_separator);
    }
    
    // Modern: Using System.lineSeparator() (Java 1.5+)
    private void test_line_separator() {
        System.out.println("// System.lineSeparator() (Java 1.5+)");
        System.out.println("Default line separator: [" + System.lineSeparator() + "]");
        System.out.println("Property line.separator: [" + System.getProperty("line.separator") + "]");
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_System_setProperty();
        test_line_separator();
        test_nothing();
    }
    
    public static void main(String[] args) {
        SystemSetPropertyDemo demo = new SystemSetPropertyDemo();
        demo.test();
    }
}
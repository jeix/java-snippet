package modern.java21.lang;

import java.util.ArrayList;
import java.util.List;

public class NullType {
    
    class Something {}
    
    private void test_null_type() {
        Something something = null;
        // Modern: pattern matching for instanceof (Java 16+)
        if (something instanceof Something s) {
            System.out.print("calling with Something...");
            check_instance(s);
        } else if (something instanceof Object o) {
            System.out.print("calling with Object...");
            check_instance(something);
        } else {
            System.out.print("calling...");
            check_instance(something);
        }
        
        String s = null;
        List<Object> list = new ArrayList<>();
        list.add(s);
        System.out.print("calling with casted Something...");
        check_instance((Something) list.get(0));
        System.out.print("calling with casted Something again...");
        check_instance((Something) (Object) s);
        
        System.out.print("calling with null...");
        check_instance(null);
    }
    
    private void check_instance(Something something) {
        // Modern: pattern matching for instanceof (Java 16+)
        if (something instanceof Something s) {
            System.out.println("called with Something");
        } else if (something instanceof Object o) {
            System.out.println("called with Object");
        } else {
            System.out.println("called");
        }
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_null_type();
        test_nothing();
    }
    
    public static void main(String[] args) {
        NullType worker = new NullType();
        worker.test();
    }
}
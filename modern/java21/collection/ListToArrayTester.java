package modern.java21.collection;

import java.util.ArrayList;
import java.util.List;

public class ListToArrayTester {
    
    private void test_List_toArray() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(String.valueOf(i + 1));
        }
        
        // Modern: toArray(IntFunction) - Java 11+
        String[] a = list.toArray(String[]::new);
        System.out.println(a.length);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
        
        // Also demonstrate empty array approach (pre-Java 11)
        String[] b = list.toArray(new String[0]);
        System.out.println(b.length);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i]);
        }
        System.out.println();
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_List_toArray();
        test_nothing();
    }
    
    public static void main(String[] args) {
        ListToArrayTester worker = new ListToArrayTester();
        worker.test();
    }
}
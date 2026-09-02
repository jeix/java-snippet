package modern.java21.collection;

import java.util.List;

public class ArrayInitializeTester {
    
    private void test_declaration_only_with_unknown_size() {
        List<String> list = List.of(); // empty immutable list
        print_list_size(list);
        print_list(list);
        // List.of is immutable, cannot add - demonstrates the point
        System.out.println("List.of is immutable, use ArrayList for mutable:");
        java.util.ArrayList<String> a = new java.util.ArrayList<>(list);
        while (a.size() < 3) {
            a.add(fetch_data(a.size()));
        }
        print_list_size(a);
        print_list(a);
    }
    
    private void print_list_size(List<String> a) {
        if (a != null) {
            System.out.println("size=" + a.size());
        }
    }
    
    private void print_list(List<String> a) {
        if (a != null) {
            System.out.print("[");
            for (int i = 0; i < a.size(); i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(a.get(i));
            }
            System.out.println("]");
        }
    }
    
    private String fetch_data(int ix) {
        return String.valueOf(ix);
    }
    
    private void test_declaration_only_with_known_size() {
        // For known size with nulls, still need array or ArrayList
        String[] a = new String[3];
        print_array_size(a);
        print_array(a);
        for (int i = 0; i < a.length; i++) {
            a[i] = fetch_data(i);
        }
        print_array(a);
    }
    
    private void test_declaration_and_initialization() {
        // Modern: List.of for immutable, or array literal
        List<String> list = List.of("1", "2", "3");
        print_list_size(list);
        print_list(list);
        
        // Array literal still works
        String[] a = {"1", "2", "3"};
        print_array_size(a);
        print_array(a);
    }
    
    private void print_array_size(String[] a) {
        if (a != null) {
            System.out.println("size=" + a.length);
        }
    }
    
    private void print_array(String[] a) {
        if (a != null) {
            System.out.print("[");
            for (int i = 0; i < a.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(a[i]);
            }
            System.out.println("]");
        }
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_declaration_only_with_unknown_size();
        test_declaration_only_with_known_size();
        test_declaration_and_initialization();
        test_nothing();
    }
    
    public static void main(String[] args) {
        ArrayInitializeTester worker = new ArrayInitializeTester();
        worker.test();
    }
}
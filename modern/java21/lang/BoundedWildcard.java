package modern.java21.lang;

import java.util.ArrayList;
import java.util.List;

public class BoundedWildcard {
    
    // Modern: Using record (Java 16+) for simple data classes
    // Note: Records cannot extend other classes - they're implicitly final
    record Foo(String name) {
        public void do_something() {
            System.out.println("Foo::" + this);
        }
    }
    
    record Bar(String name) {
        public void do_something() {
            System.out.println("Bar::" + this);
        }
    }
    
    record Baz(String name) {
        public void do_something() {
            System.out.println("Baz::" + this);
        }
    }
    
    // Regular classes for inheritance demo (bounded wildcard requires inheritance)
    static class Foo2 {
        public void do_something() {
            System.out.println("Foo2::" + this);
        }
    }
    
    static class Bar2 extends Foo2 {
        @Override
        public void do_something() {
            System.out.println("Bar2::" + this);
        }
    }
    
    static class Baz2 extends Foo2 {
        @Override
        public void do_something() {
            System.out.println("Baz2::" + this);
        }
    }
    
    private void test_bounded_wildcard() {
        List<Foo2> fooz = new ArrayList<>();
        fooz.add(new Foo2());
        fooz.add(new Bar2());
        fooz.add(new Baz2());
        bounded_wildcard(fooz);
        
        List<Bar2> barz = new ArrayList<>();
        barz.add(new Bar2());
        barz.add(new Bar2());
        bounded_wildcard(barz);
        
        List<Baz2> bazz = new ArrayList<>();
        bazz.add(new Baz2());
        bazz.add(new Baz2());
        bounded_wildcard(bazz);
    }
    
    // Modern: Using varargs and streams (Java 8+)
    private void bounded_wildcard(List<? extends Foo2> list) {
        list.forEach(Foo2::do_something);
    }
    
    // Modern: Demonstrate PECS (Producer Extends, Consumer Super)
    private void test_pecs() {
        System.out.println("// PECS example");
        List<Foo2> fooz = new ArrayList<>();
        fooz.add(new Foo2());
        
        // Producer: use extends (read only)
        consume(fooz);
        
        // Consumer: use super (write only)
        List<Object> objects = new ArrayList<>();
        produce(objects);
    }
    
    private void consume(List<? extends Foo2> list) {
        for (Foo2 item : list) {
            item.do_something();
        }
    }
    
    private void produce(List<? super Foo2> list) {
        list.add(new Foo2());
        list.add(new Bar2());
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_bounded_wildcard();
        test_pecs();
        test_nothing();
    }
    
    public static void main(String[] args) {
        BoundedWildcard worker = new BoundedWildcard();
        worker.test();
    }
}
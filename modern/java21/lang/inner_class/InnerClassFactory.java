package modern.java21.lang.inner_class;

public class InnerClassFactory {
    
    private InnerClassFactory() {}
    
    // Non-static inner class - requires outer instance
    public class Foo {
        private Foo() {}
        
        public void foo(String s) {
            System.out.println(s);
        }
    }
    
    private Foo create() {
        return new Foo();
    }
    
    public static Foo create_Foo() {
        // Need outer instance for non-static inner class
        return new InnerClassFactory().create();
    }
    
    // Static nested class - no outer instance needed
    public static class Bar {
        private Bar() {}
        
        public void bar(String s) {
            System.out.println(s);
        }
    }
    
    public static Bar create_Bar() {
        return new Bar();
    }
    
    public static class Baz {
        private Baz() {}
        
        public static void baz(String s) {
            System.out.println(s);
        }
    }
    
    public static class Qux {
        public void qux(String s) {
            System.out.println(s);
        }
    }
    
    // Modern: Record as static nested (Java 16+)
    public static record DataRecord(String value) {
        public void print() {
            System.out.println("Record: " + value);
        }
    }
}
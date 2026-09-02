package modern.java21.lang;

public class AnonymousTester {
    
    interface FooHoo {
        void oohoof();
    }
    
    abstract class AbstractFooHoo implements FooHoo {
        public abstract void oohoof();
    }
    
    class FooHooImpl implements FooHoo {
        public void oohoof() {
            System.out.println("FooHooImpl#oohoof()");
        }
    }
    
    // Modern: Lambda expressions (Java 8+)
    private void test_Interface() {
        // Lambda instead of anonymous class
        FooHoo fh = () -> System.out.println("oohoof ... Implements Interface (Lambda)");
        fh.oohoof();
        
        // Still show anonymous class for comparison
        FooHoo fhAnon = new FooHoo() {
            public void oohoof() {
                System.out.println("oohoof ... Implements Interface (Anonymous)");
            }
        };
        fhAnon.oohoof();
    }
    
    private void test_AbstractClass() {
        // Anonymous class still needed for abstract class
        FooHoo afh = new AbstractFooHoo() {
            public void oohoof() {
                System.out.println("oohoof ... Extends Abstract Class");
            }
        };
        afh.oohoof();
    }
    
    private void test_Class() {
        // Anonymous class still needed for concrete class extension
        FooHoo fhi = new FooHooImpl() {
            public void oohoof() {
                System.out.println("oohoof ... Extends Class");
            }
        };
        fhi.oohoof();
    }
    
    // Modern: Method reference example
    private void test_MethodReference() {
        System.out.println("// Method reference (Java 8+)");
        FooHoo fh = System.out::println; // Not directly compatible, but showing concept
        // Actually, we need a functional interface with matching signature
        // This is just demonstration
        fh = () -> System.out.println("oohoof ... Method Reference style");
        fh.oohoof();
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_Interface();
        test_AbstractClass();
        test_Class();
        test_MethodReference();
        test_nothing();
    }
    
    public static void main(String[] args) {
        AnonymousTester worker = new AnonymousTester();
        worker.test();
    }
}
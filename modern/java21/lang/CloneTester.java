package modern.java21.lang;

import java.util.ArrayList;
import java.util.List;

public class CloneTester {
    
    // Modern: record for immutable data (Java 16+)
    // Note: records are implicitly final and immutable by design
    // For clone demonstration, we use regular classes
    static class Bar implements Cloneable {
        
        private String val;
        
        public Bar(String val) {
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        
        public void setVal(String val) {
            this.val = val;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof Bar)) return false;
            Bar other = (Bar) o;
            if (val == null && other.val != null) return false;
            if (val != null && !val.equals(other.val)) return false;
            return true;
        }
        
        @Override
        public Bar clone() {
            try {
                return (Bar) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
        
        @Override
        public String toString() {
            return "Bar{" + val + "}";
        }
    }
    
    static class Foo implements Cloneable {
        
        private String val;
        private List<Bar> barz = new ArrayList<>();
        
        public Foo(String val) {
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        
        public void setVal(String val) {
            this.val = val;
        }
        
        public List<Bar> getBarz() {
            return barz;
        }
        
        public void addBar(Bar bar) {
            barz.add(bar);
        }
        
        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof Foo)) return false;
            Foo other = (Foo) o;
            if (val == null && other.val != null) return false;
            if (val != null && !val.equals(other.val)) return false;
            if (barz.size() != other.barz.size()) return false;
            for (int i = 0; i < barz.size(); i++) {
                Bar bar = barz.get(i);
                if (bar == null && other.barz.get(i) != null) return false;
                if (bar != null && !bar.equals(other.barz.get(i))) return false;
            }
            return true;
        }
        
        @Override
        public Foo clone() {
            try {
                Foo cloned = (Foo) super.clone();
                // Deep copy the list
                cloned.barz = new ArrayList<>();
                for (Bar bar : barz) {
                    cloned.addBar(bar.clone());
                }
                return cloned;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
        
        @Override
        public String toString() {
            return "Foo{" + val + ", barz=" + barz + "}";
        }
    }
    
    // Modern: record alternative (Java 16+) - immutable, no clone needed
    record ImmutableFoo(String val, List<ImmutableBar> barz) {
        public ImmutableFoo(String val) {
            this(val, List.of());
        }
        
        public ImmutableFoo withVal(String newVal) {
            return new ImmutableFoo(newVal, barz);
        }
        
        public ImmutableFoo withAddedBar(ImmutableBar bar) {
            List<ImmutableBar> newBarz = new ArrayList<>(barz);
            newBarz.add(bar);
            return new ImmutableFoo(val, List.copyOf(newBarz));
        }
    }
    
    record ImmutableBar(String val) {}
    
    private void test_Something() {
        System.out.println("// Traditional clone with mutable classes");
        Foo foo = new Foo("푸 1");
        Bar bar = new Bar("바 11");
        foo.addBar(bar);
        bar = new Bar("바 12");
        foo.addBar(bar);
        
        Foo foo2 = foo.clone();
        System.out.println("foo == foo2: " + (foo == foo2));
        System.out.println("foo.equals(foo2): " + foo.equals(foo2));
        foo2.setVal("푸 2");
        System.out.println("After foo2.setVal('푸 2'), foo.equals(foo2): " + foo.equals(foo2));
        Bar bar2 = foo2.getBarz().get(1);
        System.out.println("bar == bar2 (should be false for deep clone): " + (bar == bar2));
        System.out.println("bar.equals(bar2): " + bar.equals(bar2));
        bar2.setVal("바 22");
        System.out.println("After bar2.setVal('바 22'), bar.equals(bar2) (should be false for deep clone): " + bar.equals(bar2));
        foo2.setVal("푸 1");
        System.out.println("After foo2.setVal('푸 1'), foo.equals(foo2): " + foo.equals(foo2));
        
        System.out.println("\n// Modern: Immutable records (Java 16+) - no clone needed");
        ImmutableFoo iFoo = new ImmutableFoo("푸 1")
            .withAddedBar(new ImmutableBar("바 11"))
            .withAddedBar(new ImmutableBar("바 12"));
        System.out.println("Original: " + iFoo);
        
        // "Clone" by creating new instance with modified value
        ImmutableFoo iFoo2 = iFoo.withVal("푸 2");
        System.out.println("Modified: " + iFoo2);
        System.out.println("Original unchanged: " + iFoo);
        System.out.println("Are they equal? " + iFoo.equals(iFoo2));
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_Something();
        test_nothing();
    }
    
    public static void main(String[] args) {
        CloneTester worker = new CloneTester();
        worker.test();
    }
}
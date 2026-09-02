package modern.java21.lang;

public class VarArgsDemo {
    
    // Modern: varargs work the same, just show usage
    private void demo(Object... args) {
        for (Object o : args) {
            System.out.println(o.getClass().getName() + "::" + o);
        }
    }
    
    // Modern: Using varargs with arrays and individual args
    private void test_var_args(Object... args) {
        demo(new Object[] {false, 42, "The Universe", java.util.Calendar.getInstance().getTime()});
        demo(false, 42, "The Universe", java.util.Calendar.getInstance().getTime());
        
        // Modern: Show spread operator equivalent (just passing array)
        Object[] arr = {1, 2, 3};
        demo(arr); // treated as single argument (array)
        demo((Object) arr); // explicit cast to treat as single argument
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_var_args();
        test_nothing();
    }
    
    public static void main(String[] args) {
        VarArgsDemo worker = new VarArgsDemo();
        worker.test();
    }
}
package modern.java21.lang;

public class Autoboxing {
    
    public static void main(String[] args) {
        int i = 42;
        Integer boxed = i; // autoboxing
        int unboxed = boxed; // unboxing
        if (unboxed != 42) System.out.println(unboxed);
        Object o = i; // autoboxing to Object
        unboxed = (Integer) o; // casting required
        if (unboxed != 42) System.out.println(unboxed);
        
        // Modern: Show primitives vs wrappers
        System.out.println("// Primitive vs Wrapper");
        System.out.println("int.class = " + int.class);
        System.out.println("Integer.class = " + Integer.class);
        System.out.println("Integer.TYPE = " + Integer.TYPE);
        
        // Modern: var (Java 10+) - infers type
        var j = 42; // infers int
        var boxed2 = Integer.valueOf(j); // explicit boxing
        System.out.println("var j = " + j + ", boxed = " + boxed2);
        
        System.out.println(":wq");
    }
}
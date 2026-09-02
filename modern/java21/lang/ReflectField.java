package modern.java21.lang;

import java.lang.reflect.Field;

public class ReflectField {
    
    static class Foo {
        private String s;
        public String getS() { return s; }
        public void setS(String s) { this.s = s; }
        
        private int i;
        public int getI() { return i; }
        public void setI(int i) { this.i = i; }
        
        private float f;
        public float getF() { return f; }
        public void setF(float f) { this.f = f; }
    }
    
    // Modern: Using pattern matching for instanceof (Java 16+), switch expressions
    public void test_reflect() {
        Foo obj = new Foo();
        obj.setS("a");
        obj.setI(1);
        obj.setF(1.0f);
        
        for (Field f : obj.getClass().getDeclaredFields()) {
            Class<?> c = f.getType(); // Use getType() instead of getGenericType() for primitives
            System.out.println(f.getName() + " : " + c.getName());
            f.setAccessible(true);
            try {
                // Modern: pattern matching switch (Java 21+)
                if (c.isPrimitive()) {
                    switch (c.getName()) {
                        case "boolean" -> f.setBoolean(obj, !f.getBoolean(obj));
                        case "byte" -> f.setByte(obj, (byte)(0 - f.getByte(obj)));
                        case "char" -> f.setChar(obj, (char)(0 - f.getChar(obj)));
                        case "double" -> f.setDouble(obj, 0 - f.getDouble(obj));
                        case "float" -> f.setFloat(obj, 0 - f.getFloat(obj));
                        case "int" -> f.setInt(obj, 0 - f.getInt(obj));
                        case "long" -> f.setLong(obj, 0 - f.getLong(obj));
                        case "short" -> f.setShort(obj, (short)(0 - f.getShort(obj)));
                        default -> {}
                    }
                } else if (String.class.equals(c)) {
                    String str = (String) f.get(obj);
                    if (str != null) {
                        f.set(obj, str.toUpperCase());
                    }
                }
            } catch (IllegalAccessException iae) {
                iae.printStackTrace();
                return;
            }
        }
        
        System.out.println(obj.getS());
        System.out.println(obj.getI());
        System.out.println(obj.getF());
    }
    
    // Modern: Record with reflection (Java 16+)
    record Bar(String name, int value, double score) {}
    
    private void test_record_reflection() {
        System.out.println("// Record reflection (Java 16+)");
        Bar bar = new Bar("test", 42, 3.14);
        
        for (Field f : bar.getClass().getDeclaredFields()) {
            System.out.println("Record field: " + f.getName() + " : " + f.getType().getName());
        }
        
        // Records have canonical constructor, accessor methods
        System.out.println("Record accessors: " + bar.name() + ", " + bar.value() + ", " + bar.score());
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_reflect();
        test_record_reflection();
        test_nothing();
    }
    
    public static void main(String[] args) {
        ReflectField worker = new ReflectField();
        worker.test();
    }
}
package modern.java21.lang.annotation;

import java.lang.reflect.Field;

public class Ann {
    
    public void test_annotation() {
        Target target = new Target();
        target.setS("lunch");
        target.setT("supper");
        
        // Modern: Stream API for reflection (Java 8+)
        for (Field f : target.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                String prefix = "";
                String suffix = "";
                
                // Check annotations
                if (f.isAnnotationPresent(BisInDie.Simple.class)) {
                    prefix = "twice ";
                    suffix = " in a day";
                } else if (f.isAnnotationPresent(BisInDie.Parameter.class)) {
                    BisInDie.Parameter bid_param = f.getAnnotation(BisInDie.Parameter.class);
                    prefix = bid_param.prefix();
                    suffix = bid_param.suffix();
                }
                
                if (!prefix.isEmpty() || !suffix.isEmpty()) {
                    String txt = (String) f.get(target);
                    f.set(target, prefix + txt + suffix);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println(target.getS());
        System.out.println(target.getT());
    }
    
    // Modern: Record for annotation target (Java 16+)
    record AnnotatedTarget(
        @BisInDie.Simple String s,
        @BisInDie.Parameter(prefix = "twelve ", suffix = " in a day") String t,
        int i,
        float f
    ) {}
    
    private void test_record_annotation() {
        System.out.println("// Record with annotations (Java 16+)");
        AnnotatedTarget target = new AnnotatedTarget("lunch", "supper", 0, 0f);
        
        for (Field f : target.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                System.out.println("Field: " + f.getName() + " = " + f.get(target));
                
                // Check annotations
                if (f.isAnnotationPresent(BisInDie.Simple.class)) {
                    System.out.println("  @Simple");
                }
                if (f.isAnnotationPresent(BisInDie.Parameter.class)) {
                    BisInDie.Parameter p = f.getAnnotation(BisInDie.Parameter.class);
                    System.out.println("  @Parameter(prefix=\"" + p.prefix() + "\", suffix=\"" + p.suffix() + "\")");
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_annotation();
        test_record_annotation();
        test_nothing();
    }
    
    public static void main(String[] args) {
        Ann worker = new Ann();
        worker.test();
    }
}
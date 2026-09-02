package modern.java21.lang;

public class ReturnOExitDemo {
    
    private void test_finally() {
        boolean should_exit = false;
        try {
            boolean fatal_occured = false;
            if (fatal_occured) {
                System.out.println("System.exit() in try");
                System.exit(1); // exit immediately -- not executes finally
                System.out.println("after System.exit()");
            }
            fatal_occured = true;
            if (fatal_occured) {
                should_exit = true;
                return; // executes finally
                //System.out.println("after return"); // compile error : unreachable statement
            }
            System.out.println(Integer.parseInt("not a number"));
        } catch (Throwable t) {
            t.printStackTrace(System.out);
            printStackTrace(new Exception(t));
        } finally {
            System.out.println("finally");
            if (should_exit) System.exit(1);
            System.out.println("finally after System.exit()");
        }
    }
    
    // Modern: Using StringBuilder and StackWalker (Java 9+)
    private void printStackTrace(Throwable t) {
        StringBuilder sb = new StringBuilder();
        String crnl = System.lineSeparator();
        sb.append(t.getClass().getName()).append(": ").append(t.getMessage()).append(crnl);
        
        // Modern: StackWalker (Java 9+) for stack trace
        StackWalker.getInstance().forEach(frame -> 
            sb.append("    at ").append(frame).append(crnl)
        );
        
        Throwable cause = t.getCause();
        if (cause != null) {
            sb.append("Caused by: ").append(crnl);
            printStackTrace(cause);
        }
        System.out.print(sb.toString());
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_finally();
        test_nothing();
    }
    
    public static void main(String[] args) {
        ReturnOExitDemo worker = new ReturnOExitDemo();
        worker.test();
    }
}
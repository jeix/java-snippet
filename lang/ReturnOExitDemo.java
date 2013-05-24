package lang;

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
	
	// Throwable.printStackTrace() clone
	private void printStackTrace(Throwable t) {
		StringBuffer sb = new StringBuffer();
		String crnl = "\r\n";
		sb.append(t.getClass().getName()).append(": ").append(t.getMessage()).append(crnl);
		StackTraceElement[] stacks = t.getStackTrace();
		for (int i = 0; i < stacks.length; i++) {
			StackTraceElement stack = stacks[i];
			sb.append("    at ")
				.append(stack.getClassName())
				.append(".")
				.append(stack.getMethodName())
				.append("(")
				.append(stack.getFileName())
				.append(":")
				.append(stack.getLineNumber())
				.append(")")
				.append(crnl);
		}
		Throwable cause = t.getCause();
		if (null != cause) sb.append("Caused ");
		System.out.print(sb.toString());
		if (null != cause) printStackTrace(cause);
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

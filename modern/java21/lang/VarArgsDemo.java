package modern.java21.lang;

import java.time.Instant;

public class VarArgsDemo {

	private void demo(Object... args) {
		for (Object o : args) {
			System.out.println(o.getClass().getName() + "::"+ o);
		}
	}

	private void test_var_args(Object... args) {
		demo(new Object[] {false, 42, "The Universe", Instant.now()});
		demo(false, 42, "The Universe", Instant.now());
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

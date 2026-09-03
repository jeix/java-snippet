package lang;

import java.util.Calendar;

public class VarArgsDemo {
	
	private void demo(Object... args) {
		for (Object o : args) {
			System.out.println(o.getClass().getName() + "::"+ o);
		}
	}
	
	private void test_var_args(Object... args) {
		demo(new Object[] {false, 42, "The Universe", Calendar.getInstance().getTime()});
		demo(false, 42, "The Universe", Calendar.getInstance().getTime());
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

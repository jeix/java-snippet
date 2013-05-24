package lang;

import java.util.ArrayList;
import java.util.List;

public class NullType {
	
	class Something {}
	
	private void test_null_type() {
		Something something = null;
		if (something instanceof Something) { // not happen
			System.out.print("calling with Something...");
			check_instance(something);
		} else if (something instanceof Object) { // not happen
			System.out.print("calling with Object...");
			check_instance(something);
		} else {
			System.out.print("calling...");
			check_instance(something);
		}
		
		String s = null;
		List list = new ArrayList();
		list.add(s); // warning: unchecked
		System.out.print("calling with casted Something...");
		check_instance((Something) list.get(0)); // no error
		System.out.print("calling with casted Something again...");
		// check_instance((Something) s); // error - inconvertible types
		check_instance((Something) (Object) s); // no error
		
		System.out.print("calling with null...");
		check_instance(null);
	}
	private void check_instance(Something something) {
		if (something instanceof Something) { // not happen
			System.out.println("called with Something");
		// } else if (something instanceof String) { // compile-time error: inconvertible types
			// System.out.println("called with String");
		} else if (something instanceof Object) { // not happen
			System.out.println("called with Object");
		} else {
			System.out.println("called");
		}
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_null_type();
		test_nothing();
	}
	
	public static void main(String[] args) {
		NullType worker = new NullType();
		worker.test();
	}
}

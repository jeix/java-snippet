package modern.java21.lang;

import java.util.ArrayList;
import java.util.List;

// 원형 유지: null이 어떤 타입의 instanceof에도 해당하지 않으면서도 어떤 타입으로든 캐스팅이
// 통과한다는 것이 주제다. raw List/ArrayList도 컴파일 시점 타입 검사를 피해 null String을
// 넣었다가 다른 타입으로 캐스팅해 꺼내는 데 필요한 부분이라 그대로 둔다.

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

package modern.java21.collection;

import java.util.List;

public class ListToArrayTester {

	private void test_List_toArray() {
		List<String> list = List.of("1", "2", "3");

		String[] a = list.toArray(String[]::new);
		System.out.println(a.length);
		for (String s : a) {
			System.out.print(s);
		}
		System.out.println();
	}

	private void test_nothing() {
		System.out.println(":wq");
	}

	public void test() {
		test_List_toArray();
		test_nothing();
	}

	public static void main(String[] args) {
		ListToArrayTester worker = new ListToArrayTester();
		worker.test();
	}
}

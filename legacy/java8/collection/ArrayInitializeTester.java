package collection;

public class ArrayInitializeTester {
	
	private void test_declaration_only_with_unknown_size() {
		String[] a = {}; // size is unknown
		print_array_size(a); // -> size=0
		print_array(a); // -> []
		try {
			while (a.length < 3) {
				a[a.length] = fetch_data(a.length);
			}
		} catch (ArrayIndexOutOfBoundsException aioobe) { // OOPS -- isn't array mutable?
			System.err.println(aioobe.getClass().getCanonicalName()); // -> java.lang.ArrayIndexOutOfBoundsException
		}
		while (a.length < 3) {
			String[] aa = (String[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), a.length + 1);
			System.arraycopy(a, 0, aa, 0, a.length);
			a = aa;
			a[a.length - 1] = fetch_data(a.length - 1);
			print_array_size(a); // -> size=1 -> size=2 -> size=3
			print_array(a); // -> [1] -> [1, 2] -> [1, 2, 3]
		}
	}
	
	private void print_array_size(String[] a) {
		if (a != null) {
			System.out.println("size=" + String.valueOf(a.length));
		}
	}
	
	private void print_array(String[] a) {
		if (a != null) {
			System.out.print("[");
			for (int i = 0; i < a.length; i++) {
				if (i > 0) {
					System.out.print(", ");
				}
				System.out.print(a[i]);
			}
			System.out.println("]");
		}
	}
	
	private String fetch_data(int ix) {
		return String.valueOf(ix);
	}
	
	private void test_declaration_only_with_known_size() {
		String[] a = new String[3]; // size is known // initialize with null
		print_array_size(a); // -> size=3
		print_array(a); // -> [null, null, null]
		for (int i = 0; i < a.length; i++) {
			a[i] = fetch_data(i);
		}
		print_array(a); // -> [1, 2, 3]
	}
	
	private void test_declaration_and_initialization() {
		String[] a = /*new String[]*/ {"1", "2", "3"};
		print_array_size(a); // -> size=3
		print_array(a); // -> [1, 2, 3]
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_declaration_only_with_unknown_size();
		test_declaration_only_with_known_size();
		test_declaration_and_initialization();
		test_nothing();
	}
	
	public static void main(String[] args) {
		ArrayInitializeTester worker = new ArrayInitializeTester();
		worker.test();
	}
}

package collection;

import static test.Expect.expect;

public class ArrayExtendTester {
	
	private void test_with_declared_to_unknown_size() {
		String[] a = {}; // size is unknown
		expect(0, a.length);
		expect("[]", json_of_array(a));
		
		// cannot do this
		try {
			a[0] = "1";
			expect(ArrayIndexOutOfBoundsException.class);
		} catch (ArrayIndexOutOfBoundsException aioobe) { // OOPS -- maybe array isn't mutable
			expect("java.lang.ArrayIndexOutOfBoundsException", aioobe.getClass().getCanonicalName());
		}
		
		// do this
		a = extend_array(a, "1", "2", "3");
		expect(3, a.length);
		expect("[1, 2, 3]", json_of_array(a));
	}
	
	private void test_with_declared_to_known_size() {
		String[] a = new String[3]; // size is known // initialize with null
		expect(3, a.length);
		expect("[null, null, null]", json_of_array(a));
		
		a[0] = "1";
		a[1] = "2";
		a[2] = "3";
		expect(3, a.length);
		expect("[1, 2, 3]", json_of_array(a));
	}
	
	private void test_with_declared_and_initialized() {
		String[] a = /*new String[]*/ {"1", "2", "3"};
		expect(3, a.length);
		expect("[1, 2, 3]", json_of_array(a));
		
		a = extend_array(a, "4", "5", "6");
		expect(6, a.length);
		expect("[1, 2, 3, 4, 5, 6]", json_of_array(a));
	}
	
	private String json_of_array(String[] a) {
		if (a != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (int i = 0; i < a.length; i++) {
				if (i > 0) {
					sb.append(", ");
				}
				sb.append(a[i]);
			}
			sb.append("]");
			return sb.toString();
		}
		return null;
	}
	
	private String[] extend_array(String[] a, String... adds) {
		if (adds.length > 0) {
			String[] aa = (String[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), a.length + adds.length);
			System.arraycopy(a, 0, aa, 0, a.length);
			int offset = a.length;
			for (String add : adds) {
				aa[offset++] = add;
			}
			return aa;
		}
		return a;
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_with_declared_to_unknown_size();
		test_with_declared_to_known_size();
		test_with_declared_and_initialized();
		test_nothing();
	}
	
	public static void main(String[] args) {
		ArrayExtendTester worker = new ArrayExtendTester();
		worker.test();
	}
}
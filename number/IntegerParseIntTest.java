package number;

public class IntegerParseIntTest {
	
	private void test_Integer_parseInt() {
		System.out.println(Integer.parseInt("010")); // -> 10 not 8
		System.out.println(Integer.parseInt("000")); // -> 0
		try {
			System.out.println(Integer.parseInt("0x10")); // NumberFormatException
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException");
		}
		System.out.println(Integer.parseInt("10", 8)); // -> 8
		System.out.println(Integer.parseInt("00FF", 16)); // -> 255
	}
	
	private void test_Float_parseFloat() {
		System.out.println(Float.parseFloat("0")); // -> 0.0
		System.out.println(Float.parseFloat("1")); // -> 1.0
		System.out.println(Float.parseFloat("1.0")); // -> 1.0
		System.out.println(Float.parseFloat("1.01")); // -> 1.01
		System.out.println(Float.parseFloat("1.1")); // -> 1.1
		System.out.println(Float.parseFloat("1.10")); // -> 1.1
		System.out.println(Float.parseFloat("1.11")); // -> 1.11
		System.out.println(Float.parseFloat("2.010")); // -> 2.01
		System.out.println(Float.parseFloat("2.012")); // -> 2.012
		System.out.println(Float.parseFloat("09")); // -> 9.0
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_Integer_parseInt();
		test_Float_parseFloat();
		test_nothing();
	}
	
	public static void main(String[] args) {
		IntegerParseIntTest worker = new IntegerParseIntTest();
		worker.test();
	}
}

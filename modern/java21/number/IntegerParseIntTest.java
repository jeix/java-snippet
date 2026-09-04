package modern.java21.number;

public class IntegerParseIntTest {

	private void testIntegerParseInt() {
		System.out.println(Integer.parseInt("010"));
		System.out.println(Integer.parseInt("000"));
		try {
			System.out.println(Integer.parseInt("0x10"));
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException");
		}
		System.out.println(Integer.parseInt("10", 8));
		System.out.println(Integer.parseInt("00FF", 16));
	}

	private void testFloatParseFloat() {
		System.out.println(Float.parseFloat("0"));
		System.out.println(Float.parseFloat("1"));
		System.out.println(Float.parseFloat("1.0"));
		System.out.println(Float.parseFloat("1.01"));
		System.out.println(Float.parseFloat("1.1"));
		System.out.println(Float.parseFloat("1.10"));
		System.out.println(Float.parseFloat("1.11"));
		System.out.println(Float.parseFloat("2.010"));
		System.out.println(Float.parseFloat("2.012"));
		System.out.println(Float.parseFloat("09"));
	}

	private void testNothing() {
		System.out.println(":wq");
	}

	void test() {
		testIntegerParseInt();
		testFloatParseFloat();
		testNothing();
	}

	public static void main(String[] args) {
		var worker = new IntegerParseIntTest();
		worker.test();
	}
}
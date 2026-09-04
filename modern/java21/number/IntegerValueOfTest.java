package modern.java21.number;

public class IntegerValueOfTest {

	public static void main(String[] args) {
		System.out.println(Integer.valueOf("000"));
		System.out.println(Integer.valueOf("001"));
		System.out.println(Integer.valueOf("008"));
		System.out.println(Integer.valueOf("010"));
		try {
			System.out.println(Integer.parseInt("0x10"));
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException");
		}
		System.out.println(Integer.parseInt("10", 8));
		System.out.println(Integer.parseInt("00FF", 16));
	}
}
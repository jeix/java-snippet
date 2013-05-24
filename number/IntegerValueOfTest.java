package number;

public class IntegerValueOfTest {
	
	public static void main(String[] args) {
		System.out.println(Integer.valueOf("000")); // -> 0
		System.out.println(Integer.valueOf("001")); // -> 1
		System.out.println(Integer.valueOf("008")); // -> 8
		System.out.println(Integer.valueOf("010")); // -> 10
		try {
			System.out.println(Integer.parseInt("0x10")); // NumberFormatException
		} catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException");
		}
		System.out.println(Integer.parseInt("10", 8)); // -> 8
		System.out.println(Integer.parseInt("00FF", 16)); // -> 255
	}
}

package modern.java21.number;

// 원형 유지: Integer.valueOf()도 Integer.parseInt()와 마찬가지로 선행 0을 무시하고
// 디폴트 진법이 10이라는 것을 확인하는 게 주제라 바꿀 대상이 없다.

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

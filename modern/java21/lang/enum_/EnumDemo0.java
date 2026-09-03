package modern.java21.lang.enum_;

// 원형 유지: enum 도입 이전에 정수 상수 + 이름 배열로 열거형을 흉내 내던 방식을 보여주는
// 시리즈의 출발점이다. 이후 단계(EnumDemo1~4)와 나란히 둬야 개선 흐름이 보인다.
// Java 21 판: lang/enum_/EnumDemo5.java

public class EnumDemo0 {
	
	private static class Day {
		public static final int SUNDAY = 0;
		public static final int MONDAY = 1;
		public static final int TUESDAY = 2;
		public static final int WEDNESDAY = 3;
		public static final int THURSDAY = 4;
		public static final int FRIDAY = 5;
		public static final int SATURDAY = 6;
		public static final String[] NAMES = {
			"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
		};
	}
	
	public static void main(String[] args) {
		for (int i = Day.MONDAY; i <= Day.FRIDAY; i++) {
			System.out.println(Day.NAMES[i]);
		}
	}
}

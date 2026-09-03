package modern.java21.lang.enum_;

import java.util.EnumSet;

// 원형 유지: enum에 필드·생성자·메서드를 추가하는 단계를 보여주는 시리즈의 한 단계다.
// Java 21 판: lang/enum_/EnumDemo5.java

public class EnumDemo2 {
	
	enum Day {
		SUNDAY("Sunday"),
		MONDAY("Monday"),
		TUESDAY("Tuesday"),
		WEDNESDAY("Wednesday"),
		THURSDAY("Thursday"),
		FRIDAY("Friday"),
		SATURDAY("Saturday");
		
		private final String name;
		Day(String name) {
			this.name = name;
		}
		public String fullname() {
			return name;
		}
	};
	
	public static void main(String[] args) {
		for (Day d : EnumSet.range(Day.MONDAY, Day.FRIDAY)) {
			System.out.println(d.fullname());
		}
	}
}

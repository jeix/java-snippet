package modern.java21.lang.enum_;

import java.util.EnumSet;

// 원형 유지: enum 도입 직후의 가장 단순한 형태(EnumSet 활용 포함)를 보여주는 시리즈의 한 단계다.
// Java 21 판: lang/enum_/EnumDemo5.java

public class EnumDemo1 {
	
	enum Day { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY };
	
	public static void main(String[] args) {
		for (Day d : Day.values()) {
			if (Day.SUNDAY != d && Day.SATURDAY != d) {
				System.out.println(d);
					/*
					 * PrintStream#println(Object)
					 * PrintStream#print(Object)
					 * String.valueOf(Object)
					 * Object#toString()
					 */
			}
		}
		for (Day d : Day.values()) {
			if (! EnumSet.of(Day.SUNDAY, Day.SATURDAY).contains(d)) {
				System.out.println(d);
			}
		}
		for (Day d : EnumSet.range(Day.MONDAY, Day.FRIDAY)) {
			System.out.println(d);
		}
	}
}

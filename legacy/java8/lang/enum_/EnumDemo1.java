package lang.enum_;

import java.util.EnumSet;

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

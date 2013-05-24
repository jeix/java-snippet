package lang.enum_;

import java.util.EnumSet;

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

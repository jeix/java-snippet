package lang.enum_;

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

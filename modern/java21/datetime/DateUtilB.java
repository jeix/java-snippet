package modern.java21.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DateUtilB {

	private static final String DEFAULT_FORMAT = "yyyy-MM-dd";

	private final DateTimeFormatter formatter;
	private LocalDate base;

	public DateUtilB() {
		this(DEFAULT_FORMAT);
	}
	public DateUtilB(String format) {
		this(format, null);
	}
	public DateUtilB(LocalDate date_base) {
		this(DEFAULT_FORMAT, date_base);
	}
	public DateUtilB(String format, LocalDate date_base) {
		formatter = DateTimeFormatter.ofPattern(format);
		base = (date_base != null) ? date_base : LocalDate.now();
	}

	public static DateUtilB from_string(String format, String date_str) {
		DateUtilB instance = new DateUtilB(format, null);
		instance.base = instance.parse(date_str);
		return instance;
	}

	public String format(LocalDate date) {
		return formatter.format(date);
	}

	public LocalDate parse(String date_str) {
		return LocalDate.parse(date_str, formatter);
	}

	private void test_convert_date_string() {
		LocalDate date = LocalDate.parse("2010-11-17");
		System.out.println("LocalDate#toString() : " + date); // => 2010-11-17
		System.out.println("DateTimeFormatter#format() : " + format(date)); // => 2010-11-17
		System.out.println("DateTimeFormatter#parse() : " + parse(format(date))); // => 2010-11-17
	}

	public String today() {
		return format(base);
	}
	public String tomorrow() {
		return days_go(1);
	}
	public String yesterday() {
		return days_go(-1);
	}
	private String days_go(int n) {
		return format(base.plusDays(n));
	}
	public String next_week() {
		return weeks_go(1);
	}
	public String prev_week() {
		return weeks_go(-1);
	}
	private String weeks_go(int n) {
		return days_go(7 * n);
	}
	public String next_month() {
		return months_go(1);
	}
	public String prev_month() {
		return months_go(-1);
	}
	private String months_go(int n) {
		return format(base.plusMonths(n));
	}

	private static void test_march_31() {
		DateUtilB util = new DateUtilB("yyyy.MM.dd", LocalDate.parse("2011-03-31"));
		if (! "2011.03.31".equals(util.today())) System.err.println("today() failed");
		if (! "2011.03.30".equals(util.yesterday())) System.err.println("yesterday() failed");
		if (! "2011.04.01".equals(util.tomorrow())) System.err.println("tomorrow() failed");
		if (! "2011.03.24".equals(util.prev_week())) System.err.println("prev_week() failed");
		if (! "2011.04.07".equals(util.next_week())) System.err.println("next_week() failed");
		if (! "2011.02.28".equals(util.prev_month())) System.err.println("prev_month() failed");
		if (! "2011.04.30".equals(util.next_month())) System.err.println("next_month() failed");
	}

	public class Period {
		private String a_month_ago_tomorrow() {
			return format(base.minusMonths(1).plusDays(1));
		}
		private String a_month_after_yesterday() {
			return format(base.plusMonths(1).minusDays(1));
		}

		public String[] for_a_month_until_yesterday() {
			return new String[] {prev_month(), yesterday()};
		}
		public String[] for_a_month_until_today() {
			return new String[] {a_month_ago_tomorrow(), today()};
		}
		public String[] for_a_month_from_today() {
			return new String[] {today(), a_month_after_yesterday()};
		}
		public String[] for_a_month_from_tomorrow() {
			return new String[] {tomorrow(), next_month()};
		}
	}

	private void print_daies() {
		Period util = new Period();
		String[] period = util.for_a_month_until_yesterday();
		System.out.println("for a month until yesterday=" + period[0] + " ~ " + period[1]);
		period = util.for_a_month_until_today();
		System.out.println("for a month until today=" + period[0] + " ~ " + period[1]);
		period = util.for_a_month_from_today();
		System.out.println("for a month from today=" + period[0] + " ~ " + period[1]);
		period = util.for_a_month_from_tomorrow();
		System.out.println("for a month from tomorrow=" + period[0] + " ~ " + period[1]);
	}

	private void test_nothing() {
		System.out.println(":wq");
	}

	public void test() {
		test_convert_date_string();
		test_march_31();
		print_daies();
		test_nothing();
	}

	public static void main(String[] args) {
		DateUtilB worker = new DateUtilB();
		worker.test();
	}
}

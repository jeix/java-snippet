package modern.java21.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

class DateUtilB {
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd";

	private final DateTimeFormatter formatter;
	private final LocalDate base;

	public DateUtilB() {
		this(DEFAULT_FORMAT, LocalDate.now());
	}

	public DateUtilB(String format) {
		this(format, LocalDate.now());
	}

	public DateUtilB(LocalDate dateBase) {
		this(DEFAULT_FORMAT, dateBase);
	}

	public DateUtilB(String format, LocalDate dateBase) {
		formatter = DateTimeFormatter.ofPattern(format);
		base = Objects.requireNonNullElseGet(dateBase, LocalDate::now);
	}

	public static DateUtilB from_string(String format, String dateString) {
		var instance = new DateUtilB(format);
		return new DateUtilB(format, instance.parse(dateString));
	}

	public String format(LocalDate date) {
		return formatter.format(date);
	}

	public LocalDate parse(String dateString) {
		LocalDate parsed = LocalDate.parse(dateString, formatter);
		if (!format(parsed).equals(dateString)) {
			throw new DateTimeParseException("Invalid date", dateString, 0);
		}
		return parsed;
	}

	private void test_convert_date_string() {
		LocalDate date = LocalDate.of(2010, 11, 17);
		System.out.println("LocalDate#toString() : " + date);
		System.out.println("DateTimeFormatter#format() : " + format(date));
		System.out.println("LocalDate#parse() : " + parse(format(date)));
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

	private String days_go(int days) {
		return format(base.plusDays(days));
	}

	public String next_week() {
		return weeks_go(1);
	}

	public String prev_week() {
		return weeks_go(-1);
	}

	private String weeks_go(int weeks) {
		return format(base.plusWeeks(weeks));
	}

	public String next_month() {
		return months_go(1);
	}

	public String prev_month() {
		return months_go(-1);
	}

	private String months_go(int months) {
		return format(base.plusMonths(months));
	}

	private static void test_march_31() {
		var util = new DateUtilB("yyyy.MM.dd", LocalDate.of(2011, 3, 31));
		assertDate("2011.03.31", util.today(), "today");
		assertDate("2011.03.30", util.yesterday(), "yesterday");
		assertDate("2011.04.01", util.tomorrow(), "tomorrow");
		assertDate("2011.03.24", util.prev_week(), "prev_week");
		assertDate("2011.04.07", util.next_week(), "next_week");
		assertDate("2011.02.28", util.prev_month(), "prev_month");
		assertDate("2011.04.30", util.next_month(), "next_month");
	}

	public final class Period {
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
		var util = new Period();
		printPeriod("for a month until yesterday", util.for_a_month_until_yesterday());
		printPeriod("for a month until today", util.for_a_month_until_today());
		printPeriod("for a month from today", util.for_a_month_from_today());
		printPeriod("for a month from tomorrow", util.for_a_month_from_tomorrow());
	}

	private static void printPeriod(String label, String[] period) {
		System.out.println(label + "=" + period[0] + " ~ " + period[1]);
	}

	private static void assertDate(String expected, String actual, String operation) {
		if (!expected.equals(actual)) {
			throw new AssertionError(operation + ": expected " + expected + " but was " + actual);
		}
	}

	public void test() {
		test_convert_date_string();
		test_march_31();
		print_daies();
		System.out.println(":wq");
	}

	public static void main(String[] args) {
		new DateUtilB().test();
	}
}

package modern.java21.datetime;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateUtil {
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	private final DateTimeFormatter formatter;
	private LocalDate base;

	public DateUtil() {
		this(DEFAULT_DATE_FORMAT, LocalDate.now());
	}

	public DateUtil(String dateFormat) {
		this(dateFormat, LocalDate.now());
	}

	public DateUtil(LocalDate date) {
		this(DEFAULT_DATE_FORMAT, date);
	}

	public DateUtil(String dateFormat, LocalDate date) {
		formatter = DateTimeFormatter.ofPattern(dateFormat);
		base = Objects.requireNonNullElseGet(date, LocalDate::now);
	}

	private void set_date(String date) {
		base = LocalDate.parse(date, formatter);
	}

	public String today() {
		return format(base);
	}

	public String yesterday() {
		return format(base.minusDays(1));
	}

	public String tomorrow() {
		return format(base.plusDays(1));
	}

	public String a_week_ago() {
		return format(base.minusWeeks(1));
	}

	public String a_week_after() {
		return format(base.plusWeeks(1));
	}

	public String a_month_ago() {
		return format(base.minusMonths(1));
	}

	public String a_month_after() {
		return format(base.plusMonths(1));
	}

	private String a_month_ago_tomorrow() {
		return format(base.minusMonths(1).plusDays(1));
	}

	private String a_month_after_yesterday() {
		return format(base.plusMonths(1).minusDays(1));
	}

	public String[] for_a_month_until_yesterday() {
		return new String[] {a_month_ago(), yesterday()};
	}

	public String[] for_a_month_until_today() {
		return new String[] {a_month_ago_tomorrow(), today()};
	}

	public String[] for_a_month_from_today() {
		return new String[] {today(), a_month_after_yesterday()};
	}

	public String[] for_a_month_from_tomorrow() {
		return new String[] {tomorrow(), a_month_after()};
	}

	private String format(LocalDate date) {
		return formatter.format(date);
	}

	public void test_oneday() {
		set_date("20091105");
		assertDate("20091105", today(), "today");
		assertDate("20091104", yesterday(), "yesterday");
		assertDate("20091106", tomorrow(), "tomorrow");
		assertDate("20091029", a_week_ago(), "a_week_ago");
		assertDate("20091112", a_week_after(), "a_week_after");
		assertDate("20091005", a_month_ago(), "a_month_ago");
		assertDate("20091205", a_month_after(), "a_month_after");
	}

	public void test_march_31() {
		set_date("20090331");
		assertDate("20090331", today(), "today");
		assertDate("20090330", yesterday(), "yesterday");
		assertDate("20090401", tomorrow(), "tomorrow");
		assertDate("20090324", a_week_ago(), "a_week_ago");
		assertDate("20090407", a_week_after(), "a_week_after");
		assertDate("20090228", a_month_ago(), "a_month_ago");
		assertDate("20090430", a_month_after(), "a_month_after");
	}

	private static void assertDate(String expected, String actual, String operation) {
		if (!expected.equals(actual)) {
			throw new AssertionError(operation + ": expected " + expected + " but was " + actual);
		}
	}

	public static void main(String[] args) {
		var worker = new DateUtil("yyyyMMdd");
		worker.test_oneday();
		worker.test_march_31();

		LocalDate nextMonth = LocalDate.now().plusMonths(1);
		System.out.println(nextMonth);
		System.out.println(nextMonth.getMonthValue());
		System.out.println(nextMonth.lengthOfMonth());
		System.out.println(YearMonth.from(nextMonth).atEndOfMonth());
	}
}

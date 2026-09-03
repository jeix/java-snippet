package modern.java21.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateStringTest {
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

	private final LocalDate base = LocalDate.now();

	private void test_convert_date_string() {
		System.out.println("====");
		String formatted = DATE_FORMAT.format(base);
		System.out.println("DateTimeFormatter#format() : " + formatted);
		System.out.println("LocalDate#parse() : " + LocalDate.parse(formatted, DATE_FORMAT));
		System.out.println("LocalDate#toString() : " + base);
	}

	private void test_get_date_string() {
		System.out.println("====");
		System.out.println("today() : " + today());
		System.out.println("tomorrow() : " + tomorrow());
		System.out.println("yesterday() : " + yesterday());
		System.out.println("next_week() : " + next_week());
		System.out.println("prev_week() : " + prev_week());
		System.out.println("weeks_go(2) : " + weeks_go(2));
		System.out.println("weeks_go(-2) : " + weeks_go(-2));
		System.out.println("next_month() : " + next_month());
		System.out.println("prev_month() : " + prev_month());
		System.out.println("months_go(2) : " + months_go(2));
		System.out.println("months_go(-2) : " + months_go(-2));
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

	public String next_week() {
		return weeks_go(1);
	}

	public String prev_week() {
		return weeks_go(-1);
	}

	public String next_month() {
		return months_go(1);
	}

	public String prev_month() {
		return months_go(-1);
	}

	private String days_go(int days) {
		return format(base.plusDays(days));
	}

	private String weeks_go(int weeks) {
		return format(base.plusWeeks(weeks));
	}

	private String months_go(int months) {
		return format(base.plusMonths(months));
	}

	private String format(LocalDate date) {
		return DATE_FORMAT.format(date);
	}

	public void test() {
		test_convert_date_string();
		test_get_date_string();
		System.out.println(":wq");
	}

	public static void main(String[] args) {
		new DateStringTest().test();
	}
}

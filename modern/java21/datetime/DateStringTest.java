package modern.java21.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateStringTest {

	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private void test_convert_date_string() {
		System.out.println("====");

		LocalDate today = LocalDate.now();
		System.out.println("LocalDate#toString() : " + today); // => 2010-11-17

		String s = format(today);
		System.out.println("DateTimeFormatter#format() : " + s); // => 2010-11-17
		LocalDate parsed = parse(s);
		System.out.println("DateTimeFormatter#parse() : " + parsed); // => 2010-11-17
	}

	private String format(LocalDate date) {
		return FORMAT.format(date);
	}

	private LocalDate parse(String s) {
		return LocalDate.parse(s, FORMAT);
	}

	private void test_get_date_string() {
		System.out.println("====");
		System.out.println("today() : " + today()); // => 2010-11-17
		System.out.println("tomorrow() : " + tomorrow()); // => 2010-11-18
		System.out.println("yesterday() : " + yesterday()); // => 2010-11-16
		System.out.println("next_week() : " + next_week()); // => 2010-11-24
		System.out.println("prev_week() : " + prev_week()); // => 2010-11-10
		System.out.println("weeks_go(2) : " + weeks_go(2)); // => 2010-12-01
		System.out.println("weeks_go(-2) : " + weeks_go(-2)); // => 2010-11-03
		System.out.println("next_month() : " + next_month()); // => 2010-12-17
		System.out.println("prev_month() : " + prev_month()); // => 2010-10-17
		System.out.println("months_go(2) : " + months_go(2)); // => 2011-01-17
		System.out.println("months_go(-2) : " + months_go(-2)); // => 2010-09-17
	}

	public String today() {
		return format(LocalDate.now());
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
	private String days_go(int n) {
		return format(LocalDate.now().plusDays(n));
	}
	private String weeks_go(int n) {
		return days_go(7 * n);
	}
	private String months_go(int n) {
		return format(LocalDate.now().plusMonths(n));
	}

	private void test_nothing() {
		System.out.println(":wq");
	}

	public void test() {
		test_convert_date_string();
		test_get_date_string();
		test_nothing();
	}

	public static void main(String[] args) {
		DateStringTest worker = new DateStringTest();
		worker.test();
	}
}

package modern.java21.datetime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeStringTest {
	private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ISO_LOCAL_TIME;

	private final LocalTime base = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

	private void test_convert_time_string() {
		System.out.println("====");
		String formatted = TIME_FORMAT.format(base);
		System.out.println("DateTimeFormatter#format() : " + formatted);
		System.out.println("LocalTime#parse() : " + LocalTime.parse(formatted, TIME_FORMAT));
		System.out.println("LocalTime#toString() : " + base);
	}

	private void test_get_time_string() {
		System.out.println("====");
		System.out.println("now() : " + now());
		System.out.println("minutes_go(1) : " + minutes_go(1));
		System.out.println("minutes_go(-1) : " + minutes_go(-1));
		System.out.println("minutes_go(15) : " + minutes_go(15));
		System.out.println("minutes_go(-15) : " + minutes_go(-15));
		System.out.println("minutes_go(30) : " + minutes_go(30));
		System.out.println("minutes_go(-30) : " + minutes_go(-30));
		System.out.println("hours_go(1) : " + hours_go(1));
		System.out.println("hours_go(-1) : " + hours_go(-1));
		System.out.println("hours_go(6) : " + hours_go(6));
		System.out.println("hours_go(-6) : " + hours_go(-6));
		System.out.println("hours_go(12) : " + hours_go(12));
		System.out.println("hours_go(-12) : " + hours_go(-12));
	}

	private String now() {
		return format(base);
	}

	private String minutes_go(int minutes) {
		return format(base.plusMinutes(minutes));
	}

	private String hours_go(int hours) {
		return format(base.plusHours(hours));
	}

	private String format(LocalTime time) {
		return TIME_FORMAT.format(time);
	}

	public void test() {
		test_convert_time_string();
		test_get_time_string();
		System.out.println(":wq");
	}

	public static void main(String[] args) {
		new TimeStringTest().test();
	}
}

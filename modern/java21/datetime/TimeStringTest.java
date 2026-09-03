package modern.java21.datetime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeStringTest {

	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

	private void test_convert_time_string() {
		System.out.println("====");

		LocalTime time = now();
		String s = format(time);
		System.out.println("DateTimeFormatter#format() : " + s); // => 12:40:04
		LocalTime parsed = parse(s);
		System.out.println("DateTimeFormatter#parse() : " + parsed); // => 12:40:04

		LocalTime local_time = now();
		System.out.println("LocalTime#toString() : " + local_time); // => 12:40:05
		s = local_time.toString();
		local_time = parse(s);
		System.out.println("LocalTime.parse() : " + local_time); // => 12:40:05
	}

	private LocalTime now() {
		return LocalTime.now().withNano(0);
	}

	private String format(LocalTime time) {
		return FORMAT.format(time);
	}

	private LocalTime parse(String s) {
		return LocalTime.parse(s, FORMAT);
	}

	private void test_get_time_string() {
		System.out.println("====");
		System.out.println("now() : " + now_str()); // => 12:40:05
		System.out.println("minutes_go(1) : " + minutes_go(1)); // => 12:41:05
		System.out.println("minutes_go(-1) : " + minutes_go(-1)); // => 12:39:05
		System.out.println("minutes_go(15) : " + minutes_go(15)); // => 12:55:05
		System.out.println("minutes_go(-15) : " + minutes_go(-15)); // => 12:25:05
		System.out.println("minutes_go(30) : " + minutes_go(30)); // => 13:10:05
		System.out.println("minutes_go(-30) : " + minutes_go(-30)); // => 12:10:05
		System.out.println("hours_go(1) : " + hours_go(1)); // => 13:40:05
		System.out.println("hours_go(-1) : " + hours_go(-1)); // => 11:40:05
		System.out.println("hours_go(6) : " + hours_go(6)); // => 18:40:05
		System.out.println("hours_go(-6) : " + hours_go(-6)); // => 06:40:05
		System.out.println("hours_go(12) : " + hours_go(12)); // => 00:40:05
		System.out.println("hours_go(-12) : " + hours_go(-12)); // => 00:40:05
	}

	private String now_str() {
		return format(now());
	}
	private String minutes_go(int n) {
		return format(now().plusMinutes(n));
	}
	private String hours_go(int n) {
		return format(now().plusHours(n));
	}

	private void test_nothing() {
		System.out.println(":wq");
	}

	public void test() {
		test_convert_time_string();
		test_get_time_string();
		test_nothing();
	}

	public static void main(String[] args) {
		TimeStringTest worker = new TimeStringTest();
		worker.test();
	}
}

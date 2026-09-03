package modern.java21.datetime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class TimeUtilB {

	private static final String DEFAULT_FORMAT = "HH:mm:ss";

	private final DateTimeFormatter formatter;
	private LocalTime base;

	public TimeUtilB() {
		this(DEFAULT_FORMAT);
	}
	public TimeUtilB(String format) {
		this(format, null);
	}
	public TimeUtilB(LocalTime time_base) {
		this(DEFAULT_FORMAT, time_base);
	}
	public TimeUtilB(String format, LocalTime time_base) {
		formatter = DateTimeFormatter.ofPattern(format);
		base = (time_base != null) ? time_base : LocalTime.now();
	}

	public static TimeUtilB from_string(String format, String time_str) {
		TimeUtilB instance = new TimeUtilB(format, null);
		instance.base = instance.parse(time_str);
		return instance;
	}

	public String format(LocalTime time) {
		return formatter.format(time);
	}

	public LocalTime parse(String time_str) {
		return LocalTime.parse(time_str, formatter);
	}

	private void test_convert_time_string() {
		LocalTime time = LocalTime.parse("15:26:48");
		System.out.println("LocalTime#toString() : " + time); // => 15:26:48
		System.out.println("DateTimeFormatter#format() : " + format(time)); // => 15:26:48
		System.out.println("DateTimeFormatter#parse() : " + parse(format(time))); // => 15:26:48
	}

	public String now() {
		return format(base);
	}
	private String minutes_go(int n) {
		return format(base.plusMinutes(n));
	}
	private String hours_go(int n) {
		return format(base.plusHours(n));
	}

	private static void test_twelve_forty() {
		TimeUtilB util = new TimeUtilB("HH.mm.ss", LocalTime.parse("12:40:05"));
		if (! "12.40.05".equals(util.now())) System.err.println("today() failed");
		if (! "12.41.05".equals(util.minutes_go(1))) System.err.println("minutes_go(1) failed");
		if (! "12.39.05".equals(util.minutes_go(-1))) System.err.println("minutes_go(-1) failed");
		if (! "12.55.05".equals(util.minutes_go(15))) System.err.println("minutes_go(15) failed");
		if (! "12.25.05".equals(util.minutes_go(-15))) System.err.println("minutes_go(-15) failed");
		if (! "13.10.05".equals(util.minutes_go(30))) System.err.println("minutes_go(30) failed");
		if (! "12.10.05".equals(util.minutes_go(-30))) System.err.println("minutes_go(-30) failed");
		if (! "13.40.05".equals(util.hours_go(1))) System.err.println("hours_go(1) failed");
		if (! "11.40.05".equals(util.hours_go(-1))) System.err.println("hours_go(-1) failed");
		if (! "18.40.05".equals(util.hours_go(6))) System.err.println("hours_go(6) failed");
		if (! "06.40.05".equals(util.hours_go(-6))) System.err.println("hours_go(-6) failed");
		if (! "00.40.05".equals(util.hours_go(12))) System.err.println("hours_go(12) failed");
		if (! "00.40.05".equals(util.hours_go(-12))) System.err.println("hours_go(-12) failed");
	}

	private void test_nothing() {
		System.out.println(":wq");
	}

	public void test() {
		test_convert_time_string();
		test_twelve_forty();
		test_nothing();
	}

	public static void main(String[] args) {
		TimeUtilB worker = new TimeUtilB();
		worker.test();
	}
}

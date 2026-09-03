package modern.java21.datetime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

class TimeUtilB {
	private static final String DEFAULT_FORMAT = "HH:mm:ss";

	private final DateTimeFormatter formatter;
	private final LocalTime base;

	public TimeUtilB() {
		this(DEFAULT_FORMAT, LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
	}

	public TimeUtilB(String format) {
		this(format, LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
	}

	public TimeUtilB(LocalTime timeBase) {
		this(DEFAULT_FORMAT, timeBase);
	}

	public TimeUtilB(String format, LocalTime timeBase) {
		formatter = DateTimeFormatter.ofPattern(format);
		base = Objects.requireNonNullElseGet(
			timeBase,
			() -> LocalTime.now().truncatedTo(ChronoUnit.SECONDS)
		);
	}

	public static TimeUtilB from_string(String format, String timeString) {
		var instance = new TimeUtilB(format);
		return new TimeUtilB(format, instance.parse(timeString));
	}

	public String format(LocalTime time) {
		return formatter.format(time);
	}

	public LocalTime parse(String timeString) {
		LocalTime parsed = LocalTime.parse(timeString, formatter);
		if (!format(parsed).equals(timeString)) {
			throw new DateTimeParseException("Invalid time", timeString, 0);
		}
		return parsed;
	}

	private void test_convert_time_string() {
		LocalTime time = LocalTime.of(15, 26, 48);
		System.out.println("LocalTime#toString() : " + time);
		System.out.println("DateTimeFormatter#format() : " + format(time));
		System.out.println("LocalTime#parse() : " + parse(format(time)));
	}

	public String now() {
		return format(base);
	}

	private String minutes_go(int minutes) {
		return format(base.plusMinutes(minutes));
	}

	private String hours_go(int hours) {
		return format(base.plusHours(hours));
	}

	private static void test_twelve_forty() {
		var util = new TimeUtilB("HH.mm.ss", LocalTime.of(12, 40, 5));
		assertTime("12.40.05", util.now(), "now");
		assertTime("12.41.05", util.minutes_go(1), "minutes_go(1)");
		assertTime("12.39.05", util.minutes_go(-1), "minutes_go(-1)");
		assertTime("12.55.05", util.minutes_go(15), "minutes_go(15)");
		assertTime("12.25.05", util.minutes_go(-15), "minutes_go(-15)");
		assertTime("13.10.05", util.minutes_go(30), "minutes_go(30)");
		assertTime("12.10.05", util.minutes_go(-30), "minutes_go(-30)");
		assertTime("13.40.05", util.hours_go(1), "hours_go(1)");
		assertTime("11.40.05", util.hours_go(-1), "hours_go(-1)");
		assertTime("18.40.05", util.hours_go(6), "hours_go(6)");
		assertTime("06.40.05", util.hours_go(-6), "hours_go(-6)");
		assertTime("00.40.05", util.hours_go(12), "hours_go(12)");
		assertTime("00.40.05", util.hours_go(-12), "hours_go(-12)");
	}

	private static void assertTime(String expected, String actual, String operation) {
		if (!expected.equals(actual)) {
			throw new AssertionError(operation + ": expected " + expected + " but was " + actual);
		}
	}

	public void test() {
		test_convert_time_string();
		test_twelve_forty();
		System.out.println(":wq");
	}

	public static void main(String[] args) {
		new TimeUtilB().test();
	}
}

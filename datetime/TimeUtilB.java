package datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class TimeUtilB {
	
	private static final String DEFAULT_FORMAT = "HH:mm:ss";
	
	private DateFormat formatter;
	private Calendar cal;
	private Date base;
	
	public TimeUtilB() {
		this(DEFAULT_FORMAT);
	}
	public TimeUtilB(String format) {
		this(format, null);
	}
	public TimeUtilB(Date date_base) {
		this(DEFAULT_FORMAT, date_base);
	}
	public TimeUtilB(String format, Date date_base) {
		formatter = new SimpleDateFormat(format);
		cal = Calendar.getInstance();
		if (date_base != null) {
			base = date_base;
		} else {
			base = cal.getTime();
		}
	}

	public static TimeUtilB from_string(String format, String date_str) throws ParseException {
		TimeUtilB instance = new TimeUtilB(format, null);
		instance.base = instance.parse(date_str);
		return instance;
	}

	public String format(Date time) {
		return formatter.format(time);
	}

	public Date parse(String time_str) throws ParseException {
		return formatter.parse(time_str);
	}
	
	// private static Date new_util_date() {
	// 	return new java.util.Date();
	// }
	
	private static class SqlTime {
		private SqlTime() {}

		// private static java.sql.Time new_sql_time() {
		// 	return new_sql_time(new_util_date());
		// }
		// private static java.sql.Time new_sql_time(java.util.Date util_date) {
		// 	return new java.sql.Time(util_date.getTime());
		// }
		
		private static String format(java.sql.Time sql_time) {
			return sql_time.toString();
		}
		
		private static java.sql.Time parse(String time_str) {
			return java.sql.Time.valueOf(time_str);
		}

		// private static String now() {
		// 	return format(new_sql_time());
		// }
	}
	
	private void test_convert_time_string() {
		
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 10, 17, 15, 26, 48);
		Date util_date = cal.getTime();
		System.out.println("java.util.Date#toString() : " + util_date); // => Wed Nov 17 15:26:48 KST 2010
		System.out.println("java.text.DateFormat#format() : " + format(util_date)); // => 15:26:48
		try {
			System.out.println("java.text.DateFormat#parse() : " + parse(format(util_date))); // => Thu Jan 01 15:26:48 KST 1970
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		
		java.sql.Time sql_date = SqlTime.parse("15:26:48");
		System.out.println("java.sql.Time#toString() : " + SqlTime.format(sql_date)); // => 15:26:48
		System.out.println("java.sql.Time.valueOf() : " + sql_date); // => 15:26:48

		// System.out.println(SqlTime.now());
	}
	
	private void reset() {
		cal.setTime(base);
	}
	
	public String now() {
		reset();
		return format(cal.getTime());
	}
	private String minutes_go(int n) {
		reset();
		cal.add(Calendar.MINUTE, n);
		return format(cal.getTime());
	}
	private String hours_go(int n) {
		reset();
		cal.add(Calendar.HOUR, n);
		return format(cal.getTime());
	}

	private static void test_twelve_forty() {
		TimeUtilB util = new TimeUtilB("HH.mm.ss", SqlTime.parse("12:40:05"));
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

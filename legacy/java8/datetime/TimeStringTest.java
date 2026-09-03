package datetime;

import java.util.Calendar;

public class TimeStringTest {
	
	private void test_convert_time_string() {
		System.out.println("====");
		
		java.sql.Time sql_time = null;
		String s = null;
		
		java.util.Date util_date = new java.util.Date();
		s = dateformat_format(util_date);
		System.out.println("java.text.DateFormat#format() : " + s); // => 12:40:04
		util_date = dateformat_parse(s);
		System.out.println("java.text.DateFormat#parse() : " + util_date); // => Thu Jan 01 12:40:04 KST 1970
		
		sql_time = new_sql_time();
		System.out.println("java.sql.Time#toString() : " + sql_time); // => 12:40:05
		s = sql_time.toString();
		sql_time = java.sql.Time.valueOf(s);
		System.out.println("java.sql.Time.valueOf() : " + sql_time); // => 12:40:05
	}
	
	private java.sql.Time new_sql_time() {
		return new_sql_time(new java.util.Date());
	}
	
	private java.sql.Time new_sql_time(java.util.Date util_date) {
		return new java.sql.Time(util_date.getTime());
	}
	
	private String dateformat_format(java.util.Date dt) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm:ss");
		return df.format(dt.getTime());
	}
	
	private java.util.Date dateformat_parse(String s) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm:ss");
		try {
			return df.parse(s);
		} catch (java.text.ParseException pe) {
			pe.printStackTrace();
			return null;
		}
	}
	
	private void test_get_time_string() {
		System.out.println("====");
		System.out.println("now() : " + now()); // => 12:40:05
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
	
	private String now() {
		return new_sql_time().toString();
	}
	private String minutes_go(int n) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, n);
		return new_sql_time(cal.getTime()).toString();
	}
	private String hours_go(int n) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, n);
		return new_sql_time(cal.getTime()).toString();
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

package datetime;

import java.util.Calendar;

public class DateStringTest {
	
	private void test_convert_date_string() {
		System.out.println("====");
		
		java.util.Date util_date = new java.util.Date();
		System.out.println("java.util.Date#toString() : " + util_date); // => Wed Nov 17 15:45:35 KST 2010
		
		java.sql.Date sql_date = null;
		String s = null;
		
		util_date = new java.util.Date();
		s = dateformat_format(util_date);
		System.out.println("java.text.DateFormat#format() : " + s); // => 2010-11-17
		util_date = dateformat_parse(s);
		System.out.println("java.text.DateFormat#parse() : " + util_date); // => Wed Nov 17 00:00:00 KST 2010
		
		sql_date = new_sql_date();
		System.out.println("java.sql.Date#toString() : " + sql_date); // => 2010-11-17
		s = sql_date.toString();
		sql_date = java.sql.Date.valueOf(s);
		System.out.println("java.sql.Date.valueOf() : " + sql_date); // => 2010-11-17
	}
	
	private java.sql.Date new_sql_date() {
		return new_sql_date(new java.util.Date());
	}
	
	private java.sql.Date new_sql_date(java.util.Date util_date) {
		return new java.sql.Date(util_date.getTime());
	}
	
	private String dateformat_format(java.util.Date dt) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return df.format(dt.getTime());
	}
	
	private java.util.Date dateformat_parse(String s) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(s);
		} catch (java.text.ParseException pe) {
			pe.printStackTrace();
			return null;
		}
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
		return new_sql_date().toString();
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
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, n);
		return new_sql_date(cal.getTime()).toString();
	}
	private String weeks_go(int n) {
		return days_go(7 * n);
	}
	private String months_go(int n) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, n);
		return new_sql_date(cal.getTime()).toString();
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

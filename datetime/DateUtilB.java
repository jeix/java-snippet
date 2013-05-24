package datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class DateUtilB {
	
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
	
	private DateFormat formatter;
	private Calendar cal;
	private Date base;
	
	public DateUtilB() {
		this(DEFAULT_FORMAT);
	}
	public DateUtilB(String format) {
		this(format, null);
	}
	public DateUtilB(Date date_base) {
		this(DEFAULT_FORMAT, date_base);
	}
	public DateUtilB(String format, Date date_base) {
		formatter = new SimpleDateFormat(format);
		cal = Calendar.getInstance();
		if (date_base != null) {
			base = date_base;
		} else {
			base = cal.getTime();
		}
	}

	public static DateUtilB from_string(String format, String date_str) throws ParseException {
		DateUtilB instance = new DateUtilB(format, null);
		instance.base = instance.parse(date_str);
		return instance;
	}

	public String format(Date date) {
		return formatter.format(date);
	}

	public Date parse(String date_str) throws ParseException {
		return formatter.parse(date_str);
	}
	
	// private static Date new_util_date() {
	// 	return new java.util.Date();
	// }
	
	private static class SqlDate {
		private SqlDate() {}

		// private static java.sql.Date new_sql_date() {
		// 	return new_sql_date(new_util_date());
		// }
		// private static java.sql.Date new_sql_date(java.util.Date util_date) {
		// 	return new java.sql.Date(util_date.getTime());
		// }
		
		private static String format(java.sql.Date sql_date) {
			return sql_date.toString();
		}
		
		private static java.sql.Date parse(String date_str) {
			return java.sql.Date.valueOf(date_str);
		}

		// private static String today() {
		// 	return format(new_sql_date());
		// }
	}
	
	private void test_convert_date_string() {
		
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 10, 17, 15, 26, 48);
		Date util_date = cal.getTime();
		System.out.println("java.util.Date#toString() : " + util_date); // => 
		System.out.println("java.text.DateFormat#format() : " + format(util_date)); // => 2010-11-17
		try {
			System.out.println("java.text.DateFormat#parse() : " + parse(format(util_date))); // => Wed Nov 17 00:00:00 KST 2010
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		
		java.sql.Date sql_date = SqlDate.parse("2010-11-17");
		System.out.println("java.sql.Date#toString() : " + SqlDate.format(sql_date)); // => 2010-11-17
		System.out.println("java.sql.Date.valueOf() : " + sql_date); // => 2010-11-17

		// System.out.println(SqlDate.today());
	}
	
	private void reset() {
		cal.setTime(base);
	}
	
	public String today() {
		reset();
		return format(cal.getTime());
	}
	public String tomorrow() {
		return days_go(1);
	}
	public String yesterday() {
		return days_go(-1);
	}
	private String days_go(int n) {
		reset();
		cal.add(Calendar.DATE, n);
		return format(cal.getTime());
	}
	public String next_week() {
		return weeks_go(1);
	}
	public String prev_week() {
		return weeks_go(-1);
	}
	private String weeks_go(int n) {
		return days_go(7 * n);
	}
	public String next_month() {
		return months_go(1);
	}
	public String prev_month() {
		return months_go(-1);
	}
	private String months_go(int n) {
		reset();
		cal.add(Calendar.MONTH, n);
		return format(cal.getTime());
	}

	private static void test_march_31() {
		DateUtilB util = new DateUtilB("yyyy.MM.dd", SqlDate.parse("2011-03-31"));
		if (! "2011.03.31".equals(util.today())) System.err.println("today() failed");
		if (! "2011.03.30".equals(util.yesterday())) System.err.println("yesterday() failed");
		if (! "2011.04.01".equals(util.tomorrow())) System.err.println("tomorrow() failed");
		if (! "2011.03.24".equals(util.prev_week())) System.err.println("prev_week() failed");
		if (! "2011.04.07".equals(util.next_week())) System.err.println("next_week() failed");
		if (! "2011.02.28".equals(util.prev_month())) System.err.println("prev_month() failed");
		if (! "2011.04.30".equals(util.next_month())) System.err.println("next_month() failed");
	}
	
	public class Period {
		private String a_month_ago_tomorrow() {
			reset();
			cal.add(Calendar.MONTH, -1);
			cal.add(Calendar.DATE, 1);
			return format(cal.getTime());
		}
		private String a_month_after_yesterday() {
			reset();
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DATE, -1);
			return format(cal.getTime());
		}
		
		public String[] for_a_month_until_yesterday() {
			return new String[] {prev_month(), yesterday()};
		}
		public String[] for_a_month_until_today() {
			return new String[] {a_month_ago_tomorrow(), today()};
		}
		public String[] for_a_month_from_today() {
			return new String[] {today(), a_month_after_yesterday()};
		}
		public String[] for_a_month_from_tomorrow() {
			return new String[] {tomorrow(), next_month()};
		}
	}
	
	private void print_daies() {
		Period util = new Period();
		String[] period = util.for_a_month_until_yesterday();
		System.out.println("for a month until yesterday=" + period[0] + " ~ " + period[1]);
		period = util.for_a_month_until_today();
		System.out.println("for a month until today=" + period[0] + " ~ " + period[1]);
		period = util.for_a_month_from_today();
		System.out.println("for a month from today=" + period[0] + " ~ " + period[1]);
		period = util.for_a_month_from_tomorrow();
		System.out.println("for a month from tomorrow=" + period[0] + " ~ " + period[1]);
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_convert_date_string();
		test_march_31();
		print_daies();
		test_nothing();
	}
	
	public static void main(String[] args) {
		DateUtilB worker = new DateUtilB();
		worker.test();
	}
}

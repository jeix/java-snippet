package datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	
	private DateFormat df;
	private Calendar cal;
	private Date std_dt;
	
	public DateUtil() {
		this(DEFAULT_DATE_FORMAT);
	}
	public DateUtil(String date_format) {
		df = new SimpleDateFormat(date_format);
		cal = Calendar.getInstance();
		std_dt = cal.getTime();
	}
	public DateUtil(Date dt) {
		this(DEFAULT_DATE_FORMAT);
		set_date(dt);
	}
	public DateUtil(String date_format, Date dt) {
		this(date_format);
		set_date(dt);
	}
	
	private void set_date(String dt) throws ParseException {
		set_date(df.parse(dt));
	}
	private void set_date(Date dt) {
		if (dt != null) {
			std_dt = dt;
		}
	}
	
	private void reset_cal_date() {
		cal.setTime(std_dt);
	}
	
	public String today() {
		reset_cal_date();
		return df.format(cal.getTime());
	}
	public String yesterday() {
		reset_cal_date();
		cal.add(Calendar.DATE, -1);
		return df.format(cal.getTime());
	}
	public String tomorrow() {
		reset_cal_date();
		cal.add(Calendar.DATE, 1);
		return df.format(cal.getTime());
	}
	
	public String a_week_ago() {
		reset_cal_date();
		cal.add(Calendar.DATE, -7);
		return df.format(cal.getTime());
	}
	public String a_week_after() {
		reset_cal_date();
		cal.add(Calendar.DATE, 7);
		return df.format(cal.getTime());
	}
	
	public String a_month_ago() {
		reset_cal_date();
		cal.add(Calendar.MONTH, -1);
		return df.format(cal.getTime());
	}
	public String a_month_after() {
		reset_cal_date();
		cal.add(Calendar.MONTH, 1);
		return df.format(cal.getTime());
	}
	
	private String a_month_ago_tomorrow() {
		reset_cal_date();
		cal.add(Calendar.MONTH, -1);
		cal.add(Calendar.DATE, 1);
		return df.format(cal.getTime());
	}
	private String a_month_after_yesterday() {
		reset_cal_date();
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return df.format(cal.getTime());
	}
	
	public String[] for_a_month_until_yesterday() {
		return new String[] {a_month_ago(), yesterday()};
	}
	public String[] for_a_month_until_today() {
		return new String[] {a_month_ago_tomorrow(), today()};
	}
	public String[] for_a_month_from_today() {
		return new String[] {today(), a_month_after_yesterday()};
	}
	public String[] for_a_month_from_tomorrow() {
		return new String[] {tomorrow(), a_month_after()};
	}
	
	public void print_daies() {
		System.out.println("today=" + today());
		System.out.println("yesterday=" + yesterday());
		System.out.println("tomorrow=" + tomorrow());
		System.out.println("a week ago=" + a_week_ago());
		System.out.println("a week after=" + a_week_after());
		System.out.println("a month ago=" + a_month_ago());
		System.out.println("a month after=" + a_month_after());
		String[] period = for_a_month_until_yesterday();
		System.out.println("for a month until yesterday=" + period[0] + " ~ " + period[1]);
		period = for_a_month_until_today();
		System.out.println("for a month until today=" + period[0] + " ~ " + period[1]);
		period = for_a_month_from_today();
		System.out.println("for a month from today=" + period[0] + " ~ " + period[1]);
		period = for_a_month_from_tomorrow();
		System.out.println("for a month from tomorrow=" + period[0] + " ~ " + period[1]);
	}
	
	public void test_oneday() throws Exception {
		set_date("20091105");
		if (! "20091105".equals(today())) System.err.println("today() failed");
		if (! "20091104".equals(yesterday())) System.err.println("yesterday() failed");
		if (! "20091106".equals(tomorrow())) System.err.println("tomorrow() failed");
		if (! "20091029".equals(a_week_ago())) System.err.println("a_week_ago() failed");
		if (! "20091112".equals(a_week_after())) System.err.println("a_week_after() failed");
		if (! "20091005".equals(a_month_ago())) System.err.println("a_month_ago() failed");
		if (! "20091205".equals(a_month_after())) System.err.println("a_month_after() failed");
	}
	
	public void test_march_31() throws Exception {
		set_date("20090331");
		if (! "20090331".equals(today())) System.err.println("today() failed");
		if (! "20090330".equals(yesterday())) System.err.println("yesterday() failed");
		if (! "20090401".equals(tomorrow())) System.err.println("tomorrow() failed");
		if (! "20090324".equals(a_week_ago())) System.err.println("a_week_ago() failed");
		if (! "20090407".equals(a_week_after())) System.err.println("a_week_after() failed");
		if (! "20090228".equals(a_month_ago())) System.err.println("a_month_ago() failed");
		if (! "20090430".equals(a_month_after())) System.err.println("a_month_after() failed");
	}
	
	public static void main(String[] args) throws Exception {
		DateUtil worker = new DateUtil("yyyyMMdd");
		//worker.print_daies();
		worker.test_oneday();
		worker.test_march_31();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		System.out.println(new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT).format(cal.getTime()));
		System.out.println(cal.get(Calendar.MONTH) + 1);
		System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.out.println(java.sql.Date.valueOf(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1 < 10 ? "0" : "") + (cal.get(Calendar.MONTH) + 1) + "-" + (cal.getActualMaximum(Calendar.DAY_OF_MONTH) < 10 ? "0" : "") + cal.getActualMaximum(Calendar.DAY_OF_MONTH)).toString());
	}
}

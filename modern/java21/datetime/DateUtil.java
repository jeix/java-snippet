package modern.java21.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	private final DateTimeFormatter formatter;
	private LocalDate std_dt;

	public DateUtil() {
		this(DEFAULT_DATE_FORMAT);
	}
	public DateUtil(String date_format) {
		formatter = DateTimeFormatter.ofPattern(date_format);
		std_dt = LocalDate.now();
	}
	public DateUtil(LocalDate dt) {
		this(DEFAULT_DATE_FORMAT);
		set_date(dt);
	}
	public DateUtil(String date_format, LocalDate dt) {
		this(date_format);
		set_date(dt);
	}

	private void set_date(String dt) {
		set_date(LocalDate.parse(dt, formatter));
	}
	private void set_date(LocalDate dt) {
		if (dt != null) {
			std_dt = dt;
		}
	}

	public String today() {
		return formatter.format(std_dt);
	}
	public String yesterday() {
		return formatter.format(std_dt.minusDays(1));
	}
	public String tomorrow() {
		return formatter.format(std_dt.plusDays(1));
	}

	public String a_week_ago() {
		return formatter.format(std_dt.minusWeeks(1));
	}
	public String a_week_after() {
		return formatter.format(std_dt.plusWeeks(1));
	}

	public String a_month_ago() {
		return formatter.format(std_dt.minusMonths(1));
	}
	public String a_month_after() {
		return formatter.format(std_dt.plusMonths(1));
	}

	private String a_month_ago_tomorrow() {
		return formatter.format(std_dt.minusMonths(1).plusDays(1));
	}
	private String a_month_after_yesterday() {
		return formatter.format(std_dt.plusMonths(1).minusDays(1));
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

	public void test_oneday() {
		set_date("20091105");
		if (! "20091105".equals(today())) System.err.println("today() failed");
		if (! "20091104".equals(yesterday())) System.err.println("yesterday() failed");
		if (! "20091106".equals(tomorrow())) System.err.println("tomorrow() failed");
		if (! "20091029".equals(a_week_ago())) System.err.println("a_week_ago() failed");
		if (! "20091112".equals(a_week_after())) System.err.println("a_week_after() failed");
		if (! "20091005".equals(a_month_ago())) System.err.println("a_month_ago() failed");
		if (! "20091205".equals(a_month_after())) System.err.println("a_month_after() failed");
	}

	public void test_march_31() {
		set_date("20090331");
		if (! "20090331".equals(today())) System.err.println("today() failed");
		if (! "20090330".equals(yesterday())) System.err.println("yesterday() failed");
		if (! "20090401".equals(tomorrow())) System.err.println("tomorrow() failed");
		if (! "20090324".equals(a_week_ago())) System.err.println("a_week_ago() failed");
		if (! "20090407".equals(a_week_after())) System.err.println("a_week_after() failed");
		if (! "20090228".equals(a_month_ago())) System.err.println("a_month_ago() failed");
		if (! "20090430".equals(a_month_after())) System.err.println("a_month_after() failed");
	}

	public static void main(String[] args) {
		DateUtil worker = new DateUtil("yyyyMMdd");
		//worker.print_daies();
		worker.test_oneday();
		worker.test_march_31();

		LocalDate next_month = LocalDate.now().plusMonths(1);
		System.out.println(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_DATE_FORMAT).format(next_month));
		System.out.println(next_month.getMonthValue());
		System.out.println(next_month.lengthOfMonth());
		System.out.println(next_month.withDayOfMonth(next_month.lengthOfMonth()));
	}
}

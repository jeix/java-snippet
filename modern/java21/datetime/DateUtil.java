package modern.java21.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {
    
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    
    private DateTimeFormatter formatter;
    private LocalDate baseDate;
    
    public DateUtil() {
        this(DEFAULT_DATE_FORMAT);
    }
    public DateUtil(String dateFormat) {
        this.formatter = DateTimeFormatter.ofPattern(dateFormat);
        this.baseDate = LocalDate.now();
    }
    public DateUtil(java.util.Date dt) {
        this(DEFAULT_DATE_FORMAT);
        setDate(dt);
    }
    public DateUtil(String dateFormat, java.util.Date dt) {
        this(dateFormat);
        setDate(dt);
    }
    
    private void setDate(String dt) {
        setDate(LocalDate.parse(dt, formatter));
    }
    private void setDate(java.util.Date dt) {
        if (dt != null) {
            this.baseDate = dt.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        }
    }
    private void setDate(LocalDate dt) {
        if (dt != null) {
            this.baseDate = dt;
        }
    }
    
    public String today() {
        return baseDate.format(formatter);
    }
    public String yesterday() {
        return baseDate.minusDays(1).format(formatter);
    }
    public String tomorrow() {
        return baseDate.plusDays(1).format(formatter);
    }
    
    public String aWeekAgo() {
        return baseDate.minusWeeks(1).format(formatter);
    }
    public String aWeekAfter() {
        return baseDate.plusWeeks(1).format(formatter);
    }
    
    public String aMonthAgo() {
        return baseDate.minusMonths(1).format(formatter);
    }
    public String aMonthAfter() {
        return baseDate.plusMonths(1).format(formatter);
    }
    
    private String aMonthAgoTomorrow() {
        return baseDate.minusMonths(1).plusDays(1).format(formatter);
    }
    private String aMonthAfterYesterday() {
        return baseDate.plusMonths(1).minusDays(1).format(formatter);
    }
    
    public String[] forAMonthUntilYesterday() {
        return new String[] {aMonthAgo(), yesterday()};
    }
    public String[] forAMonthUntilToday() {
        return new String[] {aMonthAgoTomorrow(), today()};
    }
    public String[] forAMonthFromToday() {
        return new String[] {today(), aMonthAfterYesterday()};
    }
    public String[] forAMonthFromTomorrow() {
        return new String[] {tomorrow(), aMonthAfter()};
    }
    
    public void printDays() {
        System.out.println("today=" + today());
        System.out.println("yesterday=" + yesterday());
        System.out.println("tomorrow=" + tomorrow());
        System.out.println("a week ago=" + aWeekAgo());
        System.out.println("a week after=" + aWeekAfter());
        System.out.println("a month ago=" + aMonthAgo());
        System.out.println("a month after=" + aMonthAfter());
        String[] period = forAMonthUntilYesterday();
        System.out.println("for a month until yesterday=" + period[0] + " ~ " + period[1]);
        period = forAMonthUntilToday();
        System.out.println("for a month until today=" + period[0] + " ~ " + period[1]);
        period = forAMonthFromToday();
        System.out.println("for a month from today=" + period[0] + " ~ " + period[1]);
        period = forAMonthFromTomorrow();
        System.out.println("for a month from tomorrow=" + period[0] + " ~ " + period[1]);
    }
    
    public void testOneDay() throws Exception {
        setDate("20091105");
        if (!"20091105".equals(today())) System.err.println("today() failed");
        if (!"20091104".equals(yesterday())) System.err.println("yesterday() failed");
        if (!"20091106".equals(tomorrow())) System.err.println("tomorrow() failed");
        if (!"20091029".equals(aWeekAgo())) System.err.println("a_week_ago() failed");
        if (!"20091112".equals(aWeekAfter())) System.err.println("a_week_after() failed");
        if (!"20091005".equals(aMonthAgo())) System.err.println("a_month_ago() failed");
        if (!"20091205".equals(aMonthAfter())) System.err.println("a_month_after() failed");
    }
    
    public void testMarch31() throws Exception {
        setDate("20090331");
        if (!"20090331".equals(today())) System.err.println("today() failed");
        if (!"20090330".equals(yesterday())) System.err.println("yesterday() failed");
        if (!"20090401".equals(tomorrow())) System.err.println("tomorrow() failed");
        if (!"20090324".equals(aWeekAgo())) System.err.println("a_week_ago() failed");
        if (!"20090407".equals(aWeekAfter())) System.err.println("a_week_after() failed");
        if (!"20090228".equals(aMonthAgo())) System.err.println("a_month_ago() failed");
        if (!"20090430".equals(aMonthAfter())) System.err.println("a_month_after() failed");
    }
    
    public static void main(String[] args) throws Exception {
        DateUtil worker = new DateUtil("yyyyMMdd");
        worker.testOneDay();
        worker.testMarch31();
        
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        System.out.println(nextMonth.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
        System.out.println(nextMonth.getMonthValue());
        System.out.println(nextMonth.lengthOfMonth());
        System.out.println(java.time.LocalDate.of(nextMonth.getYear(), nextMonth.getMonth(), nextMonth.lengthOfMonth()));
    }
}
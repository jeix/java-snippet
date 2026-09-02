package modern.java21.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtilB {
    
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
    
    private DateTimeFormatter formatter;
    private LocalDate base;
    
    public DateUtilB() {
        this(DEFAULT_FORMAT);
    }
    public DateUtilB(String format) {
        this(format, null);
    }
    public DateUtilB(java.util.Date dateBase) {
        this(DEFAULT_FORMAT, dateBase);
    }
    public DateUtilB(String format, java.util.Date dateBase) {
        this.formatter = DateTimeFormatter.ofPattern(format);
        if (dateBase != null) {
            if (dateBase instanceof java.sql.Date) {
                this.base = ((java.sql.Date) dateBase).toLocalDate();
            } else {
                this.base = dateBase.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            }
        } else {
            this.base = LocalDate.now();
        }
    }

    public static DateUtilB fromString(String format, String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate parsed = LocalDate.parse(dateStr, formatter);
        DateUtilB instance = new DateUtilB(format, null);
        instance.base = parsed;
        return instance;
    }

    public String format(java.util.Date date) {
        if (date == null) return "";
        LocalDate localDate;
        if (date instanceof java.sql.Date) {
            localDate = ((java.sql.Date) date).toLocalDate();
        } else {
            localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        }
        return localDate.format(formatter);
    }

    public LocalDate parse(String dateStr) {
        return LocalDate.parse(dateStr, formatter);
    }
    
    private static class SqlDate {
        private SqlDate() {}

        private static String format(java.sql.Date sqlDate) {
            return sqlDate.toString();
        }
        
        private static java.sql.Date parse(String dateStr) {
            return java.sql.Date.valueOf(dateStr);
        }
    }
    
    private void testConvertDateString() {
        java.util.Date utilDate = java.util.Date.from(LocalDate.of(2010, 11, 17).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
        System.out.println("java.util.Date#toString() : " + utilDate);
        System.out.println("java.time.format : " + format(utilDate));
        try {
            System.out.println("java.time.parse : " + parse(format(utilDate)));
        } catch (Exception pe) {
            pe.printStackTrace();
        }
        
        java.sql.Date sqlDate = SqlDate.parse("2010-11-17");
        System.out.println("java.sql.Date#toString() : " + SqlDate.format(sqlDate));
        System.out.println("java.sql.Date.valueOf() : " + sqlDate);
    }
    
    private void reset() {
    }
    
    public String today() {
        return base.format(formatter);
    }
    public String tomorrow() {
        return daysGo(1);
    }
    public String yesterday() {
        return daysGo(-1);
    }
    private String daysGo(int n) {
        return base.plusDays(n).format(formatter);
    }
    public String nextWeek() {
        return weeksGo(1);
    }
    public String prevWeek() {
        return weeksGo(-1);
    }
    private String weeksGo(int n) {
        return daysGo(7 * n);
    }
    public String nextMonth() {
        return monthsGo(1);
    }
    public String prevMonth() {
        return monthsGo(-1);
    }
    private String monthsGo(int n) {
        return base.plusMonths(n).format(formatter);
    }

    private static void testMarch31() {
        DateUtilB util = new DateUtilB("yyyy.MM.dd", SqlDate.parse("2011-03-31"));
        if (!"2011.03.31".equals(util.today())) System.err.println("today() failed");
        if (!"2011.03.30".equals(util.yesterday())) System.err.println("yesterday() failed");
        if (!"2011.04.01".equals(util.tomorrow())) System.err.println("tomorrow() failed");
        if (!"2011.03.24".equals(util.prevWeek())) System.err.println("prev_week() failed");
        if (!"2011.04.07".equals(util.nextWeek())) System.err.println("next_week() failed");
        if (!"2011.02.28".equals(util.prevMonth())) System.err.println("prev_month() failed");
        if (!"2011.04.30".equals(util.nextMonth())) System.err.println("next_month() failed");
    }
    
    public class Period {
        private String aMonthAgoTomorrow() {
            return base.minusMonths(1).plusDays(1).format(formatter);
        }
        private String aMonthAfterYesterday() {
            return base.plusMonths(1).minusDays(1).format(formatter);
        }
        
        public String[] forAMonthUntilYesterday() {
            return new String[] {prevMonth(), yesterday()};
        }
        public String[] forAMonthUntilToday() {
            return new String[] {aMonthAgoTomorrow(), today()};
        }
        public String[] forAMonthFromToday() {
            return new String[] {today(), aMonthAfterYesterday()};
        }
        public String[] forAMonthFromTomorrow() {
            return new String[] {tomorrow(), nextMonth()};
        }
    }
    
    private void printDays() {
        Period util = new Period();
        String[] period = util.forAMonthUntilYesterday();
        System.out.println("for a month until yesterday=" + period[0] + " ~ " + period[1]);
        period = util.forAMonthUntilToday();
        System.out.println("for a month until today=" + period[0] + " ~ " + period[1]);
        period = util.forAMonthFromToday();
        System.out.println("for a month from today=" + period[0] + " ~ " + period[1]);
        period = util.forAMonthFromTomorrow();
        System.out.println("for a month from tomorrow=" + period[0] + " ~ " + period[1]);
    }
    
    private void testNothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        testConvertDateString();
        testMarch31();
        printDays();
        testNothing();
    }
    
    public static void main(String[] args) {
        DateUtilB worker = new DateUtilB();
        worker.test();
    }
}
package modern.java21.datetime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtilB {
    
    private static final String DEFAULT_FORMAT = "HH:mm:ss";
    
    private DateTimeFormatter formatter;
    private LocalTime base;
    
    public TimeUtilB() {
        this(DEFAULT_FORMAT);
    }
    public TimeUtilB(String format) {
        this(format, null);
    }
    public TimeUtilB(java.util.Date dateBase) {
        this(DEFAULT_FORMAT, dateBase);
    }
    public TimeUtilB(String format, java.util.Date dateBase) {
        this.formatter = DateTimeFormatter.ofPattern(format);
        if (dateBase != null) {
            if (dateBase instanceof java.sql.Time) {
                this.base = ((java.sql.Time) dateBase).toLocalTime();
            } else {
                this.base = dateBase.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
            }
        } else {
            this.base = LocalTime.now();
        }
    }

    public static TimeUtilB fromString(String format, String timeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalTime parsed = LocalTime.parse(timeStr, formatter);
        TimeUtilB instance = new TimeUtilB(format, null);
        instance.base = parsed;
        return instance;
    }

    public String format(java.util.Date time) {
        if (time == null) return "";
        LocalTime localTime;
        if (time instanceof java.sql.Time) {
            localTime = ((java.sql.Time) time).toLocalTime();
        } else {
            localTime = time.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
        }
        return localTime.format(formatter);
    }

    public LocalTime parse(String timeStr) {
        return LocalTime.parse(timeStr, formatter);
    }
    
    private static class SqlTime {
        private SqlTime() {}

        private static String format(java.sql.Time sqlTime) {
            return sqlTime.toString();
        }
        
        private static java.sql.Time parse(String timeStr) {
            return java.sql.Time.valueOf(timeStr);
        }
    }
    
    private void testConvertTimeString() {
        LocalTime localTime = LocalTime.of(15, 26, 48);
        System.out.println("LocalTime#toString() : " + localTime);
        System.out.println("DateTimeFormatter#format() : " + format(java.util.Date.from(localTime.atDate(java.time.LocalDate.of(1970, 1, 1)).atZone(java.time.ZoneId.systemDefault()).toInstant())));
        try {
            System.out.println("DateTimeFormatter#parse() : " + parse(format(java.util.Date.from(localTime.atDate(java.time.LocalDate.of(1970, 1, 1)).atZone(java.time.ZoneId.systemDefault()).toInstant()))));
        } catch (Exception pe) {
            pe.printStackTrace();
        }
        
        java.sql.Time sqlTime = SqlTime.parse("15:26:48");
        System.out.println("java.sql.Time#toString() : " + SqlTime.format(sqlTime));
        System.out.println("java.sql.Time.valueOf() : " + sqlTime);
    }
    
    private void reset() {
    }
    
    public String now() {
        return base.format(formatter);
    }
    private String minutesGo(int n) {
        return base.plusMinutes(n).format(formatter);
    }
    private String hoursGo(int n) {
        return base.plusHours(n).format(formatter);
    }

    private static void testTwelveForty() {
        TimeUtilB util = new TimeUtilB("HH.mm.ss", SqlTime.parse("12:40:05"));
        if (!"12.40.05".equals(util.now())) System.err.println("now() failed");
        if (!"12.41.05".equals(util.minutesGo(1))) System.err.println("minutes_go(1) failed");
        if (!"12.39.05".equals(util.minutesGo(-1))) System.err.println("minutes_go(-1) failed");
        if (!"12.55.05".equals(util.minutesGo(15))) System.err.println("minutes_go(15) failed");
        if (!"12.25.05".equals(util.minutesGo(-15))) System.err.println("minutes_go(-15) failed");
        if (!"13.10.05".equals(util.minutesGo(30))) System.err.println("minutes_go(30) failed");
        if (!"12.10.05".equals(util.minutesGo(-30))) System.err.println("minutes_go(-30) failed");
        if (!"13.40.05".equals(util.hoursGo(1))) System.err.println("hours_go(1) failed");
        if (!"11.40.05".equals(util.hoursGo(-1))) System.err.println("hours_go(-1) failed");
        if (!"18.40.05".equals(util.hoursGo(6))) System.err.println("hours_go(6) failed");
        if (!"06.40.05".equals(util.hoursGo(-6))) System.err.println("hours_go(-6) failed");
        if (!"00.40.05".equals(util.hoursGo(12))) System.err.println("hours_go(12) failed");
        if (!"00.40.05".equals(util.hoursGo(-12))) System.err.println("hours_go(-12) failed");
    }
    
    private void testNothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        testConvertTimeString();
        testTwelveForty();
        testNothing();
    }
    
    public static void main(String[] args) {
        TimeUtilB worker = new TimeUtilB();
        worker.test();
    }
}
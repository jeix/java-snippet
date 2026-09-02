package modern.java21.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateStringTest {
    
    private void testConvertDateString() {
        System.out.println("====");
        
        LocalDateTime utilDate = LocalDateTime.now();
        System.out.println("LocalDateTime#toString() : " + utilDate);
        
        java.sql.Date sqlDate = null;
        String s = null;
        
        utilDate = LocalDateTime.now();
        s = dateTimeFormat(utilDate);
        System.out.println("DateTimeFormatter#format() : " + s);
        utilDate = dateTimeParse(s);
        System.out.println("DateTimeFormatter#parse() : " + utilDate);
        
        sqlDate = newSqlDate();
        System.out.println("java.sql.Date#toString() : " + sqlDate);
        s = sqlDate.toString();
        sqlDate = java.sql.Date.valueOf(s);
        System.out.println("java.sql.Date.valueOf() : " + sqlDate);
    }
    
    private java.sql.Date newSqlDate() {
        return newSqlDate(LocalDate.now());
    }
    
    private java.sql.Date newSqlDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }
    
    private String dateTimeFormat(LocalDateTime dt) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dt.format(df);
    }
    
    private LocalDateTime dateTimeParse(String s) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(s, df).atStartOfDay();
        } catch (Exception pe) {
            pe.printStackTrace();
            return null;
        }
    }
    
    private void testGetDateString() {
        System.out.println("====");
        System.out.println("today() : " + today());
        System.out.println("tomorrow() : " + tomorrow());
        System.out.println("yesterday() : " + yesterday());
        System.out.println("next_week() : " + nextWeek());
        System.out.println("prev_week() : " + prevWeek());
        System.out.println("weeks_go(2) : " + weeksGo(2));
        System.out.println("weeks_go(-2) : " + weeksGo(-2));
        System.out.println("next_month() : " + nextMonth());
        System.out.println("prev_month() : " + prevMonth());
        System.out.println("months_go(2) : " + monthsGo(2));
        System.out.println("months_go(-2) : " + monthsGo(-2));
    }
    
    public String today() {
        return LocalDate.now().toString();
    }
    public String tomorrow() {
        return daysGo(1);
    }
    public String yesterday() {
        return daysGo(-1);
    }
    public String nextWeek() {
        return weeksGo(1);
    }
    public String prevWeek() {
        return weeksGo(-1);
    }
    public String nextMonth() {
        return monthsGo(1);
    }
    public String prevMonth() {
        return monthsGo(-1);
    }
    private String daysGo(int n) {
        return LocalDate.now().plusDays(n).toString();
    }
    private String weeksGo(int n) {
        return daysGo(7 * n);
    }
    private String monthsGo(int n) {
        return LocalDate.now().plusMonths(n).toString();
    }
    
    private void testNothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        testConvertDateString();
        testGetDateString();
        testNothing();
    }
    
    public static void main(String[] args) {
        DateStringTest worker = new DateStringTest();
        worker.test();
    }
}
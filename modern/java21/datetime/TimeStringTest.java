package modern.java21.datetime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeStringTest {
    
    private void testConvertTimeString() {
        System.out.println("====");
        
        java.sql.Time sqlTime = null;
        String s = null;
        
        LocalTime localTime = LocalTime.now();
        s = dateTimeFormat(localTime);
        System.out.println("DateTimeFormatter#format() : " + s);
        localTime = dateTimeParse(s);
        System.out.println("DateTimeFormatter#parse() : " + localTime);
        
        sqlTime = newSqlTime();
        System.out.println("java.sql.Time#toString() : " + sqlTime);
        s = sqlTime.toString();
        sqlTime = java.sql.Time.valueOf(s);
        System.out.println("java.sql.Time.valueOf() : " + sqlTime);
    }
    
    private java.sql.Time newSqlTime() {
        return newSqlTime(LocalTime.now());
    }
    
    private java.sql.Time newSqlTime(LocalTime localTime) {
        return java.sql.Time.valueOf(localTime);
    }
    
    private String dateTimeFormat(LocalTime dt) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
        return dt.format(df);
    }
    
    private LocalTime dateTimeParse(String s) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            return LocalTime.parse(s, df);
        } catch (Exception pe) {
            pe.printStackTrace();
            return null;
        }
    }
    
    private void testGetTimeString() {
        System.out.println("====");
        System.out.println("now() : " + now());
        System.out.println("minutes_go(1) : " + minutesGo(1));
        System.out.println("minutes_go(-1) : " + minutesGo(-1));
        System.out.println("minutes_go(15) : " + minutesGo(15));
        System.out.println("minutes_go(-15) : " + minutesGo(-15));
        System.out.println("minutes_go(30) : " + minutesGo(30));
        System.out.println("minutes_go(-30) : " + minutesGo(-30));
        System.out.println("hours_go(1) : " + hoursGo(1));
        System.out.println("hours_go(-1) : " + hoursGo(-1));
        System.out.println("hours_go(6) : " + hoursGo(6));
        System.out.println("hours_go(-6) : " + hoursGo(-6));
        System.out.println("hours_go(12) : " + hoursGo(12));
        System.out.println("hours_go(-12) : " + hoursGo(-12));
    }
    
    private String now() {
        return LocalTime.now().toString();
    }
    private String minutesGo(int n) {
        return LocalTime.now().plusMinutes(n).toString();
    }
    private String hoursGo(int n) {
        return LocalTime.now().plusHours(n).toString();
    }
    
    private void testNothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        testConvertTimeString();
        testGetTimeString();
        testNothing();
    }
    
    public static void main(String[] args) {
        TimeStringTest worker = new TimeStringTest();
        worker.test();
    }
}
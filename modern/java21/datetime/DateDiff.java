package modern.java21.datetime;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateDiff {
    
    private LocalDate date;
    
    private DateDiff() {}
    
    public static DateDiff base(LocalDate date) {
        DateDiff diff = new DateDiff();
        diff.date = date;
        return diff;
    }
    
    public boolean isEQ(LocalDate date) {
        return this.date.equals(date);
    }
    public boolean isNE(LocalDate date) {
        return !this.date.equals(date);
    }
    public boolean isLE(LocalDate date) {
        return this.date.isBefore(date) || this.date.equals(date);
    }
    public boolean isLT(LocalDate date) {
        return this.date.isBefore(date);
    }
    public boolean isGE(LocalDate date) {
        return this.date.isAfter(date) || this.date.equals(date);
    }
    public boolean isGT(LocalDate date) {
        return this.date.isAfter(date);
    }
    
    public long daysBetween(LocalDate date) {
        return ChronoUnit.DAYS.between(this.date, date);
    }
    
    public static void compareDates(LocalDate date1, LocalDate date2) {
        DateDiff diff = DateDiff.base(date1);
        if (diff.isEQ(date2)) System.out.println(date1 + " == " + date2);
        if (diff.isNE(date2)) System.out.println(date1 + " != " + date2);
        if (diff.isLE(date2)) System.out.println(date1 + " <= " + date2);
        if (diff.isLT(date2)) System.out.println(date1 + " < " + date2);
        if (diff.isGE(date2)) System.out.println(date1 + " >= " + date2);
        if (diff.isGT(date2)) System.out.println(date1 + " > " + date2);
        System.out.println("Days between: " + diff.daysBetween(date2));
    }
    
    public static void main(String[] args) {
        LocalDate date0 = LocalDate.of(2013, 4, 21);
        LocalDate date1 = LocalDate.of(2013, 4, 20);
        LocalDate date2 = LocalDate.of(2013, 4, 21);
        LocalDate date3 = LocalDate.of(2013, 4, 22);
        System.out.println("------------");
        compareDates(date0, date1);
        System.out.println("------------");
        compareDates(date0, date2);
        System.out.println("------------");
        compareDates(date0, date3);
    }
}
package datetime;

import java.sql.Date;

public class DateDiff {
	
	private Date date;
	
	private DateDiff() {}
	
	public static DateDiff base(Date date) {
		DateDiff diff = new DateDiff();
		diff.date = date;
		return diff;
	}
	
	public boolean isEQ(Date date) {
		return (this.date.compareTo(date) == 0);
	}
	public boolean isNE(Date date) {
		return (this.date.compareTo(date) != 0);
	}
	public boolean isLE(Date date) {
		return (this.date.compareTo(date) <= 0);
	}
	public boolean isLT(Date date) {
		return (this.date.compareTo(date) < 0);
	}
	public boolean isGE(Date date) {
		return (this.date.compareTo(date) >= 0);
	}
	public boolean isGT(Date date) {
		return (this.date.compareTo(date) > 0);
	}
	
	public static void compareDates(Date date1, Date date2) {
		if (DateDiff.base(date1).isEQ(date2)) System.out.println(date1.toString() + " == " + date2.toString());
		if (DateDiff.base(date1).isNE(date2)) System.out.println(date1.toString() + " != " + date2.toString());
		if (DateDiff.base(date1).isLE(date2)) System.out.println(date1.toString() + " <= " + date2.toString());
		if (DateDiff.base(date1).isLT(date2)) System.out.println(date1.toString() + " < " + date2.toString());
		if (DateDiff.base(date1).isGE(date2)) System.out.println(date1.toString() + " >= " + date2.toString());
		if (DateDiff.base(date1).isGT(date2)) System.out.println(date1.toString() + " > " + date2.toString());
	}
	
	public static void main(String[] args) {
		Date date0 = Date.valueOf("2013-04-21");
		Date date1 = Date.valueOf("2013-04-20");
		Date date2 = Date.valueOf("2013-04-21");
		Date date3 = Date.valueOf("2013-04-22");
		System.out.println("------------");
		compareDates(date0, date1);
		System.out.println("------------");
		compareDates(date0, date2);
		System.out.println("------------");
		compareDates(date0, date3);
		System.out.println("------------");
	}
}
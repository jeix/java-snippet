package modern.java21.datetime;

import java.time.LocalDate;

public class DateDiff {

	private final LocalDate date;

	private DateDiff(LocalDate date) {
		this.date = date;
	}

	public static DateDiff base(LocalDate date) {
		return new DateDiff(date);
	}

	public boolean isEQ(LocalDate date) {
		return this.date.isEqual(date);
	}
	public boolean isNE(LocalDate date) {
		return ! this.date.isEqual(date);
	}
	public boolean isLE(LocalDate date) {
		return ! this.date.isAfter(date);
	}
	public boolean isLT(LocalDate date) {
		return this.date.isBefore(date);
	}
	public boolean isGE(LocalDate date) {
		return ! this.date.isBefore(date);
	}
	public boolean isGT(LocalDate date) {
		return this.date.isAfter(date);
	}

	public static void compareDates(LocalDate date1, LocalDate date2) {
		if (DateDiff.base(date1).isEQ(date2)) System.out.println(date1 + " == " + date2);
		if (DateDiff.base(date1).isNE(date2)) System.out.println(date1 + " != " + date2);
		if (DateDiff.base(date1).isLE(date2)) System.out.println(date1 + " <= " + date2);
		if (DateDiff.base(date1).isLT(date2)) System.out.println(date1 + " < " + date2);
		if (DateDiff.base(date1).isGE(date2)) System.out.println(date1 + " >= " + date2);
		if (DateDiff.base(date1).isGT(date2)) System.out.println(date1 + " > " + date2);
	}

	public static void main(String[] args) {
		LocalDate date0 = LocalDate.parse("2013-04-21");
		LocalDate date1 = LocalDate.parse("2013-04-20");
		LocalDate date2 = LocalDate.parse("2013-04-21");
		LocalDate date3 = LocalDate.parse("2013-04-22");
		System.out.println("------------");
		compareDates(date0, date1);
		System.out.println("------------");
		compareDates(date0, date2);
		System.out.println("------------");
		compareDates(date0, date3);
		System.out.println("------------");
	}
}

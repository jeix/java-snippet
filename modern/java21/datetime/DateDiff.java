package modern.java21.datetime;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public record DateDiff(LocalDate date) {
	public DateDiff {
		Objects.requireNonNull(date, "date");
	}

	public static DateDiff base(LocalDate date) {
		return new DateDiff(date);
	}

	public boolean isEQ(LocalDate other) {
		return date.isEqual(other);
	}

	public boolean isNE(LocalDate other) {
		return !isEQ(other);
	}

	public boolean isLE(LocalDate other) {
		return !date.isAfter(other);
	}

	public boolean isLT(LocalDate other) {
		return date.isBefore(other);
	}

	public boolean isGE(LocalDate other) {
		return !date.isBefore(other);
	}

	public boolean isGT(LocalDate other) {
		return date.isAfter(other);
	}

	public long daysUntil(LocalDate other) {
		return ChronoUnit.DAYS.between(date, other);
	}

	public static void compareDates(LocalDate date1, LocalDate date2) {
		var diff = DateDiff.base(date1);
		if (diff.isEQ(date2)) System.out.println(date1 + " == " + date2);
		if (diff.isNE(date2)) System.out.println(date1 + " != " + date2);
		if (diff.isLE(date2)) System.out.println(date1 + " <= " + date2);
		if (diff.isLT(date2)) System.out.println(date1 + " < " + date2);
		if (diff.isGE(date2)) System.out.println(date1 + " >= " + date2);
		if (diff.isGT(date2)) System.out.println(date1 + " > " + date2);
	}

	public static void main(String[] args) {
		var date0 = LocalDate.parse("2013-04-21");
		var date1 = LocalDate.parse("2013-04-20");
		var date2 = LocalDate.parse("2013-04-21");
		var date3 = LocalDate.parse("2013-04-22");
		System.out.println("------------");
		compareDates(date0, date1);
		System.out.println("------------");
		compareDates(date0, date2);
		System.out.println("------------");
		compareDates(date0, date3);
		System.out.println("------------");
	}
}

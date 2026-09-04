package modern.java21.number;

import java.text.NumberFormat;

public class NumberFormatDemo {

	public static void main(String[] args) {
		NumberFormat nf = NumberFormat.getInstance();

		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		System.out.println(nf.format(Double.parseDouble("0.")));

		nf.setMaximumFractionDigits(1);
		nf.setMinimumFractionDigits(1);
		System.out.println(nf.format(Double.parseDouble(".0")));

		nf.setMaximumFractionDigits(0);
		nf.setMinimumFractionDigits(0);
		System.out.println(nf.format(Double.parseDouble("0.")));

		System.out.println(":wq");
	}
}
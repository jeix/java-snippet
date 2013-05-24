package number;

import java.text.NumberFormat;

import static test.Expect.expect;

public class NumberFormatDemo {
	
	public static void main(String[] args) {
		NumberFormat nf = NumberFormat.getInstance();
		
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		expect("0.00", nf.format(Double.parseDouble("0.")));
		
		nf.setMaximumFractionDigits(1);
		nf.setMinimumFractionDigits(1);
		expect("0.0", nf.format(Double.parseDouble(".0")));
		
		nf.setMaximumFractionDigits(0);
		nf.setMinimumFractionDigits(0);
		expect("0", nf.format(Double.parseDouble("0.")));
		
		System.out.println(":wq");
	}
}
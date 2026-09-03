package modern.java21.number;

import java.text.NumberFormat;

import static modern.java21.test.Expect.expect;

// 원형 유지: NumberFormat의 최대/최소 소수 자릿수 설정이 실제 포맷 결과에 어떻게
// 반영되는지 확인하는 게 주제라 바꿀 대상이 없다.

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
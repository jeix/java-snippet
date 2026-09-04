package modern.java21.number;

import java.math.BigDecimal;

public class BigDecimalDemo {

	public static void main(String[] args) {
		System.out.println("BigDecimal(\"1.266E7\")");
		var b = new BigDecimal("1.266E7");
		System.out.print("BigDecimal#toString() : ");
		System.out.println(b.toString());
		System.out.print("BigDecimal#toPlainString() : ");
		System.out.println(b.toPlainString());
		System.out.print("BigDecimal(BigDecimal#toPlainString()) : ");
		System.out.println(new BigDecimal(b.toPlainString()).toString());

		System.out.print("BigDecimal#movePointLeft(1) : ");
		System.out.println(b.movePointLeft(1).toString());
		System.out.print("BigDecimal#movePointLeft(2) : ");
		System.out.println(b.movePointLeft(2).toString());
		System.out.print("BigDecimal#movePointRight(1) : ");
		System.out.println(b.movePointRight(1).toString());
		System.out.print("BigDecimal#movePointRight(2) : ");
		System.out.println(b.movePointRight(2).toString());
		System.out.print("BigDecimal#movePointLeft(1)#movePointRight(1) : ");
		System.out.println(b.movePointLeft(1).movePointRight(1).toString());
		System.out.print("BigDecimal#movePointRight(1)#movePointLeft(1) : ");
		System.out.println(b.movePointRight(1).movePointLeft(1).toString());

		System.out.print("BigDecimal#longValue()/10000 : ");
		System.out.println(String.valueOf(b.longValue() / 10000));
		System.out.print("BigDecimal#divide(BigDecimal(10000)) : ");
		System.out.println(b.divide(new BigDecimal(10000)).toString());

		System.out.println(new BigDecimal("1.266E7").toString());
		System.out.println(new BigDecimal("1.266E7").precision());
		System.out.println(new BigDecimal("1.266E7").scale());
		System.out.println(new BigDecimal("1.266E7").signum());
		System.out.println(new BigDecimal("12.66E6").toString());
		System.out.println(new BigDecimal("12.66E6").precision());
		System.out.println(new BigDecimal("12.66E6").scale());
		System.out.println(new BigDecimal("12.66E6").signum());
		System.out.println(new BigDecimal("0.1266E8").toString());
		System.out.println(new BigDecimal("0.1266E8").precision());
		System.out.println(new BigDecimal("0.1266E8").scale());
		System.out.println(new BigDecimal("0.1266E8").signum());
	}
}
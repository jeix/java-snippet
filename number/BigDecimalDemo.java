package number;

import java.math.BigDecimal;

public class BigDecimalDemo {

	public static void main(String[] args) {
		System.out.println("BigDecimal(\"1.266E7\")");
		BigDecimal b = new BigDecimal("1.266E7");
		System.out.print("BigDecimal#toString() : ");
		System.out.println(b.toString()); // -> 1.266E+7
		System.out.print("BigDecimal#toPlainString() : ");
		System.out.println(b.toPlainString()); // -> 12660000
		System.out.print("BigDecimal(BigDecimal#toPlainString()) : ");
		System.out.println(new BigDecimal(b.toPlainString()).toString()); // -> 12660000
		
		System.out.print("BigDecimal#movePointLeft(1) : ");
		System.out.println(b.movePointLeft(1).toString()); // -> 1266000
		System.out.print("BigDecimal#movePointLeft(2) : ");
		System.out.println(b.movePointLeft(2).toString()); // -> 126600
		System.out.print("BigDecimal#movePointRight(1) : ");
		System.out.println(b.movePointRight(1).toString()); // -> 126600000
		System.out.print("BigDecimal#movePointRight(2) : ");
		System.out.println(b.movePointRight(2).toString()); // -> 1266000000
		System.out.print("BigDecimal#movePointLeft(1)#movePointRight(1) : ");
		System.out.println(b.movePointLeft(1).movePointRight(1).toString()); // -> 12660000
		System.out.print("BigDecimal#movePointRight(1)#movePointLeft(1) : ");
		System.out.println(b.movePointRight(1).movePointLeft(1).toString()); // -> 12660000.0
		
		System.out.print("BigDecimal#longValue()/10000 : ");
		System.out.println(String.valueOf(b.longValue()/10000)); // -> 1266
		System.out.print("BigDecimal#divide(BigDecimal(10000)) : ");
		System.out.println(b.divide(new BigDecimal(10000)).toString()); // -> 1266
		
		System.out.println(new BigDecimal("1.266E7").toString()); // -> 1.266E+7
		System.out.println(new BigDecimal("1.266E7").precision()); // -> 4
		System.out.println(new BigDecimal("1.266E7").scale()); // -> -4
		System.out.println(new BigDecimal("1.266E7").signum()); // -> 1
		System.out.println(new BigDecimal("12.66E6").toString()); // -> 1.266E+7
		System.out.println(new BigDecimal("12.66E6").precision()); // -> 4
		System.out.println(new BigDecimal("12.66E6").scale()); // -> -4
		System.out.println(new BigDecimal("12.66E6").signum()); // -> 1
		System.out.println(new BigDecimal("0.1266E8").toString()); // -> 1.266E+7
		System.out.println(new BigDecimal("0.1266E8").precision()); // -> 4
		System.out.println(new BigDecimal("0.1266E8").scale()); // -> -4
		System.out.println(new BigDecimal("0.1266E8").signum()); // -> 1
		
	}
}

package test;

import java.math.BigDecimal;

public class Expect {
	public static void expect(Object expected, Object result) {
		if (! expected.equals(result)) System.out.println("[" + expected.toString() + "] expected BUT [" + result.toString() + "]");
	}
	public static void expect(String expected, String result) {
		if (! expected.equals(result)) System.out.println("[" + expected + "] expected BUT [" + result + "]");
	}
	public static void expect(BigDecimal expected, BigDecimal result) {
		if (! expected.equals(result)) System.out.println("[" + result.toString() + "] expected BUT [" + result.toString() + "]");
	}
	public static void expect(int expected, int result) {
		if (expected != result) System.out.println("[" + String.valueOf(expected) + "] expected BUT [" + String.valueOf(result) + "]");
	}
	public static void expect(long expected, long result) {
		if (expected != result) System.out.println("[" + String.valueOf(expected) + "] expected BUT [" + String.valueOf(result) + "]");
	}
	public static void expect(float expected, float result) {
		if (expected != result) System.out.println("[" + String.valueOf(expected) + "] expected BUT [" + String.valueOf(result) + "]");
	}
	public static void expect(double expected, double result) {
		if (expected != result) System.out.println("[" + String.valueOf(expected) + "] expected BUT [" + String.valueOf(result) + "]");
	}
	public static void expect(boolean expected, boolean result) {
		if (expected != result) System.out.println("[" + String.valueOf(expected) + "] expected BUT [" + String.valueOf(result) + "]");
	}
	public static void expect(Class<?> except) {
		System.out.println("[" + except.getCanonicalName() + "] expected BUT not thrown");
	}
}
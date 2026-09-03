package modern.java21.lang.enum_;

// 원형 유지: 상수별 클래스 본문(constant-specific method body)으로 switch 분기를 없애는
// 단계를 보여주는 시리즈의 마지막 단계다. toString()의 switch만 남아 있다.
// Java 21 판: lang/enum_/EnumDemo5.java

public class EnumDemo4 {
	
	enum Operation {
		PLUS   { public double eval(double x, double y) { return x + y; } },
		MINUS  { public double eval(double x, double y) { return x - y; } },
		TIMES  { public double eval(double x, double y) { return x * y; } },
		DIVIDE { public double eval(double x, double y) { return x / y; } };
		
		abstract double eval(double x, double y);
		
		public String toString() {
			switch (this) {
				case PLUS:   return "+";
				case MINUS:  return "-";
				case TIMES:  return "*";
				case DIVIDE: return "/";
			}
			return "";
		}
	}
	
	public static void main(String[] args) {
		double x = 6.0;
		double y = 9.0;
		for (Operation op : Operation.values()) {
			System.out.printf("%.1f %s %.1f = %.2f%n", x, op, y, op.eval(x, y));
		}
	}
}

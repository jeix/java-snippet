package modern.java21.lang.enum_;

// EnumDemo0~4 시리즈의 Java 21 판. EnumDemo4의 상수별 클래스 본문은 그대로 두고,
// 마지막까지 switch 문으로 남아 있던 toString()을 switch 식(-> , JEP 361)으로 바꿨다.
// 모든 상수를 다루면 컴파일러가 그 자체로 완전하다는 걸 알아서, EnumDemo3/4에 있던
// "그 외의 경우" return이나 break가 필요 없다.
public class EnumDemo5 {

	enum Operation {
		PLUS   { public double eval(double x, double y) { return x + y; } },
		MINUS  { public double eval(double x, double y) { return x - y; } },
		TIMES  { public double eval(double x, double y) { return x * y; } },
		DIVIDE { public double eval(double x, double y) { return x / y; } };

		abstract double eval(double x, double y);

		public String toString() {
			return switch (this) {
				case PLUS   -> "+";
				case MINUS  -> "-";
				case TIMES  -> "*";
				case DIVIDE -> "/";
			};
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

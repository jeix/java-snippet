package lang.enum_;

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

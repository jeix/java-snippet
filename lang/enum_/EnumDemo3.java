package lang.enum_;

public class EnumDemo3 {
	
	enum Operation {
		PLUS, MINUS, TIMES, DIVIDE;
		
		public double eval(double x, double y) {
			switch (this) {
				case PLUS:   return x + y;
				case MINUS:  return x - y;
				case TIMES:  return x * y;
				case DIVIDE: return x / y;
			}
			throw new AssertionError("Unknown op: " + this);
		}
		
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

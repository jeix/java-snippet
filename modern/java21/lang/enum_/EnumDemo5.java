package modern.java21.lang.enum_;

public class EnumDemo5 {

	enum Operation {
		PLUS("+"),
		MINUS("-"),
		TIMES("*"),
		DIVIDE("/");

		private final String symbol;

		Operation(String symbol) {
			this.symbol = symbol;
		}

		public double eval(double x, double y) {
			return switch (this) {
				case PLUS -> x + y;
				case MINUS -> x - y;
				case TIMES -> x * y;
				case DIVIDE -> x / y;
			};
		}

		@Override
		public String toString() {
			return symbol;
		}
	}

	public static void main(String[] args) {
		double x = 6.0;
		double y = 9.0;
		for (Operation operation : Operation.values()) {
			System.out.printf("%.1f %s %.1f = %.2f%n", x, operation, y, operation.eval(x, y));
		}
	}
}

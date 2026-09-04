package modern.java21.ood.delegation;

import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class TypeSafeDelegation {

	static class Config {
		public static final String f = "foo";

		public static String m(String p, String q) {
			return p + q;
		}
	}

	static class Worker {
		private final Supplier<String> field;
		private final BinaryOperator<String> method;

		Worker(Supplier<String> field, BinaryOperator<String> method) {
			this.field = field;
			this.method = method;
		}

		public void work() {
			var x = field.get();
			System.out.println(x);
			var y = "bar";
			var z = method.apply(x, y);
			System.out.println(z);
		}
	}

	public static void main(String[] args) {
		var worker = new Worker(() -> Config.f, Config::m);
		worker.work();
	}
}

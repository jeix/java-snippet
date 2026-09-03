package modern.java21.ood.delegation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class InstancelessDelegation {

	static final class Config {
		public static final String f = "foo";

		public static String m(String p, String q) {
			return p + q;
		}
	}

	static final class Worker {
		private final Class<?> c;

		Worker(Class<?> c) {
			this.c = Objects.requireNonNull(c);
		}

		void work() {
			var x = getField("f");
			System.out.println(x);
			var y = "bar";
			var z = callMethod("m", x, y);
			System.out.println(z);
		}

		@SuppressWarnings("unchecked")
		private String getField(String name) {
			try {
				var field = c.getField(name);
				return (String) field.get(null);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				return null;
			}
		}

		@SuppressWarnings("unchecked")
		private String callMethod(String name, String... params) {
			try {
				var paramTypes = new Class<?>[params.length];
				for (var i = 0; i < params.length; i++) {
					paramTypes[i] = params[i].getClass();
				}
				var method = c.getMethod(name, paramTypes);
				return (String) method.invoke(null, (Object[]) params);
			} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NullPointerException e) {
				return null;
			}
		}
	}

	public static void main(String[] args) {
		var worker = new Worker(Config.class);
		worker.work();
	}
}
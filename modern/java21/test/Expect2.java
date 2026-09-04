package modern.java21.test;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Expect2 {

	@FunctionalInterface
	public interface ThrowingRunnable {
		void run() throws Exception;
	}

	public static Expect2 expect(Object val) {
		var expectation = new Expect2(val, false);
		expectation.not = new Expect2(val, true);
		return expectation;
	}

	private final Object val;
	private boolean negative;

	// public -- "Play at Your Own Risk"
	public Expect2 not;

	private Expect2(Object val, boolean negative) {
		this.val = val;
		this.negative = negative;
	}

	public Expect2 nay() {
		negative = !negative;
		return this;
	}

	public void to_equal(Object x) {
		if (failed(Objects.equals(val, x))) {
			System.out.println("[" + format(val) + "] != [" + format(x) + "]");
		}
	}

	public void to_be(Object x) {
		if (failed(val == x)) {
			System.out.println("[" + format(val) + "] !== [" + format(x) + "]");
		}
	}

	public void to_match(String regex) {
		to_match(Pattern.compile(regex));
	}

	public void to_match(Pattern pattern) {
		boolean matches = val instanceof CharSequence text && pattern.matcher(text).find();
		report(matches, "match", pattern);
	}

	public void to_be_null() {
		report(val == null, "be null", null);
	}

	public void to_be_truthy() {
		report(truthy(val), "be truthy", null);
	}

	public void to_be_falsy() {
		report(!truthy(val), "be falsy", null);
	}

	public void to_contain(Object x) {
		boolean contains = switch (val) {
			case CharSequence text when x instanceof CharSequence part -> text.toString().contains(part);
			case Iterable<?> items -> iterable_contains(items, x);
			case null -> false;
			default -> val.getClass().isArray() && array_contains(val, x);
		};
		report(contains, "contain", x);
	}

	public void to_be_less_than(Object x) {
		report(compare(val, x) < 0, "be less than", x);
	}

	public void to_be_greater_than(Object x) {
		report(compare(val, x) > 0, "be greater than", x);
	}

	public void to_be_close_to(Number x) {
		to_be_close_to(x, 1.0e-9);
	}

	public void to_be_close_to(Number x, double tolerance) {
		if (tolerance < 0.0) throw new IllegalArgumentException("tolerance must not be negative");
		boolean close = false;
		if (val instanceof Number number && x != null) {
			var actual = number.doubleValue();
			var expected = x.doubleValue();
			close = Double.compare(actual, expected) == 0 || Math.abs(actual - expected) <= tolerance;
		}
		report(close, "be close to", x);
	}

	public void to_throw() {
		to_throw(Throwable.class);
	}

	public void to_throw(Class<? extends Throwable> expected) {
		if (!(val instanceof ThrowingRunnable action)) {
			throw new IllegalStateException("to_throw requires a ThrowingRunnable");
		}

		Throwable thrown = null;
		try {
			action.run();
		} catch (Throwable exception) {
			thrown = exception;
		}
		report(thrown != null && expected.isInstance(thrown), "throw", expected.getCanonicalName());
	}

	private static boolean iterable_contains(Iterable<?> items, Object expected) {
		for (Object item : items) {
			if (Objects.equals(item, expected)) return true;
		}
		return false;
	}

	private static boolean array_contains(Object array, Object expected) {
		for (int i = 0; i < Array.getLength(array); i++) {
			if (Objects.equals(Array.get(array, i), expected)) return true;
		}
		return false;
	}

	private static boolean truthy(Object value) {
		return switch (value) {
			case null -> false;
			case Boolean bool -> bool;
			case BigDecimal number -> number.compareTo(BigDecimal.ZERO) != 0;
			case Number number -> number.doubleValue() != 0.0;
			case Character character -> character != '\0';
			case CharSequence text -> !text.isEmpty();
			case Collection<?> collection -> !collection.isEmpty();
			case Map<?, ?> map -> !map.isEmpty();
			default -> !value.getClass().isArray() || Array.getLength(value) > 0;
		};
	}

	private static int compare(Object left, Object right) {
		if (left instanceof Number left_number && right instanceof Number right_number) {
			return new BigDecimal(left_number.toString()).compareTo(new BigDecimal(right_number.toString()));
		}
		if (left == null || right == null || !left.getClass().isInstance(right)) {
			throw new IllegalArgumentException("Values are not mutually comparable");
		}
		return compare_same_type(left, right);
	}

	@SuppressWarnings("unchecked")
	private static <T> int compare_same_type(T left, T right) {
		if (left instanceof Comparable<?> comparable) {
			return ((Comparable<T>) comparable).compareTo(right);
		}
		throw new IllegalArgumentException("Value is not comparable: " + left.getClass().getName());
	}

	private void report(boolean result, String matcher, Object expected) {
		if (failed(result)) {
			System.out.println("[" + format(val) + "] failed to " + (negative ? "not " : "")
					+ matcher + (expected == null ? "" : " [" + format(expected) + "]"));
		}
	}

	private static String format(Object value) {
		if (value == null || !value.getClass().isArray()) return String.valueOf(value);
		var length = Array.getLength(value);
		var values = new Object[length];
		for (int i = 0; i < length; i++) values[i] = Array.get(value, i);
		return java.util.Arrays.toString(values);
	}

	private boolean failed(boolean result) {
		return result == negative;
	}
}

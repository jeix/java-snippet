package modern.java21.test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.regex.Pattern;

public class Expect2 {

	public static Expect2 expect(Object val) {
		Expect2 _exp = new Expect2(val);
		_exp.not = negative(val);
		return _exp;
	}

	private static Expect2 negative(Object val) {
		Expect2 _exp = new Expect2(val);
		_exp.negative = true;
		return _exp;
	}

	private Object val;
	private boolean negative;

	// public -- "Play at Your Own Risk"
	public Expect2 not;

	private Expect2(Object val) {
		this.val = val;
	}

	public Expect2 nay() {
		negative = ! negative;
		return this;
	}

	public void to_equal(Object x) {
		boolean equality = false;
		if (val == x) equality = true;
		else if (val == null && x != null) equality = false;
		else if (val.equals(x)) equality = true;

		if (failed(equality)) {
			System.out.println("[" + val + "] != [" + x + "]");
		}
	}

	public void to_be(Object x) {
		if (failed(val == x)) {
			System.out.println("[" + val + "] !== [" + x + "]");
		}
	}

	public void to_match(String regex) {
		// String#matches()는 전체 문자열이 일치해야 해서(암묵적 앵커링) 부분 일치를 보려면
		// Matcher#find()를 써야 한다.
		boolean matched = (val != null) && Pattern.compile(regex).matcher(val.toString()).find();
		if (failed(matched)) {
			System.out.println("[" + val + "] not match [" + regex + "]");
		}
	}

	public void to_be_null() {
		if (failed(val == null)) {
			System.out.println("[" + val + "] is not null");
		}
	}

	public void to_be_truthy() {
		if (failed(truthy(val))) {
			System.out.println("[" + val + "] is not truthy");
		}
	}

	public void to_be_falsy() {
		if (failed(! truthy(val))) {
			System.out.println("[" + val + "] is not falsy");
		}
	}

	private static boolean truthy(Object val) {
		return switch (val) {
			case null -> false;
			case Boolean b -> b;
			case String s -> ! s.isEmpty();
			case Integer i -> i != 0;
			case Long l -> l != 0L;
			case Float f -> f != 0.0f;
			case Double d -> d != 0.0;
			case BigDecimal bd -> ! BigDecimal.ZERO.equals(bd);
			default -> true;
		};
	}

	public void to_contain(Object x) {
		boolean contain = false;
		for (Object v : (Object[]) val) {
			if (v == x) contain = true;
			else if (v == null && x != null) contain = false;
			else if (v.equals(x)) contain = true;

			if (contain) break;
		}

		if (failed(contain)) {
			System.out.println("[" + val + "] not contain [" + x + "]");
		}
	}

	@SuppressWarnings("unchecked")
	public void to_be_less_than(Object x) {
		boolean less = (val instanceof Comparable) && ((Comparable<Object>) val).compareTo(x) < 0;
		if (failed(less)) {
			System.out.println("[" + val + "] not less than [" + x + "]");
		}
	}

	@SuppressWarnings("unchecked")
	public void to_be_greater_than(Object x) {
		boolean greater = (val instanceof Comparable) && ((Comparable<Object>) val).compareTo(x) > 0;
		if (failed(greater)) {
			System.out.println("[" + val + "] not greater than [" + x + "]");
		}
	}

	public void to_be_close_to(double x, int precision) {
		boolean close = (val instanceof Number)
				&& Math.abs(((Number) val).doubleValue() - x) < Math.pow(10, -precision) / 2;
		if (failed(close)) {
			System.out.println("[" + val + "] not close to [" + x + "] within " + precision + " decimal place(s)");
		}
	}

	// val을 Runnable로 감싸서 실행해 보고 지정한 예외가 나오는지 확인한다.
	// 예) expect((Runnable) () -> { throw new IllegalArgumentException(); }).to_throw(IllegalArgumentException.class);
	public void to_throw(Class<? extends Throwable> exceptionType) {
		boolean thrown = false;
		if (val instanceof Runnable r) {
			try {
				r.run();
			} catch (Throwable t) {
				thrown = exceptionType.isInstance(t);
			}
		}
		if (failed(thrown)) {
			System.out.println("[" + exceptionType.getCanonicalName() + "] not thrown");
		}
	}

	private boolean failed (boolean result) {
		return (result ^ negative == false);
	}
}

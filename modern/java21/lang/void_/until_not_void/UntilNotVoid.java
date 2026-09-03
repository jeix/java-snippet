package modern.java21.lang.void_.until_not_void;

import java.math.BigDecimal;

public class UntilNotVoid {
	public static String until_not_void(String... args) {
		String maybe_last = null;
		for (String arg : args) {
			maybe_last = arg;
			if (arg != null && arg.length() > 0) {
				return arg;
			}
		}
		return maybe_last;
	}

	public static BigDecimal until_not_void(BigDecimal... args) {
		BigDecimal maybe_last = null;
		for (BigDecimal arg : args) {
			maybe_last = arg;
			if (arg != null && ! BigDecimal.ZERO.equals(arg)) {
				return arg;
			}
		}
		return maybe_last;
	}

	public static Object unv(Object... args) {
		Object maybe_last = null;
		for (Object arg : args) {
			maybe_last = arg;
			boolean truthy = switch (arg) {
				case null -> false;
				case String s -> ! s.isEmpty();
				case Integer i -> i != 0;
				case Long l -> l != 0L;
				case Float f -> f != 0.0f;
				case Double d -> d != 0.0;
				case Boolean bool -> bool;
				case BigDecimal bd -> ! BigDecimal.ZERO.equals(bd);
				default -> true;
			};
			if (truthy) return arg;
		}
		return maybe_last;
	}

	public static String unv(String... args) {
		return (String) unv((Object[]) args);
	}
	public static Integer unv(Integer... args) {
		return (Integer) unv((Object[]) args);
	}
	public static Long unv(Long... args) {
		return (Long) unv((Object[]) args);
	}
	public static Float unv(Float... args) {
		return (Float) unv((Object[]) args);
	}
	public static Double unv(Double... args) {
		return (Double) unv((Object[]) args);
	}
	public static Boolean unv(Boolean... args) {
		return (Boolean) unv((Object[]) args);
	}
	public static BigDecimal unv(BigDecimal... args) {
		return (BigDecimal) unv((Object[]) args);
	}
}

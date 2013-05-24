package lang.void_.until_not_void;

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
			if (arg != null) {
				if (arg instanceof String) {
					if (((String) arg).length() > 0) return arg;
				} else if (arg instanceof Integer) {
					if (((Integer) arg) != 0) return arg;
				} else if (arg instanceof Long) {
					if (((Long) arg) != 0L) return arg;
				} else if (arg instanceof Float) {
					if (((Float) arg) != 0.0f) return arg;
				} else if (arg instanceof Double) {
					if (((Double) arg) != 0.0) return arg;
				} else if (arg instanceof Boolean) {
					if (((Boolean) arg) != false) return arg;
				} else if (arg instanceof BigDecimal) {
					if (! BigDecimal.ZERO.equals(((BigDecimal) arg))) return arg;
				} else {
					return arg;
				}
			}
		}
		return maybe_last;
	}

	public static String unv(String... args) {
		//return until_not_void(args);
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
		//return until_not_void(args);
		return (BigDecimal) unv((Object[]) args);
	}
}
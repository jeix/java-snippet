package modern.java21.lang.is_in;

import java.util.Set;

public class IsIn {
	public static boolean is_in(String s, String... args) {
		if (args.length == 1) { // csv
			return Set.of(args[0].split(",")).contains(s);
		} else { // array
			return Set.of(args).contains(s);
		}
	}

	public static boolean is_in(Integer i, String csv) {
		return Set.of(csv.split(",")).contains(String.valueOf(i));
	}
	public static boolean is_in(Integer i, Integer... args) {
		return Set.of(args).contains(i);
	}

	public static boolean isin(Object x, Object... args) {
		if (args.length == 1 && args[0] instanceof String csv) {
			return Set.of(csv.split(",")).contains(String.valueOf(x));
		}
		for (Object arg : args) {
			if (arg != null && arg.equals(x)) return true;
		}
		return false;
	}
	public static boolean isin(String s, String... args) {
		return isin((Object) s, (Object[]) args);
	}
	public static boolean isin(Integer i, String csv) { // valid if and only if fixed width
		return isin((Object) i, new Object[] { csv });
	}
	public static boolean isin(Integer i, Integer... args) {
		return isin((Object) i, (Object[]) args);
	}
	public static boolean isin(Long l, String csv) { // valid if and only if fixed width
		return isin((Object) l, new Object[] { csv });
	}
	public static boolean isin(Long l, Long... args) {
		return isin((Object) l, (Object[]) args);
	}
	public static boolean isin(Float f, String csv) { // valid if and only if fixed width
		return isin((Object) f, new Object[] { csv });
	}
	public static boolean isin(Float f, Float... args) {
		return isin((Object) f, (Object[]) args);
	}
	public static boolean isin(Double d, String csv) { // valid if and only if fixed width
		return isin((Object) d, new Object[] { csv });
	}
	public static boolean isin(Double d, Double... args) {
		return isin((Object) d, (Object[]) args);
	}
}

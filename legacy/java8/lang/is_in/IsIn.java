package lang.is_in;

public class IsIn {
	public static boolean is_in(String s, String... args) {
		if (args.length == 1) { // csv
			return args[0].contains(s);
		} else { // array
			for (String arg : args) {
				if (arg.equals(s)) return true;
			}
			return false;
		}
	}
	
	public static boolean is_in(Integer i, String csv) {
		return csv.contains(i.toString()); // valid if and only if fixed width
	}
	public static boolean is_in(Integer i, Integer... args) {
		for (Integer arg : args) {
			if (i == arg) return true;
		}
		return false;
	}
	
	public static boolean isin(Object x, Object... args) {
		if (args.length == 1 && args[0] instanceof String) {
			String csv = (String) args[0];
			return csv.contains(x.toString());
		} else {
			for (Object arg : args) {
				if (arg != null) {
					if (x instanceof Integer && arg instanceof Integer) {
						if ((Integer) arg == (Integer) x) return true;
					} else if (x instanceof Long && arg instanceof Long) {
						if ((Long) arg == (Long) x) return true;
					} else if (x instanceof Float && arg instanceof Float) {
						// if (((Float) arg).floatValue() == ((Float) x).floatValue()) return true;
						if (Float.compare((Float) arg, (Float) x) == 0) return true;
					} else if (x instanceof Double && arg instanceof Double) {
						// if (((Double) arg).doubleValue() == ((Double) x).doubleValue()) return true;
						if (Double.compare((Double) arg, (Double) x) == 0) return true;
					} else {
						if (arg.equals(x)) return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean isin(String s, String... args) {
		// return is_in(s, args);
		return isin((Object) s, (Object[]) args);
	}
	public static boolean isin(Integer i, String csv) { // valid if and only if fixed width
		// return is_in(i, csv);
		return isin((Object) i, new Object[] { csv });
	}
	public static boolean isin(Integer i, Integer... args) {
		// return is_in(i, args);
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
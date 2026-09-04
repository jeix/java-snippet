package modern.java21.lang.is_in;

import java.util.Arrays;
import java.util.Objects;

public class IsIn {

	public static boolean is_in(String value, String... candidates) {
		if (candidates == null) return false;
		if (candidates.length == 1) return csv_contains(candidates[0], value);
		return Arrays.stream(candidates).anyMatch(candidate -> Objects.equals(candidate, value));
	}

	public static boolean is_in(Integer value, String csv) {
		return csv_contains(csv, value);
	}

	public static boolean is_in(Integer value, Integer... candidates) {
		return array_contains(value, candidates);
	}

	public static boolean isin(Object value, Object... candidates) {
		if (candidates == null) return false;
		if (candidates.length == 1 && candidates[0] instanceof String csv) {
			return csv_contains(csv, value);
		}
		return array_contains(value, candidates);
	}

	public static boolean isin(String value, String... candidates) {
		return is_in(value, candidates);
	}

	public static boolean isin(Integer value, String csv) {
		return csv_contains(csv, value);
	}

	public static boolean isin(Integer value, Integer... candidates) {
		return array_contains(value, candidates);
	}

	public static boolean isin(Long value, String csv) {
		return csv_contains(csv, value);
	}

	public static boolean isin(Long value, Long... candidates) {
		return array_contains(value, candidates);
	}

	public static boolean isin(Float value, String csv) {
		return csv_contains(csv, value);
	}

	public static boolean isin(Float value, Float... candidates) {
		return array_contains(value, candidates);
	}

	public static boolean isin(Double value, String csv) {
		return csv_contains(csv, value);
	}

	public static boolean isin(Double value, Double... candidates) {
		return array_contains(value, candidates);
	}

	private static boolean csv_contains(String csv, Object value) {
		if (csv == null || value == null) return false;
		var expected = value.toString();
		return Arrays.stream(csv.split(",", -1))
				.map(String::trim)
				.anyMatch(expected::equals);
	}

	private static <T> boolean array_contains(T value, T[] candidates) {
		return candidates != null
				&& Arrays.stream(candidates).anyMatch(candidate -> Objects.equals(candidate, value));
	}
}

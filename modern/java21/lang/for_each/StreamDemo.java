package modern.java21.lang.for_each;

import java.util.Collection;
import java.util.List;

public class StreamDemo {

	private static final List<String> WORDS = List.of("foo", "bar", "baz");
	private static final List<Integer> NUMBERS = List.of(0, 1, 2, 3);

	private void test_map_instead_of_mutating_for_each() {
		var uppercased = WORDS.stream()
				.map(String::toUpperCase)
				.toList();
		System.out.println(uppercased); // -> [FOO, BAR, BAZ]
	}

	private void test_all_match() {
		System.out.println(NUMBERS.stream().allMatch(number -> number % 2 == 0)); // -> false
		System.out.println(NUMBERS.stream().allMatch(number -> number < 5)); // -> true
	}

	private void test_any_match() {
		System.out.println(NUMBERS.stream().anyMatch(number -> number % 2 == 1)); // -> true
		System.out.println(NUMBERS.stream().anyMatch(number -> number > 5)); // -> false
	}

	private void test_filter() {
		var filtered = WORDS.stream()
				.filter("bar"::equals)
				.toList();
		System.out.println(filtered); // -> [bar]
	}

	private void test_map() {
		var uppercased = WORDS.stream()
				.map(String::toUpperCase)
				.toList();
		System.out.println(uppercased); // -> [FOO, BAR, BAZ]
	}

	private void test_reduce() {
		var sum = NUMBERS.stream().reduce(0, Integer::sum);
		System.out.println(sum); // -> 6
	}

	private void test_flat_map() {
		var nested = List.of(List.of(0, 1), List.of(2, 3), List.of(4, 5));
		var flattened = nested.stream()
				.flatMap(Collection::stream)
				.toList();
		System.out.println(flattened); // -> [0, 1, 2, 3, 4, 5]
	}

	private void test_reversed_reduce() {
		var sum = NUMBERS.reversed().stream().reduce(0, Integer::sum);
		System.out.println(sum); // -> 6
	}

	private void test_reversed_flat_map() {
		var nested = List.of(List.of(0, 1), List.of(2, 3), List.of(4, 5));
		var flattened = nested.reversed().stream()
				.flatMap(Collection::stream)
				.toList();
		System.out.println(flattened); // -> [4, 5, 2, 3, 0, 1]
	}

	public void test() {
		test_map_instead_of_mutating_for_each();
		test_all_match();
		test_any_match();
		test_filter();
		test_map();
		test_reduce();
		test_flat_map();
		test_reversed_reduce();
		test_reversed_flat_map();
		System.out.println(":wq");
	}

	public static void main(String[] args) {
		new StreamDemo().test();
	}
}

package modern.java21.collection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class RemoveDuringIterationModern {

	private static final Predicate<String> SHOULD_REMOVE = item -> item.contains("도");

	private static List<String> prepare_list() {
		return new ArrayList<>(List.of("고구마", "고도리", "고사리"));
	}

	private static Map<String, String> prepare_map() {
		var items = new LinkedHashMap<String, String>();
		items.put("고구마", "고구마");
		items.put("고도리", "고도리");
		items.put("고사리", "고사리");
		return items;
	}

	private void test_remove_if() {
		var items = prepare_list();
		items.removeIf(SHOULD_REMOVE);
		System.out.println("removeIf: " + items);
	}

	private void test_remove_all() {
		var items = prepare_list();
		items.removeAll(List.of("고도리", "없는 항목"));
		System.out.println("removeAll: " + items);
	}

	private void test_stream_filter() {
		var original = prepare_list();
		var filtered = original.stream()
				.filter(SHOULD_REMOVE.negate())
				.toList();
		System.out.println("stream original: " + original);
		System.out.println("stream filtered: " + filtered);
	}

	private void test_map_remove_if() {
		var items = prepare_map();
		items.entrySet().removeIf(entry -> SHOULD_REMOVE.test(entry.getValue()));
		System.out.println("map removeIf: " + items);
	}

	public void test() {
		test_remove_if();
		test_remove_all();
		test_stream_filter();
		test_map_remove_if();
		System.out.println(":wq");
	}

	public static void main(String[] args) {
		new RemoveDuringIterationModern().test();
	}
}

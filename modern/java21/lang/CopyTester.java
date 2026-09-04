package modern.java21.lang;

import java.util.List;

public class CopyTester {

	record Bar(String value) {}

	record Foo(String value, List<Bar> bars) {

		Foo {
			bars = List.copyOf(bars);
		}

		Foo copy() {
			return new Foo(value, bars);
		}

		Foo withValue(String new_value) {
			return new Foo(new_value, bars);
		}

		Foo withBar(int index, Bar new_bar) {
			var copied_bars = new java.util.ArrayList<>(bars);
			copied_bars.set(index, new_bar);
			return new Foo(value, copied_bars);
		}
	}

	private void test_explicit_copy() {
		var original = new Foo("푸 1", List.of(new Bar("바 11"), new Bar("바 12")));
		var copied = original.copy();

		System.out.println("same instance: " + (original == copied));
		System.out.println("equal value: " + original.equals(copied));

		var changed = copied
				.withValue("푸 2")
				.withBar(1, new Bar("바 22"));
		System.out.println("original: " + original);
		System.out.println("changed: " + changed);
	}

	public void test() {
		test_explicit_copy();
		System.out.println(":wq");
	}

	public static void main(String[] args) {
		new CopyTester().test();
	}
}

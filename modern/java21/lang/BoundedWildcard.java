package modern.java21.lang;

import java.util.ArrayList;
import java.util.List;

public class BoundedWildcard {

	public sealed class Foo permits Bar, Baz {
		public void do_something() {
			System.out.println("Foo::" + this);
		}
	}

	public final class Bar extends Foo {
		public void do_something() {
			System.out.println("Bar::" + this);
		}
	}

	public final class Baz extends Foo {
		public void do_something() {
			System.out.println("Baz::" + this);
		}
	}

	private void test_bounded_wildcard() {
		var fooz = new ArrayList<Foo>();
		fooz.add(new Foo());
		fooz.add(new Bar());
		fooz.add(new Baz());
		bounded_wildcard(fooz);

		var barz = new ArrayList<Bar>();
		barz.add(new Bar());
		barz.add(new Bar());
		bounded_wildcard(barz);

		var bazz = new ArrayList<Baz>();
		bazz.add(new Baz());
		bazz.add(new Baz());
		bounded_wildcard(bazz);
	}
	private void bounded_wildcard(List<? extends Foo> list) {
		for (var item : list) {
			item.do_something();
		}
	}

	private void test_nothing() {
		System.out.println(":wq");
	}

	public void test() {
		test_bounded_wildcard();
		test_nothing();
	}

	public static void main(String[] args) {
		BoundedWildcard worker = new BoundedWildcard();
		worker.test();
	}
}

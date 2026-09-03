package lang;

import java.util.ArrayList;
import java.util.List;

public class BoundedWildcard {
	
	public class Foo {
		public void do_something() {
			System.out.println("Foo::" + this);
		}
	}
	
	public class Bar extends Foo {
		public void do_something() {
			System.out.println("Bar::" + this);
		}
	}
	
	public class Baz extends Foo {
		public void do_something() {
			System.out.println("Baz::" + this);
		}
	}
	
	private void test_bounded_wildcard() {
		List<Foo> fooz = new ArrayList<Foo>();
		fooz.add(new Foo());
		fooz.add(new Bar());
		fooz.add(new Baz());
		bounded_wildcard(fooz);
		
		List<Bar> barz = new ArrayList<Bar>();
		barz.add(new Bar());
		barz.add(new Bar());
		bounded_wildcard(barz);
		
		List<Baz> bazz = new ArrayList<Baz>();
		bazz.add(new Baz());
		bazz.add(new Baz());
		bounded_wildcard(bazz);
	}
	private void bounded_wildcard(List<? extends Foo> list) {
		for (Foo item : list) {
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

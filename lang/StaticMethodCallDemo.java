package lang;

public class StaticMethodCallDemo {
	
	static class Foo {
		public static void static_do_something() {
			System.out.println("Foo.static_do_something()");
		}
		public void do_something() {
			System.out.println("Foo#do_something()");
		}
	}
	
	static class Bar extends Foo {
		public static void static_do_something() {
			System.out.println("Bar.static_do_something()");
		}
		public void do_something() {
			System.out.println("Bar#do_something()");
		}
	}
	
	public void test_call_static_method() {
		Foo foo = new Foo();
		foo.static_do_something(); // -> Foo.static_do_something()
		foo.do_something(); // -> Foo#do_something()
		
		Bar bar = new Bar();
		bar.static_do_something(); // -> Bar.static_do_something()
		bar.do_something(); // -> Bar#do_something()
		
		((Foo) bar).static_do_something(); // -> Foo.static_do_something()
		((Foo) bar).do_something(); // -> Bar#do_something()
		
		foo = new Bar();
		foo.static_do_something(); // -> Foo.static_do_something()
		((Bar) foo).static_do_something(); // -> Bar.static_do_something()
		foo.do_something(); // -> Bar#do_something()
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_call_static_method();
		test_nothing();
	}
	
	public static void main(String[] args) {
		StaticMethodCallDemo worker = new StaticMethodCallDemo();
		worker.test();
	}
}

package ood.template_method;

public class OverrideProtectedMethodTest {
	
	class Foo {
		
		public final void public_template_method() {
			System.out.println("Foo#public_template_method()");
			protected_method();
		}
		
		protected void protected_method() {
			System.out.println("Foo#protected_method()");
		}
	}
	
	class Bar extends Foo {
		
		protected void protected_method() {
			System.out.println("Bar#protected_method()");
		}
	}
	
	private void test_Something() {
		Foo foo = new Foo();
		foo.public_template_method();
			// -> Foo#public_template_method()
			// -> Foo#protected_method()
		Foo bar = new Bar();
		bar.public_template_method();
			// -> Foo#public_template_method()
			// -> Bar#protected_method()
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_Something();
		test_nothing();
	}
	
	public static void main(String[] args) {
		OverrideProtectedMethodTest worker = new OverrideProtectedMethodTest();
		worker.test();
	}
}

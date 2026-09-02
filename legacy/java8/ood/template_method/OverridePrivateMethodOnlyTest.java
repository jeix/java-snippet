package ood.template_method;

public class OverridePrivateMethodOnlyTest {
	
	class Foo {
		
		public void public_template_method() {
			System.out.println("Foo#public_template_method()");
			private_method();
		}
		
		private void private_method() {
			System.out.println("Foo#private_method()");
		}
	}
	
	class Bar extends Foo {
		
		private void private_method() {
			System.out.println("Bar#private_method()");
		}
	}
	
	class Baz extends Foo {
		
		public void public_template_method() {
			System.out.println("Baz#public_template_method()");
			private_method();
		}
		
		private void private_method() {
			System.out.println("Baz#private_method()");
		}
	}
	
	private void test_Something() {
		Foo foo = new Foo();
		foo.public_template_method();
			// -> Foo#public_template_method()
			// -> Foo#private_method()
		Foo bar = new Bar();
		bar.public_template_method();
			// -> Foo#public_template_method()
			// -> Foo#private_method()
		Foo baz = new Baz();
		baz.public_template_method();
			// -> Baz#public_template_method()
			// -> Baz#private_method()
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_Something();
		test_nothing();
	}
	
	public static void main(String[] args) {
		OverridePrivateMethodOnlyTest worker = new OverridePrivateMethodOnlyTest();
		worker.test();
	}
}

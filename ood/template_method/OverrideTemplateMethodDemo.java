package ood.template_method;

public class OverrideTemplateMethodDemo {
	
	abstract class AbstractFoo {
		public abstract void public_template_method();
	}
	
	class Foo extends AbstractFoo {
		
		public void public_template_method() {
			System.out.println("Foo#public_template_method()");
			private_method();
		}
		
		private void private_method() {
			System.out.println("Foo#private_method()");
		}
	}
	
	class Bar extends AbstractFoo {
		
		public void public_template_method() {
			System.out.println("Bar#public_template_method()");
			private_method();
		}
		
		private void private_method() {
			System.out.println("Bar#private_method()");
		}
	}
	
	class Baz extends AbstractFoo {
		
		public void public_template_method() {
			System.out.println("Baz#public_template_method()");
			private_method();
		}
		
		private void private_method() {
			System.out.println("Baz#private_method()");
		}
	}
	
	private void test_Something() {
		AbstractFoo foo = new Foo();
		foo.public_template_method();
			// -> Foo#public_template_method()
			// -> Foo#private_method()
		AbstractFoo bar = new Bar();
		bar.public_template_method();
			// -> Bar#public_template_method()
			// -> Bar#private_method()
		AbstractFoo baz = new Baz();
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
		OverrideTemplateMethodDemo worker = new OverrideTemplateMethodDemo();
		worker.test();
	}
}

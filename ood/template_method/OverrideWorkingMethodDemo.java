package ood.template_method;

public class OverrideWorkingMethodDemo {
	
	abstract class Foo {
		
		public final void public_template_method() {
			System.out.println("Foo#public_template_method()");
			protected_method();
			private_method();
		}
		
		protected abstract void protected_method();
		
		private void private_method() {
			System.out.println("Foo#private_method()");
		}
	}
	
	class NaturalFoo extends Foo {
		
		protected void protected_method() {
			System.out.println("NaturalFoo#protected_method()");
		}
	}
	
	class RealFoo extends Foo {
		
		protected void protected_method() {
			System.out.println("RealFoo#protected_method()");
		}
	}
	
	class ComplexFoo extends Foo {
		
		protected void protected_method() {
			System.out.println("ComplexFoo#protected_method()");
		}
	}
	
	private void test_Something() {
		Foo natural = new NaturalFoo();
		natural.public_template_method();
			// -> Foo#public_template_method()
			// -> NaturalFoo#protected_method()
			// -> Foo#private_method()
		Foo real = new RealFoo();
		real.public_template_method();
			// -> Foo#public_template_method()
			// -> RealFoo#protected_method()
			// -> Foo#private_method()
		Foo complex = new ComplexFoo();
		complex.public_template_method();
			// -> Foo#public_template_method()
			// -> ComplexFoo#protected_method()
			// -> Foo#private_method()
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_Something();
		test_nothing();
	}
	
	public static void main(String[] args) {
		OverrideWorkingMethodDemo worker = new OverrideWorkingMethodDemo();
		worker.test();
	}
}

package modern.java21.ood.template_method;

public class OverrideWorkingMethodDemo {

	sealed interface Foo permits NaturalFoo, RealFoo, ComplexFoo {
		void publicTemplateMethod();
	}

	static final class NaturalFoo implements Foo {
		@Override
		public void publicTemplateMethod() {
			System.out.println("Foo#public_template_method()");
			protectedMethod();
			System.out.println("Foo#private_method()");
		}

		private void protectedMethod() {
			System.out.println("NaturalFoo#protected_method()");
		}
	}

	static final class RealFoo implements Foo {
		@Override
		public void publicTemplateMethod() {
			System.out.println("Foo#public_template_method()");
			protectedMethod();
			System.out.println("Foo#private_method()");
		}

		private void protectedMethod() {
			System.out.println("RealFoo#protected_method()");
		}
	}

	static final class ComplexFoo implements Foo {
		@Override
		public void publicTemplateMethod() {
			System.out.println("Foo#public_template_method()");
			protectedMethod();
			System.out.println("Foo#private_method()");
		}

		private void protectedMethod() {
			System.out.println("ComplexFoo#protected_method()");
		}
	}

	private void testSomething() {
		Foo natural = new NaturalFoo();
		natural.publicTemplateMethod();

		Foo real = new RealFoo();
		real.publicTemplateMethod();

		Foo complex = new ComplexFoo();
		complex.publicTemplateMethod();
	}

	private void testNothing() {
		System.out.println(":wq");
	}

	public void test() {
		testSomething();
		testNothing();
	}

	public static void main(String[] args) {
		var worker = new OverrideWorkingMethodDemo();
		worker.test();
	}
}
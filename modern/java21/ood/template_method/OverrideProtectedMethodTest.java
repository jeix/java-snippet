package modern.java21.ood.template_method;

public class OverrideProtectedMethodTest {

	abstract static class Foo {
		public final void publicTemplateMethod() {
			System.out.println("Foo#public_template_method()");
			protectedMethod();
		}

		protected void protectedMethod() {
			System.out.println("Foo#protected_method()");
		}
	}

	static final class Bar extends Foo {
		@Override
		protected void protectedMethod() {
			System.out.println("Bar#protected_method()");
		}
	}

	private void testSomething() {
		Foo foo = new Foo() {};
		foo.publicTemplateMethod();

		Foo bar = new Bar();
		bar.publicTemplateMethod();
	}

	private void testNothing() {
		System.out.println(":wq");
	}

	public void test() {
		testSomething();
		testNothing();
	}

	public static void main(String[] args) {
		var worker = new OverrideProtectedMethodTest();
		worker.test();
	}
}
package modern.java21.ood.template_method;

public class OverridePrivateMethodOnlyTest {

	static sealed class Foo permits Bar, Baz {
		public void publicTemplateMethod() {
			System.out.println("Foo#public_template_method()");
			privateMethod();
		}

		private void privateMethod() {
			System.out.println("Foo#private_method()");
		}
	}

	static final class Bar extends Foo {
		private void privateMethod() {
			System.out.println("Bar#private_method()");
		}
	}

	static final class Baz extends Foo {
		@Override
		public void publicTemplateMethod() {
			System.out.println("Baz#public_template_method()");
			privateMethod();
		}

		private void privateMethod() {
			System.out.println("Baz#private_method()");
		}
	}

	private void testSomething() {
		Foo foo = new Foo();
		foo.publicTemplateMethod();

		Foo bar = new Bar();
		bar.publicTemplateMethod();

		Foo baz = new Baz();
		baz.publicTemplateMethod();
	}

	private void testNothing() {
		System.out.println(":wq");
	}

	public void test() {
		testSomething();
		testNothing();
	}

	public static void main(String[] args) {
		var worker = new OverridePrivateMethodOnlyTest();
		worker.test();
	}
}
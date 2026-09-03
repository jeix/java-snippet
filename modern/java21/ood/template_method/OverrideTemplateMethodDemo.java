package modern.java21.ood.template_method;

public class OverrideTemplateMethodDemo {

	sealed interface AbstractFoo permits Foo, Bar, Baz {
		void publicTemplateMethod();

		static record Log() {
			void log(String message) {
				System.out.println(message);
			}
		}
	}

	static final class Foo implements AbstractFoo {
		@Override
		public void publicTemplateMethod() {
			var log = new AbstractFoo.Log();
			log.log("Foo#public_template_method()");
			work();
		}

		private void work() {
			var log = new AbstractFoo.Log();
			log.log("Foo#private_method()");
		}
	}

	static final class Bar implements AbstractFoo {
		@Override
		public void publicTemplateMethod() {
			var log = new AbstractFoo.Log();
			log.log("Bar#public_template_method()");
			work();
		}

		private void work() {
			var log = new AbstractFoo.Log();
			log.log("Bar#private_method()");
		}
	}

	static final class Baz implements AbstractFoo {
		@Override
		public void publicTemplateMethod() {
			var log = new AbstractFoo.Log();
			log.log("Baz#public_template_method()");
			work();
		}

		private void work() {
			var log = new AbstractFoo.Log();
			log.log("Baz#private_method()");
		}
	}

	private void testSomething() {
		AbstractFoo foo = new Foo();
		foo.publicTemplateMethod();

		AbstractFoo bar = new Bar();
		bar.publicTemplateMethod();

		AbstractFoo baz = new Baz();
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
		var worker = new OverrideTemplateMethodDemo();
		worker.test();
	}
}
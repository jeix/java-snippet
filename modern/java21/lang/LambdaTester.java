package modern.java21.lang;

public class LambdaTester {

	@FunctionalInterface
	interface FooHoo {
		void oohoof();
	}

	private void named_implementation() {
		System.out.println("oohoof ... Method Reference");
	}

	private void test_lambda() {
		FooHoo action = () -> System.out.println("oohoof ... Lambda Implements Interface");
		action.oohoof();
	}

	private void test_method_reference() {
		FooHoo action = this::named_implementation;
		action.oohoof();
	}

	public void test() {
		test_lambda();
		test_method_reference();
		System.out.println(":wq");
	}

	public static void main(String[] args) {
		new LambdaTester().test();
	}
}

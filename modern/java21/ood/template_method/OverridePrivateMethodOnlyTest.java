package modern.java21.ood.template_method;

// 원형 유지: public 템플릿 메서드에서 부르는 private 메서드만 오버라이드하면 다형성이
// 통하지 않는다는 문제 제기다. OverrideTemplateMethodDemo.java(대안 1),
// OverrideWorkingMethodDemo.java(권장), OverrideProtectedMethodTest.java(변형)와 함께
// "이렇게 하면 안 된다 → 이렇게 하면 된다"는 흐름을 보여주는 시리즈다.

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

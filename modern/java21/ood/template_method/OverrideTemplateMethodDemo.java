package modern.java21.ood.template_method;

// 원형 유지: 템플릿 메서드 자체를 abstract로 두고 강제로 오버라이드하게 하는 대안이다.
// (동작은 하지만 템플릿 메서드를 서브클래스마다 중복 작성해야 해서 권장되지 않는다 -
// OverrideWorkingMethodDemo.java가 권장하는 방식이다.) OverridePrivateMethodOnlyTest.java와
// 함께 보는 시리즈의 한 단계다.

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

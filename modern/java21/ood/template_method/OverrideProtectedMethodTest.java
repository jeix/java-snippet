package modern.java21.ood.template_method;

// 원형 유지: OverrideWorkingMethodDemo.java와 같은 구조지만 작업 메서드를 abstract로
// 강제하지 않는 변형이다 - 강제할 필요가 없다면 이 정도로 충분하다는 것을 보여준다.

public class OverrideProtectedMethodTest {
	
	class Foo {
		
		public final void public_template_method() {
			System.out.println("Foo#public_template_method()");
			protected_method();
		}
		
		protected void protected_method() {
			System.out.println("Foo#protected_method()");
		}
	}
	
	class Bar extends Foo {
		
		protected void protected_method() {
			System.out.println("Bar#protected_method()");
		}
	}
	
	private void test_Something() {
		Foo foo = new Foo();
		foo.public_template_method();
			// -> Foo#public_template_method()
			// -> Foo#protected_method()
		Foo bar = new Bar();
		bar.public_template_method();
			// -> Foo#public_template_method()
			// -> Bar#protected_method()
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_Something();
		test_nothing();
	}
	
	public static void main(String[] args) {
		OverrideProtectedMethodTest worker = new OverrideProtectedMethodTest();
		worker.test();
	}
}

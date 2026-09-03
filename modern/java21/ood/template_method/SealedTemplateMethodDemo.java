package modern.java21.ood.template_method;

// OverridePrivateMethodOnlyTest/OverrideTemplateMethodDemo/OverrideWorkingMethodDemo/
// OverrideProtectedMethodTest가 다형성(오버라이드)으로 템플릿 메서드의 확장 지점을 여는
// 시리즈의 Java 21 판. sealed interface로 확장 지점을 "이 목록 안에서만"이라고 못박고,
// 템플릿 메서드 쪽에서 패턴 매칭 switch(JEP 441)로 처리한다 - 새 변형을 추가하면 switch가
// 컴파일 타임에 모든 경우를 다뤘는지 검사해 준다(exhaustiveness).
public class SealedTemplateMethodDemo {

	sealed interface Foo permits NaturalFoo, RealFoo, ComplexFoo {}
	record NaturalFoo() implements Foo {}
	record RealFoo() implements Foo {}
	record ComplexFoo() implements Foo {}

	public final void public_template_method(Foo foo) {
		System.out.println("SealedTemplateMethodDemo#public_template_method()");
		protected_method(foo);
		private_method();
	}

	private void protected_method(Foo foo) {
		String message = switch (foo) {
			case NaturalFoo n -> "NaturalFoo#protected_method()";
			case RealFoo r -> "RealFoo#protected_method()";
			case ComplexFoo c -> "ComplexFoo#protected_method()";
		};
		System.out.println(message);
	}

	private void private_method() {
		System.out.println("SealedTemplateMethodDemo#private_method()");
	}

	private void test_Something() {
		public_template_method(new NaturalFoo());
			// -> SealedTemplateMethodDemo#public_template_method()
			// -> NaturalFoo#protected_method()
			// -> SealedTemplateMethodDemo#private_method()
		public_template_method(new RealFoo());
			// -> SealedTemplateMethodDemo#public_template_method()
			// -> RealFoo#protected_method()
			// -> SealedTemplateMethodDemo#private_method()
		public_template_method(new ComplexFoo());
			// -> SealedTemplateMethodDemo#public_template_method()
			// -> ComplexFoo#protected_method()
			// -> SealedTemplateMethodDemo#private_method()
	}

	private void test_nothing() {
		System.out.println(":wq");
	}

	public void test() {
		test_Something();
		test_nothing();
	}

	public static void main(String[] args) {
		SealedTemplateMethodDemo worker = new SealedTemplateMethodDemo();
		worker.test();
	}
}

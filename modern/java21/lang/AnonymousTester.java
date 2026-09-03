package modern.java21.lang;

public class AnonymousTester {
	
	interface FooHoo {
		public void oohoof();
	}
	abstract class AbstractFooHoo implements FooHoo {
		public abstract void oohoof();
	}
	class FooHooImpl implements FooHoo {
		public void oohoof() {
			System.out.println("FooHooImpl#oohoof()");
		}
	}
	
	private void test_Interface() {
		// FooHoo는 추상 메서드가 하나뿐인 함수형 인터페이스라 람다로 바로 구현할 수 있다.
		FooHoo fh = () -> System.out.println("oohoof ... Implements Interface");
		fh.oohoof();
	}

	private void test_AbstractClass() {
		// 람다는 인터페이스만 구현할 수 있다 - 추상 클래스를 상속하는 이 경우는 익명 클래스로 남는다.
		FooHoo afh = new AbstractFooHoo() {
			public void oohoof() {
				System.out.println("oohoof ... Extends Abstract Class");
			}
		};
		afh.oohoof();
	}

	private void test_Class() {
		// 람다는 인터페이스만 구현할 수 있다 - 구체 클래스를 상속하는 이 경우도 익명 클래스로 남는다.
		FooHoo fhi = new FooHooImpl() {
			public void oohoof() {
				System.out.println("oohoof ... Extends Class");
			}
		};
		fhi.oohoof();
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_Interface();
		test_AbstractClass();
		test_Class();
		test_nothing();
	}
	
	public static void main(String[] args) {
		AnonymousTester worker = new AnonymousTester();
		worker.test();
	}
}

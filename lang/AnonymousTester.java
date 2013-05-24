package lang;

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
		FooHoo fh = new FooHoo() {
			public void oohoof() {
				System.out.println("oohoof ... Implements Interface");
			}
		};
		fh.oohoof();
	}
	
	private void test_AbstractClass() {
		FooHoo afh = new AbstractFooHoo() {
			public void oohoof() {
				System.out.println("oohoof ... Extends Abstract Class");
			}
		};
		afh.oohoof();
	}
	
	private void test_Class() {
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

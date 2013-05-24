package lang.inner_class;

public class InnerClassFactory {
	
	private InnerClassFactory() {}

	public class Foo {
		private Foo() {}
		
		public void foo(String s) {
			System.out.println(s);
		}
	}
	
	private Foo create() {
		return new Foo();
	}
	
	public static Foo create_Foo() {
		//return new Foo(); // compile time error
			// non-static variable this cannot be referenced from a static context
		//return create(); // compile time error
			// non-static method create() cannot be referenced from a static context
		return new InnerClassFactory().create(); // instantiate factory -- to avoid compile time error
	}
	
	public static class Bar { // static
		private Bar() {}
		
		public void bar(String s) {
			System.out.println(s);
		}
	}
	
	public static Bar create_Bar() {
		return new Bar(); // no factory instantiation
	}

	public static class Baz { // static
		private Baz() {}

		public static void baz(String s) { // static method
			System.out.println(s);
		}
	}

	public static class Qux { // static
		public void qux(String s) {
			System.out.println(s);
		}
	}
}

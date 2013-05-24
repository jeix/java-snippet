package lang.inner_class;

class Outer {
	
	private String s;
	
	public Outer(String s) {
		this.s = s;
	}
	
	public String process_but_hide_inner(String t) {
		return new PrivateInner().private_process(t);
	}
	
	private class PrivateInner {
		private PrivateInner() {};
		
		private String private_process(String t) {
			return s + t;
		}
	}
	
	public Inner inner() {
		return new Inner();
	}
	
	public class Inner {
		private Inner() {};
		
		public String process(String t) {
			return s + t;
		}
	}
}

class OuterFactory {
	
	private OuterFactory() {}
	
	public static Inner create_Inner() {
		//return new Inner(); // compile time error
			// non-static variable this cannot be referenced from a static context
		//return inner(); // compile time error
			// non-static method inner() cannot be referenced from a static context
		return new OuterFactory().inner(); // instantiate factory -- to avoid compile time error
	}
	
	private Inner inner() {
		return new Inner();
	}
	
	public class Inner {
		private Inner() {}
		
		public void process(String s) {
			System.out.println(s);
		}
	}
	
	public static void process_but_hide_PrivateStaticNested(String s) {
		PrivateStaticNested.private_process(s);
	}
	
	private static class PrivateStaticNested {
		private PrivateStaticNested() {};
		
		private static void private_process(String s) {
			System.out.println(s);
		}
	}
	
	public static StaticNested create_StaticNested() {
		return new StaticNested(); // no factory instantiation
	}
	
	public static class StaticNested { // static
		private StaticNested() {}
		
		public void process(String s) {
			System.out.println(s);
		}
		
		public static void static_process(String s) { // static method
			System.out.println(s);
		}
	}
	
	public static class StaticNestedInstantiable { // static
		public StaticNestedInstantiable() {}
		
		public void process(String s) {
			System.out.println(s);
		}
	}
}

public class InnerClassInstantiationDemo {
	
	private void test_instance_inner_class() {
		String str = new Outer("new Outer()").process_but_hide_inner("#process_but_hide_inner()");
		System.out.println(str);
		
		Outer.Inner inner = new Outer("new Outer()").inner();
		str = inner.process("#inner()#process()");
		System.out.println(str);
	}
	
	private void test_class_inner_class() {
		// create non-static inner with static method that instantiate factory
		OuterFactory.Inner inner_0 = OuterFactory.create_Inner();
		inner_0.process("OuterFactory.create_Inner()#process() -- Why create non-static inner with static method?");
		
		OuterFactory.process_but_hide_PrivateStaticNested("OuterFactory.process_but_hide_PrivateStaticNested()");
		
		// create static inner with static method that does not instantiate factory
		OuterFactory.StaticNested inner_1 = OuterFactory.create_StaticNested();
		inner_1.process("OuterFactory.create_StaticNested()#process()");
		
		OuterFactory.StaticNested.static_process("OuterFactory.StaticNested.static_process()");
		
		OuterFactory.StaticNestedInstantiable inner_2 = new OuterFactory.StaticNestedInstantiable();
		inner_2.process("new OuterFactory.StaticNestedInstantiable()#process()");
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_instance_inner_class();
		test_class_inner_class();
		test_nothing();
	}
	
	public static void main(String[] args) {
		InnerClassInstantiationDemo worker = new InnerClassInstantiationDemo();
		worker.test();
	}
}

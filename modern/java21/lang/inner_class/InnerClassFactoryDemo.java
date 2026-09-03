package modern.java21.lang.inner_class;

// 원형 유지: InnerClassFactory.java가 보여주는 non-static/static inner 클래스 생성 방식을
// 실제로 호출해서 확인하는 짝이라 그대로 둔다.

public class InnerClassFactoryDemo {
	
	private void test_inner_class_factory() {
		// create non-static inner with static method that instantiate factory
		InnerClassFactory.Foo foo = InnerClassFactory.create_Foo();
		foo.foo("InnerClassFactory.create_Foo()#foo()");
		
		// create static inner with static method that does not instantiate factory
		InnerClassFactory.Bar bar = InnerClassFactory.create_Bar();
		bar.bar("InnerClassFactory.create_Bar#bar()");

		// static 
		InnerClassFactory.Baz.baz("InnerClassFactory.Baz.baz()");

		InnerClassFactory.Qux qux = new InnerClassFactory.Qux();
		qux.qux("new InnerClassFactory.Qux()#qux");
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_inner_class_factory();
		test_nothing();
	}
	
	public static void main(String[] args) {
		InnerClassFactoryDemo worker = new InnerClassFactoryDemo();
		worker.test();
	}
}

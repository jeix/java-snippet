package string;

public class StringReplaceDemo {
	
	private void test_String_replace() {
		String s = "2010-09-03";
		System.out.println(s.replace("-", ""));
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_String_replace();
		test_nothing();
	}
	
	public static void main(String[] args) {
		StringReplaceDemo worker = new StringReplaceDemo();
		worker.test();
	}
}

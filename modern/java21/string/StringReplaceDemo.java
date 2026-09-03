package modern.java21.string;

// 원형 유지: String#replace()는 찾을 문자열을 정규식이 아닌 리터럴로 받는다는 것 자체가
// 주제라 바꿀 대상이 없다. StringReplaceAllDemo.java(정규식을 받는 replaceAll())와
// 대비된다.

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

package modern.java21.string;

// 원형 유지: String#replaceAll()이 정규식을 받는다는 것(패턴 문자를 이스케이프해야 하고,
// 문자 클래스로 숫자만 남기는 등) 자체가 주제라 바꿀 대상이 없다. StringReplaceDemo.java
// (정규식이 아닌 replace())와 대비된다.

public class StringReplaceAllDemo {
	
	private void test_String_replaceAll() {
		String s = "blah blah blah\n foo_${FOO}@bar\nblah blah blah ${FOO} blah";
		System.out.println(s.replaceAll("\\$\\{FOO\\}", "03"));
		
		s = "연령제한없음";
		s = s.replaceAll("[^0-9]", "");
		System.out.println("["+s+"]");
		if (s.length() == 0) s = "0";
		int n = Integer.parseInt(s);
		System.out.println(n);
		s = "만21세이상";
		s = s.replaceAll("[^0-9]", "");
		System.out.println("["+s+"]");
		n = Integer.parseInt(s);
		System.out.println(n);
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_String_replaceAll();
		test_nothing();
	}
	
	public static void main(String[] args) {
		StringReplaceAllDemo worker = new StringReplaceAllDemo();
		worker.test();
	}
}

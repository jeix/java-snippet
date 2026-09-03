package string;

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

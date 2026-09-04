package modern.java21.string;

public class StringReplaceAllDemo {

	private void testStringReplaceAll() {
		var s = "blah blah blah\n foo_${FOO}@bar\nblah blah blah ${FOO} blah";
		System.out.println(s.replaceAll("\\$\\{FOO\\}", "03"));

		s = "연령제한없음";
		s = s.replaceAll("[^0-9]", "");
		System.out.println("[" + s + "]");
		if (s.length() == 0) s = "0";
		var n = Integer.parseInt(s);
		System.out.println(n);

		s = "만21세이상";
		s = s.replaceAll("[^0-9]", "");
		System.out.println("[" + s + "]");
		n = Integer.parseInt(s);
		System.out.println(n);
	}

	private void testNothing() {
		System.out.println(":wq");
	}

	void test() {
		testStringReplaceAll();
		testNothing();
	}

	public static void main(String[] args) {
		var worker = new StringReplaceAllDemo();
		worker.test();
	}
}
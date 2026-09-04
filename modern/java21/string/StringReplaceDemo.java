package modern.java21.string;

public class StringReplaceDemo {

	private void testStringReplace() {
		var s = "2010-09-03";
		System.out.println(s.replace("-", ""));
	}

	private void testNothing() {
		System.out.println(":wq");
	}

	void test() {
		testStringReplace();
		testNothing();
	}

	public static void main(String[] args) {
		var worker = new StringReplaceDemo();
		worker.test();
	}
}
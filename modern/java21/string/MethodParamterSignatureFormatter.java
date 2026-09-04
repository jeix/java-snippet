package modern.java21.string;

public class MethodParamterSignatureFormatter {

	private void testFormatCommaSeparatedMethodParameter() {
		var expected = "foo(bar, baz)";
		var cases = new String[] {
			"foo(bar,baz)",
			"foo(bar, baz)",
			"foo(bar ,baz)",
			"foo(bar , baz)",
			"foo(bar  ,  baz)",
			"foo( bar,baz )"
		};
		for (var i = 0; i < cases.length; i++) {
			var normalized = format(cases[i]);
			if (!expected.equals(normalized)) {
				System.out.println(cases[i] + " :: " + expected + " expected BUT " + normalized + " returned");
			}
		}
	}

	private String format(String ms) {
		if (ms == null) return null;
		return ms
			.replaceAll(" +,", ",")
			.replaceAll(",  +", ", ")
			.replaceAll(",([^ ])", ", $1")
			.replaceAll(" +\\)", ")")
			.replaceAll("\\( +", "(");
	}

	private void testNothing() {
		System.out.println(":wq");
	}

	void test() {
		testFormatCommaSeparatedMethodParameter();
		testNothing();
	}

	public static void main(String[] args) {
		var worker = new MethodParamterSignatureFormatter();
		worker.test();
	}
}
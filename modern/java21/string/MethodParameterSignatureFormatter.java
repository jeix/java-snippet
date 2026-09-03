package modern.java21.string;

// legacy/java8/string/MethodParamterSignatureFormatter.java 를 대체해요.
// 이름 변경 이유: 클래스명의 오타(Paramter) 수정.

public class MethodParameterSignatureFormatter {

	private void test_format_comma_separated_method_parameter() {
		String expected = "foo(bar, baz)";
		String[] cases = {
			"foo(bar,baz)",
			"foo(bar, baz)",
			"foo(bar ,baz)",
			"foo(bar , baz)",
			"foo(bar  ,  baz)",
			"foo( bar,baz )"
		};
		for (int i = 0; i < cases.length; i++) {
			String normalized = format_comma_separated_method_parameter(cases[i]);
			if (! expected.equals(normalized)) { // TODO use JUnit
				System.out.println(cases[i] + " :: " + expected + " expected BUT " + normalized + " returned");
			}
		}
	}
	// format comma separated method parameter (method signature)
	private String format_comma_separated_method_parameter(String ms) {
		if (null == ms) return null;
		return ms
			.replaceAll(" +,", ",")
			.replaceAll(",  +", ", ")
			.replaceAll(",([^ ])", ", $1")
			.replaceAll(" +\\)", ")")
			.replaceAll("\\( +", "(");
	}

	private void test_nothing() {
		System.out.println(":wq");
	}

	public void test() {
		test_format_comma_separated_method_parameter();
		test_nothing();
	}

	public static void main(String[] args) {
		MethodParameterSignatureFormatter worker = new MethodParameterSignatureFormatter();
		worker.test();
	}
}

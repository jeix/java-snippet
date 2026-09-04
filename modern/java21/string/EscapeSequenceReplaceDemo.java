package modern.java21.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EscapeSequenceReplaceDemo {

	private final String s = "foo \t bar";

	private void testWithStringReplace() {
		var t = s.replace("\t", "\\t");
		System.out.println(t);
	}

	private void testWithReplaceAll() {
		var t = s.replaceAll("\\t", "\\\\t");
		System.out.println(t);
	}

	private void demoMatcherQuoteReplacement() {
		var x = "\\t";
		var y = Matcher.quoteReplacement(x);
		System.out.println("[" + x + "]=>[" + y + "]");

		x = "\\\\t";
		y = Matcher.quoteReplacement(x);
		System.out.println("[" + x + "]=>[" + y + "]");
	}

	private void demoMatcherAppendReplacement() {
		Matcher m = Pattern.compile("x", Pattern.LITERAL).matcher("xxx");

		var x = "\\t";
		var sb = new StringBuilder();
		if (m.find()) {
			m.appendReplacement(sb, x);
			System.out.println("[" + x + "]=>[" + sb.toString() + "]");
		}

		x = "\\\\t";
		sb = new StringBuilder();
		if (m.find()) {
			m.appendReplacement(sb, x);
			System.out.println("[" + x + "]=>[" + sb.toString() + "]");
		}
	}

	private void testNothing() {
		System.out.println(":wq");
	}

	void test() {
		testWithStringReplace();
		testWithReplaceAll();
		demoMatcherQuoteReplacement();
		demoMatcherAppendReplacement();
		testNothing();
	}

	public static void main(String[] args) {
		var worker = new EscapeSequenceReplaceDemo();
		worker.test();
	}
}
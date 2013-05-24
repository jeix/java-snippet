package string;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EscapeSequenceReplaceDemo {
	
	String s = "foo \t bar";
	
	private void test_with_String_replace() {
		String t = s.replace("\t", "\\t");
		System.out.println(t); // -> foo \t bar
	}
	
	private void test_with_String_replaceAll() {
		String t = s.replaceAll("\\t", "\\\\t");
		System.out.println(t); // -> foo \t bar
	}
	
	private void demo_Matcher_quoteReplacement() {
		String x = null;
		String y = null;
		
		x = "\\t";
		y = Matcher.quoteReplacement(x);
		System.out.println("[" + x + "]=>[" + y + "]"); // -> [\t]=>[\\t]
		
		x = "\\\\t";
		y = Matcher.quoteReplacement(x);
		System.out.println("[" + x + "]=>[" + y + "]"); // -> [\\t]=>[\\\\t]
	}
	
	private void demo_Matcher_appendReplacement() {
		String x = null;
		StringBuffer sb = null;
		
		Matcher m = Pattern.compile("x", Pattern.LITERAL).matcher("xxx");
		
		x = "\\t";
		sb = new StringBuffer();
		if (m.find()) {
			m.appendReplacement(sb, x);
			System.out.println("[" + x + "]=>[" + sb.toString() + "]"); // -> [\t]=>[t]
		}
		
		x = "\\\\t";
		sb = new StringBuffer();
		if (m.find()) {
			m.appendReplacement(sb, x);
			System.out.println("[" + x + "]=>[" + sb.toString() + "]"); // -> [\\t]=>[\t]
		}
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_with_String_replace();
		test_with_String_replaceAll();
		demo_Matcher_quoteReplacement();
		demo_Matcher_appendReplacement();
		test_nothing();
	}
	
	public static void main(String[] args) {
		EscapeSequenceReplaceDemo worker = new EscapeSequenceReplaceDemo();
		worker.test();
	}
}
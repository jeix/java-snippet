package string;

import java.util.Formatter;

public class FormatterFormatDemo {
	
	private void test_Something() {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		formatter.format("|%1$6.1f|%1$06.1f|%1$-6.2f|%2$8s|%2$-8s|", new Float(10.4), "Tiger"); // - means left align
		System.out.println(sb.toString());
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_Something();
		test_nothing();
	}
	
	public static void main(String[] args) {
		FormatterFormatDemo worker = new FormatterFormatDemo();
		worker.test();
	}
}

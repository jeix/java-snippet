package modern.java21.string;

import java.util.Formatter;

public class FormatterFormatDemo {

	private void testSomething() {
		var sb = new StringBuilder();
		var formatter = new Formatter(sb);
		formatter.format("|%1$6.1f|%1$06.1f|%1$-6.2f|%2$8s|%2$-8s|", 10.4f, "Tiger");
		System.out.println(sb);
	}

	private void testNothing() {
		System.out.println(":wq");
	}

	void test() {
		testSomething();
		testNothing();
	}

	public static void main(String[] args) {
		var worker = new FormatterFormatDemo();
		worker.test();
	}
}
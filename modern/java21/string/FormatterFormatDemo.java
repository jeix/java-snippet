package modern.java21.string;

import java.util.Formatter;
import java.util.Locale;

public class FormatterFormatDemo {

	private void test_Something() {
		StringBuilder sb = new StringBuilder();
		try (Formatter formatter = new Formatter(sb, Locale.ROOT)) {
			formatter.format("|%1$6.1f|%1$06.1f|%1$-6.2f|%2$8s|%2$-8s|", 10.4, "Tiger"); // - means left align
		}
		System.out.println(sb);
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

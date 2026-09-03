package modern.java21.string;

import java.util.StringTokenizer;

public class StringSplit {

	void testStringSplit() {
		var s = "boo:and:foo";

		var tokens = s.split(":");
		System.out.print(tokens.length);
		for (var i = 0; i < tokens.length; i++) {
			System.out.print("," + tokens[i]);
		}
		System.out.println();

		tokens = s.split("o");
		System.out.print(tokens.length);
		for (var i = 0; i < tokens.length; i++) {
			System.out.print("," + tokens[i]);
		}
		System.out.println();

		tokens = "".split("\\}");
		System.out.println(tokens.length);
	}

	void testStringTokenizer() {
		var s = "boo:and:foo";

		var tokens = new StringTokenizer(s, ":", false);
		System.out.print(tokens.countTokens());
		while (tokens.hasMoreTokens()) {
			System.out.print("," + tokens.nextToken());
		}
		System.out.println();

		tokens = new StringTokenizer(s, "o", false);
		System.out.print(tokens.countTokens());
		while (tokens.hasMoreTokens()) {
			System.out.print("," + tokens.nextToken());
		}
		System.out.println();

		tokens = new StringTokenizer("", "|", false);
		System.out.println(tokens.countTokens());
	}

	void testNothing() {
		System.out.println(":wq");
	}

	void test() {
		testStringSplit();
		testStringTokenizer();
		testNothing();
	}

	public static void main(String[] args) {
		var worker = new StringSplit();
		worker.test();
	}
}
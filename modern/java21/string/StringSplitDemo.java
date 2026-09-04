package modern.java21.string;

import java.util.StringTokenizer;

public class StringSplitDemo {

	public static void split(String s) {
		var sa = s.split("-");
		System.out.print(sa.length);
		for (var i = 0; i < sa.length; i++) {
			System.out.print("::");
			System.out.print(sa[i]);
		}
		System.out.println();
	}

	public static void tokenize(String s) {
		var tokens = new StringTokenizer(s, "-", false);
		System.out.print(tokens.countTokens());
		while (tokens.hasMoreTokens()) {
			System.out.print("::" + tokens.nextToken());
		}
		System.out.println();
	}

	public static void main(String[] args) {
		System.out.println("------------");
		split("xxx-yyy-zzz");
		split("-yyy-zzz");
		split("xxx-yyy-");
		split("-yyy-");
		split("--");
		split("");
		System.out.println("------------");
		tokenize("xxx-yyy-zzz");
		tokenize("-yyy-zzz");
		tokenize("xxx-yyy-");
		tokenize("-yyy-");
		tokenize("--");
		tokenize("");
		System.out.println("------------");
	}
}
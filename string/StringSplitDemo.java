package string;

import java.util.StringTokenizer;

public class StringSplitDemo {
	
	public static void split(String s) {
		String[] sa = s.split("-");
		System.out.print(sa.length);
		for (int i = 0; i < sa.length; i++) {
			System.out.print("::");
			System.out.print(sa[i]);
		}
		System.out.println();
	}
	public static void tokenize(String s) {
		StringTokenizer tokens = new StringTokenizer(s, "-", false);
		System.out.print(tokens.countTokens());
		while (tokens.hasMoreTokens()) {
			System.out.print("::" + tokens.nextToken());
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		System.out.println("------------");
		split("xxx-yyy-zzz");		// ==> 3::xxx::yyy::zzz
		split("-yyy-zzz");			// ==> 3::::yyy::zzz
		split("xxx-yyy-");			// ==> 2::xxx::yyy
		split("-yyy-");				// ==> 2::::yyy
		split("--");				// ==> 0
		split("");					// ==> 1::
		System.out.println("------------");
		tokenize("xxx-yyy-zzz");	// ==> 3::xxx::yyy::zzz
		tokenize("-yyy-zzz");		// ==> 2::yyy::zzz
		tokenize("xxx-yyy-");		// ==> 2::xxx::yyy
		tokenize("-yyy-");			// ==> 1::yyy
		tokenize("--");				// ==> 0
		tokenize("");				// ==> 0
		System.out.println("------------");
	}
}

package modern.java21.string;

import java.util.StringTokenizer;

// 원형 유지: 구분자가 문자열의 앞/뒤/전부를 차지할 때 String#split()과 StringTokenizer가
// 토큰 개수를 서로 다르게 센다는 것 자체가 주제라 바꿀 대상이 없다. StringSplit.java와
// 함께 본다.

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

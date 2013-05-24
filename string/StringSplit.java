package string;

import java.util.StringTokenizer;

public class StringSplit {
	
	private void test_java_lang_String_split() {
		String s = "boo:and:foo";
		
		String[] tokens = s.split(":"); // re
		System.out.print(tokens.length); // 3
		for (int i = 0; i < tokens.length; i++) {
			System.out.print("," + tokens[i]); // ,boo,and,foo
		}
		System.out.println();
		
		tokens = s.split("o"); // re
		System.out.print(tokens.length); // 3
		for (int i = 0; i < tokens.length; i++) {
			System.out.print("," + tokens[i]); // ,b,,:and:f -- 문자열의 끝에 나타나는 구분자들을 잘라내 버린 후 나눈다.
		}
		System.out.println();
		
		tokens = "".split("\\}"); // re
		System.out.println(tokens.length); // 1 -- !!!
	}
	
	private void test_java_util_StringTokenizer() {
		String s = "boo:and:foo";
		
		StringTokenizer tokens = new StringTokenizer(s, ":", false);
		System.out.print(tokens.countTokens()); // 3
		while (tokens.hasMoreTokens()) {
			System.out.print("," + tokens.nextToken()); // ,boo,and,foo
		}
		System.out.println();
		
		tokens = new StringTokenizer(s, "o", false);
		System.out.print(tokens.countTokens());  // 2
		while (tokens.hasMoreTokens()) {
			System.out.print("," + tokens.nextToken()); // ,b,:and:f -- 문자열의 끝에 나타나는 구분자들을 잘라내 버린 후 나누고, 빈 문자열은 제외한다.
		}
		System.out.println();
		
		tokens = new StringTokenizer("", "|", false);
		System.out.println(tokens.countTokens());  // 0
	}
	
	private void test_Nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_java_lang_String_split();
		test_java_util_StringTokenizer();
		test_Nothing();
	}
	
	public static void main(String[] args) {
		StringSplit worker = new StringSplit();
		worker.test();
	}
}

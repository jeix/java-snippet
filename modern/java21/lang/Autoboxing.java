package modern.java21.lang;

// 원형 유지: 오토박싱·언박싱과, Object로 박싱된 값을 원래 타입으로 되돌릴 때
// 캐스팅이 필요하다는 언어 의미론 자체가 주제라 바꿀 대상이 없다.

public class Autoboxing {
	
	public static void main(String[] args) {
		int i = 42;
		Integer boxed = i;
		int unboxed = boxed;
		if (unboxed != 42) System.out.println(unboxed);
		Object o = i;
		unboxed = (Integer) o; // casting required
		if (unboxed != 42) System.out.println(unboxed);
		System.out.println(":wq");
	}
}

package lang;

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

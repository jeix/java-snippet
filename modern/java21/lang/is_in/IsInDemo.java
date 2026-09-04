package modern.java21.lang.is_in;

import static modern.java21.lang.is_in.IsIn.isin;

public class IsInDemo {
	public static void assertEquals(boolean expected, boolean result, String comment) {
		if (expected != result) System.out.println("[" + String.valueOf(result) + "] is not expected [" + String.valueOf(expected) + "] " + comment);
	}

	public static void main(String[] args) {
		System.out.println("--------------------");
		assertEquals(true, IsIn.is_in("foo", "foo,bar,baz"), "IsIn.is_in(s, csv)");
		assertEquals(true, IsIn.is_in("foo", "foo","bar","baz"), "IsIn.is_in(s, ...)");
		assertEquals(false, IsIn.is_in("zoo", "foo,bar,baz"), "IsIn.is_in(s, csv)");
		assertEquals(false, IsIn.is_in("zoo", "foo","bar","baz"), "IsIn.is_in(s, ...)");
		System.out.println("----------");
		assertEquals(true, IsIn.is_in(100, "100,200,400"), "IsIn.is_in(i, csv)");
		assertEquals(true, IsIn.is_in(100, 100,200,400), "IsIn.is_in(i, ...)");
		assertEquals(false, IsIn.is_in(300, "100,200,400"), "IsIn.is_in(i, csv)");
		assertEquals(false, IsIn.is_in(300, 100,200,400), "IsIn.is_in(i, ...)");
		assertEquals(false, IsIn.is_in(10, "100,200,400"), "exact csv token");
		assertEquals(false, IsIn.is_in(100, "not-a-number,100x"), "invalid numeric token");
		assertEquals(true, IsIn.is_in(1000, 1000,2000,4000), "boxed value equality");
		System.out.println("--------------------");
		assertEquals(true, isin("foo", "foo,bar,baz"), "isin.(s, csv)");
		assertEquals(true, isin("foo", "foo","bar","baz"), "isin.(s, ...)");
		assertEquals(false, isin("zoo", "foo,bar,baz"), "isin.(s, csv)");
		assertEquals(false, isin("zoo", "foo","bar","baz"), "isin.(s, ...)");
		System.out.println("----------");
		assertEquals(true, isin(100, "100,200,400"), "isin.(i, csv)");
		assertEquals(true, isin(100, 100,200,400), "isin.(i, ...)");
		assertEquals(false, isin(300, "100,200,400"), "isin.(i, csv)");
		assertEquals(false, isin(300, 100,200,400), "isin.(i, ...)");
		System.out.println("----------");
		assertEquals(true, isin(100L, "100,200,400"), "isin.(l, csv)");
		assertEquals(true, isin(100L, 100L,200L,400L), "isin.(l, ...)");
		assertEquals(false, isin(300L, "100,200,400"), "isin.(l, csv)");
		assertEquals(false, isin(300L, 100L,200L,400L), "isin.(l, ...)");
		System.out.println("----------");
		assertEquals(true, isin(10.0f, "10.0,20.0,40.0"), "isin.(f, csv)");
		assertEquals(true, isin(10.0f, 10.0f,20.0f,40.0f), "isin.(f, ...)");
		assertEquals(false, isin(30.0f, "10.0,20.0,40.0"), "isin.(f, csv)");
		assertEquals(false, isin(30.0f, 10.0f,20.0f,40.0f), "isin.(f, ...)");
		System.out.println("----------");
		assertEquals(true, isin(10.0, "10.0,20.0,40.0"), "isin.(d, csv)");
		assertEquals(true, isin(10.0, 10.0,20.0,40.0), "isin.(d, ...)");
		assertEquals(false, isin(30.0, "10.0,20.0,40.0"), "isin.(d, csv)");
		assertEquals(false, isin(30.0, 10.0,20.0,40.0), "isin.(d, ...)");
		System.out.println("----------");
		assertEquals(true, isin("foo", " foo , bar "), "trimmed csv token");
		assertEquals(false, isin("foo", "foobar,baz"), "not a substring");
		assertEquals(true, isin("", ",bar"), "explicit empty csv token");
		assertEquals(false, isin((Object) null, "foo,bar"), "null is absent from csv");
		assertEquals(true, isin((Object) null, new Object[] {null, "foo"}), "explicit null array member");
		System.out.println("--------------------");
	}
}

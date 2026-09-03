package lang.void_;

import static lang.void_.NullProof.Nada.*;

import java.sql.Date;

// assert requires VM option -ea

public class NullProof {
	
	static class Nada {

		public static String trim(String s) {
			return (s != null ? s.trim() : "");
		}
		
		public static String string(Date date) {
			return (date != null ? date.toString() : "");
		}
		
		public static String nada(String s, String... alters) {
			if (s != null && s.length() > 0) return s;
			for (String t : alters) {
				if (t != null && t.length() > 0) return t;
			}
			return "";
		}
		
		public static String concat(String s, String... args) {
			StringBuffer sb = new StringBuffer();
			sb.append(s != null ? s : "");
			for (String t : args) {
				if (t != null && t.length() > 0) sb.append(t);
			}
			return sb.toString();
		}
	}

	class Nil<T> {
		
		private T v;
		
		public Nil(T v) {
			this.v = v;
		}
		
		public String trim() {
			return this.string().trim();
		}
		
		public String string() {
			if (v != null) {
				return v.toString();
			} else {
				return "";
			}
		}
		
		public String concat(Object... args) {
			StringBuffer sb = new StringBuffer();
			sb.append(string());
			for (Object o : args) {
				if (o != null) sb.append(o);
			}
			return sb.toString();
		}
		
		@SuppressWarnings("unchecked")
		public int compareTo(T o) {
			if (v != null) {
				if (o == null) {
					return 1;
				} else if (v instanceof Comparable) {
					return ((Comparable<T>) v).compareTo(o);
				} else {
					return string().compareTo(o.toString());
				}
			} else {
				if (o != null) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}
	
	private void test_nada() {
		String s = null;
		String t = null;
		String res = nada(trim(s), trim(t), "N");
		assert res.equals("N");
		
		Date dt1 = null;
		String dt2 = null;
		res = nada(string(dt1), trim(dt2), "2012-04-24");
		assert res.equals("2012-04-24");
		
		dt1 = Date.valueOf("2012-04-24");
		dt2 = "2011-07-24";
		assert string(dt1).compareTo(dt2) > 0;
		
		res = "N".concat(nada(null));
		assert res.equals("N");
		
		res = concat(s, "", res, null, "N");
		assert res.equals("NN");
	}
	
	private void test_nil() {
		String s = null;
		Date dt = Date.valueOf("2012-04-24");
		
		Nil<String> s1 = new Nil<String>(s);
		assert s1.trim().equals("");
		assert s1.concat(dt, s, "N", 3, false).equals("2012-04-24N3false");
		
		dt = null;
		Nil<Date> dt1 = new Nil<Date>(dt);
		assert dt1.string().equals("");
		assert dt1.compareTo(Date.valueOf("2012-04-24")) < 0;
		assert dt1.string().compareTo("2011-07-24") < 0;
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_nada();
		test_nil();
		test_nothing();
	}
	
	public static void main(String[] args) {
		NullProof worker = new NullProof();
		worker.test();
	}
}
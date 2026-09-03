package modern.java21.lang.void_;

import static modern.java21.lang.void_.NullProof.Nada.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

// assert requires VM option -ea

public class NullProof {

	static class Nada {

		public static String trim(String s) {
			return Optional.ofNullable(s).map(String::trim).orElse("");
		}

		public static String string(LocalDate date) {
			return Optional.ofNullable(date).map(LocalDate::toString).orElse("");
		}

		public static String nada(String s, String... alters) {
			if (s != null && ! s.isEmpty()) return s;
			return Stream.of(alters)
					.filter(t -> t != null && ! t.isEmpty())
					.findFirst()
					.orElse("");
		}

		public static String concat(String s, String... args) {
			StringBuilder sb = new StringBuilder(s != null ? s : "");
			for (String t : args) {
				if (t != null && ! t.isEmpty()) sb.append(t);
			}
			return sb.toString();
		}
	}

	class Nil<T> {

		private final Optional<T> value;

		public Nil(T v) {
			this.value = Optional.ofNullable(v);
		}

		public String trim() {
			return this.string().trim();
		}

		public String string() {
			return value.map(Object::toString).orElse("");
		}

		public String concat(Object... args) {
			StringBuilder sb = new StringBuilder(string());
			for (Object o : args) {
				if (o != null) sb.append(o);
			}
			return sb.toString();
		}

		@SuppressWarnings("unchecked")
		public int compareTo(T o) {
			return value.map(v -> {
				if (o == null) return 1;
				if (v instanceof Comparable) return ((Comparable<T>) v).compareTo(o);
				return v.toString().compareTo(o.toString());
			}).orElseGet(() -> o != null ? -1 : 0);
		}
	}

	private void test_nada() {
		String s = null;
		String t = null;
		String res = nada(trim(s), trim(t), "N");
		assert res.equals("N");

		LocalDate dt1 = null;
		String dt2 = null;
		res = nada(string(dt1), trim(dt2), "2012-04-24");
		assert res.equals("2012-04-24");

		dt1 = LocalDate.parse("2012-04-24");
		dt2 = "2011-07-24";
		assert string(dt1).compareTo(dt2) > 0;

		res = "N".concat(nada(null));
		assert res.equals("N");

		res = concat(s, "", res, null, "N");
		assert res.equals("NN");
	}

	private void test_nil() {
		String s = null;
		LocalDate dt = LocalDate.parse("2012-04-24");

		Nil<String> s1 = new Nil<String>(s);
		assert s1.trim().equals("");
		assert s1.concat(dt, s, "N", 3, false).equals("2012-04-24N3false");

		dt = null;
		Nil<LocalDate> dt1 = new Nil<LocalDate>(dt);
		assert dt1.string().equals("");
		assert dt1.compareTo(LocalDate.parse("2012-04-24")) < 0;
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

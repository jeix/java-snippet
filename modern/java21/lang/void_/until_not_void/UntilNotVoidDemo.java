package modern.java21.lang.void_.until_not_void;

import java.math.BigDecimal;

import static modern.java21.lang.void_.until_not_void.UntilNotVoid.unv;
import static modern.java21.test.Expect.expect;

public class UntilNotVoidDemo {

	static class Something {
		private String val;
		public Something(String val) {
			this.val = val;
		}
		public String toString() {
			return val;
		}
	}

	public static void main(String[] args) {
		String s = null;
		BigDecimal n = null;
		int i = -1;
		long l = -1L;
		float f = -1.0f;
		double d = -1.0;
		boolean b = false;
		System.out.println("--------------------");
		s = UntilNotVoid.until_not_void(null, "", "foo");
		expect("foo", s);
		s = UntilNotVoid.until_not_void("foo", "");
		expect("foo", s);
		s = UntilNotVoid.until_not_void(null, "");
		expect("", s);
		System.out.println("----------");
		n = UntilNotVoid.until_not_void(null, BigDecimal.ZERO, BigDecimal.TEN);
		expect(BigDecimal.TEN, n);
		n = UntilNotVoid.until_not_void(BigDecimal.ONE, BigDecimal.ZERO);
		expect(BigDecimal.ONE, n);
		n = UntilNotVoid.until_not_void(null, BigDecimal.ZERO);
		expect(BigDecimal.ZERO, n);
		System.out.println("--------------------");
		// unv(...)는 오버로드 해석으로 정확한 타입을 돌려주기 때문에 캐스팅이 필요 없다.
		s = unv(null, "", "foo");
		expect("foo", s);
		s = unv("foo", "");
		expect("foo", s);
		s = unv(null, "");
		expect("", s);
		System.out.println("----------");
		i = unv(null, 0, 10);
		expect(10, i);
		i = unv(1, 0);
		expect(1, i);
		i = unv(null, 0);
		expect(0, i);
		System.out.println("----------");
		l = unv(null, 0L, 10L);
		expect(10L, l);
		l = unv(1L, 0L);
		expect(1L, l);
		l = unv(null, 0L);
		expect(0L, l);
		System.out.println("----------");
		f = unv(null, 0.0f, 10.0f);
		expect(10.0f, f);
		f = unv(1.0f, 0.0f);
		expect(1.0f, f);
		f = unv(null, 0.0f);
		expect(0.0f, f);
		System.out.println("----------");
		d = unv(null, 0.0, 10.0);
		expect(10.0, d);
		d = unv(1.0, 0.0);
		expect(1.0, d);
		d = unv(null, 0.0);
		expect(0.0, d);
		System.out.println("----------");
		b = unv(null, false, true);
		expect(true, b);
		b = unv(true, false);
		expect(true, b);
		b = unv(null, false);
		expect(false, b);
		System.out.println("----------");
		n = unv(null, BigDecimal.ZERO, BigDecimal.TEN);
		expect(BigDecimal.TEN, n);
		n = unv(BigDecimal.ONE, BigDecimal.ZERO);
		expect(BigDecimal.ONE, n);
		n = unv(null, BigDecimal.ZERO);
		expect(BigDecimal.ZERO, n);
		System.out.println("----------");
		Object o = null;
		o = (Something) unv(null, new Something("foo"), new Something("bar"));
		expect("foo", o.toString());
		o = (Something) unv(new Something("foo"), new Something("bar"));
		expect("foo", o.toString());
		o = (Something) unv(null, new Something("foo"));
		expect("foo", o.toString());
		System.out.println("--------------------");
	}
}

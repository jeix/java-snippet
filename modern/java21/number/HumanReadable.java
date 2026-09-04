package modern.java21.number;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class HumanReadable {

	private static final String[] SI_PREFIXES = {"", "k", "M", "G", "T"};
	private static final String[] KR_PREFIXES = {"", "만", "억", "조", "경"};

	public static String filesize(long size) {
		var d = 1.0d * size;
		var e = 0;
		while (d >= 1024) {
			d /= 1024;
			e += 1;
		}
		NumberFormat nf = new DecimalFormat("#,##0.#");
		nf.setMaximumFractionDigits(2);
		var sb = new StringBuffer();
		sb.append(nf.format(d)).append(SI_PREFIXES[e]).append("B");
		return sb.toString();
	}

	private void testHumanReadableFileSize() {
		System.out.println(HumanReadable.filesize(1L));
		System.out.println(HumanReadable.filesize(12L));
		System.out.println(HumanReadable.filesize(123L));
		System.out.println(HumanReadable.filesize(1234L));
		System.out.println(HumanReadable.filesize(12345L));
		System.out.println(HumanReadable.filesize(123456L));
		System.out.println(HumanReadable.filesize(1234567L));
		System.out.println(HumanReadable.filesize(12345678L));
		System.out.println(HumanReadable.filesize(123456789L));
		System.out.println(HumanReadable.filesize(1234567890L));
	}

	public static String numberKr(long n) {
		var s = String.valueOf(n);
		var list = new ArrayList<String>();
		while (s.length() > 4) {
			list.add(s.substring(s.length() - 4));
			s = s.substring(0, s.length() - 4);
		}
		if (!s.isEmpty()) {
			list.add(s);
		}
		var sb = new StringBuffer();
		for (var i = list.size() - 1; i >= 0; i--) {
			sb.append(list.get(i)).append(KR_PREFIXES[i]);
			if (i > 0) sb.append(" ");
		}
		return sb.toString();
	}

	private void testHumanReadableNumberKr() {
		System.out.println(HumanReadable.numberKr(1L));
		System.out.println(HumanReadable.numberKr(12L));
		System.out.println(HumanReadable.numberKr(123L));
		System.out.println(HumanReadable.numberKr(1234L));
		System.out.println(HumanReadable.numberKr(12345L));
		System.out.println(HumanReadable.numberKr(123456L));
		System.out.println(HumanReadable.numberKr(1234567L));
		System.out.println(HumanReadable.numberKr(12345678L));
		System.out.println(HumanReadable.numberKr(123456789L));
		System.out.println(HumanReadable.numberKr(1234567890L));
	}

	private void testNothing() {
		System.out.println(":wq");
	}

	void test() {
		testHumanReadableFileSize();
		testHumanReadableNumberKr();
		testNothing();
	}

	public static void main(String[] args) {
		var worker = new HumanReadable();
		worker.test();
	}
}
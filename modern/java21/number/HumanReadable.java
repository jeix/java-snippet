package modern.java21.number;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HumanReadable {

	private static final List<String> SI_PREFIXES = List.of("", "k", "M", "G", "T", "P", "E");

	public static String filesize(long size) {
		double d = 1.0d * size;
		int e = 0;
		while (d >= 1024) {
			d /= 1024;
			e += 1;
		}
		NumberFormat nf = new DecimalFormat("#,##0.#", DecimalFormatSymbols.getInstance(Locale.ROOT));
		nf.setMaximumFractionDigits(2);
		return nf.format(d) + SI_PREFIXES.get(e) + "B";
	}

	private void test_human_readable_file_size() {
		System.out.println(HumanReadable.filesize(1L));          // 1B
		System.out.println(HumanReadable.filesize(12L));         // 12B
		System.out.println(HumanReadable.filesize(123L));        // 123B
		System.out.println(HumanReadable.filesize(1234L));       // 1.21kB
		System.out.println(HumanReadable.filesize(12345L));      // 12.06kB
		System.out.println(HumanReadable.filesize(123456L));     // 120.56kB
		System.out.println(HumanReadable.filesize(1234567L));    // 1.18MB
		System.out.println(HumanReadable.filesize(12345678L));   // 11.77MB
		System.out.println(HumanReadable.filesize(123456789L));  // 117.74MB
		System.out.println(HumanReadable.filesize(1234567890L)); // 1.15GB
	}

	private static final List<String> KR_PREFIXES = List.of("", "만", "억", "조", "경");

	public static String number_kr(long n) {
		String s = String.valueOf(n);
		List<String> list = new ArrayList<>();
		while (s.length() > 4) {
			list.add(s.substring(s.length()-4));
			s = s.substring(0, s.length()-4);
		}
		if (s.length() > 0) {
			list.add(s);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = list.size() - 1; i >= 0; i--) {
			sb.append(list.get(i)).append(KR_PREFIXES.get(i));
			if (i > 0) sb.append(" ");
		}
		return sb.toString();
	}

	private void test_human_readable_number_kr() {
		System.out.println(HumanReadable.number_kr(1L));          // 1
		System.out.println(HumanReadable.number_kr(12L));         // 12
		System.out.println(HumanReadable.number_kr(123L));        // 123
		System.out.println(HumanReadable.number_kr(1234L));       // 1234
		System.out.println(HumanReadable.number_kr(12345L));      // 1만 2345
		System.out.println(HumanReadable.number_kr(123456L));     // 12만 3456
		System.out.println(HumanReadable.number_kr(1234567L));    // 123만 4567
		System.out.println(HumanReadable.number_kr(12345678L));   // 1234만 5678
		System.out.println(HumanReadable.number_kr(123456789L));  // 1억 2345만 6789
		System.out.println(HumanReadable.number_kr(1234567890L)); // 12억 3456만 7890
	}

	private void test_Nothing() {
		System.out.println(":wq");
	}

	public void test() {
		test_human_readable_file_size();
		test_human_readable_number_kr();
		test_Nothing();
	}

	public static void main(String[] args) {
		HumanReadable worker = new HumanReadable();
		worker.test();
	}
}

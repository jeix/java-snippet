package number;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class HumanReadable {
	
	private static String[] si_prefixes = {"", "k", "M", "G", "T"};
	
	public static String filesize(long size) {
		double d = 1.0d * size;
		int e = 0;
		while (d >= 1024) {
			d /= 1024;
			e += 1;
		}
		NumberFormat nf = new DecimalFormat("#,##0.#");
		nf.setMaximumFractionDigits(2);
		StringBuffer sb = new StringBuffer();
		sb.append(nf.format(d)).append(si_prefixes[e]).append("B");
		return sb.toString();
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
	
	private static String[] kr_prefixes = {"", "만", "억", "조", "경"};
	
	public static String number_kr(long n) {
		String s = String.valueOf(n);
		List<String> list = new ArrayList<String>();
		while (s.length() > 4) {
			list.add(s.substring(s.length()-4));
			s = s.substring(0, s.length()-4);
		}
		if (s.length() > 0) {
			list.add(s);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = list.size() - 1; i >= 0; i--) {
			sb.append(list.get(i)).append(kr_prefixes[i]);
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

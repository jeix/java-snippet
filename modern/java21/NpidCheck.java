package modern.java21;

import java.util.stream.IntStream;

public class NpidCheck {

	private static final int[] MULTIPLIERS = {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5};
	private static final int PRIME_NUMBER = 11;

	public static boolean is_valid_jumin_no(String jumin_no) {
		if (jumin_no == null) return false;

		jumin_no = jumin_no.replace("-", "");

		if (jumin_no.length() != 13) return false;

		if (is_foreign_jumin_no(jumin_no)) {
			return is_valid_foreign_jumin_no(jumin_no);
		}

		final String no = jumin_no;
		int sum = IntStream.range(0, MULTIPLIERS.length)
				.map(i -> MULTIPLIERS[i] * Character.getNumericValue(no.charAt(i)))
				.sum();
		int remainder = (sum % PRIME_NUMBER) % 10;

		int chk_digit = (PRIME_NUMBER - Character.getNumericValue(no.charAt(no.length() - 1))) % 10;

		return remainder == chk_digit;
	}

	private static boolean is_foreign_jumin_no(String jumin_no) {
		return "5678".contains(jumin_no.substring(6, 7));
	}

	private static boolean is_valid_foreign_jumin_no(String jumin_no) {
		int[] numericalized = IntStream.range(0, 13)
				.map(i -> Character.getNumericValue(jumin_no.charAt(i)))
				.toArray();
		if (numericalized[8] % 2 == 0 && 6 <= numericalized[11] && numericalized[11] <= 9) {
			int sum = IntStream.range(0, MULTIPLIERS.length)
					.map(i -> MULTIPLIERS[i] * numericalized[i])
					.sum();
			int remainder = (PRIME_NUMBER - (sum % PRIME_NUMBER) + 2) % 10;
			int chk_digit = numericalized[12];
			return remainder == chk_digit;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		String npids = """
				7701202821514
				820225-2026319
				8206302695713
				780404-2898811
				7610031520611
				700120-1031221
				7511101538624
				751110-1538619
				801106-5120594""";
		npids.lines()
				.filter(npid -> ! is_valid_jumin_no(npid))
				.forEach(System.out::println);
	}
}

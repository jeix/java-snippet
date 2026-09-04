package modern.java21;

import java.util.regex.Pattern;

public class NpidCheck {
	private static final int[] MULTIPLIERS = {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5};
	private static final int PRIME_NUMBER = 11;
	private static final int NPID_LENGTH = 13;
	private static final Pattern NPID_PATTERN = Pattern.compile("(?:[0-9]{13}|[0-9]{6}-[0-9]{7})");

	public static boolean is_valid_jumin_no(String jumin_no) {
		if (jumin_no == null) return false;
		if (!NPID_PATTERN.matcher(jumin_no).matches()) return false;
		jumin_no = jumin_no.replace("-", "");

		if (is_foreign_jumin_no(jumin_no)) {
			return is_valid_foreign_jumin_no(jumin_no);
		}

		int sum = 0;
		for (int i = 0; i < MULTIPLIERS.length; i++) {
			sum += MULTIPLIERS[i] * digit_at(jumin_no, i);
		}
		int remainder = sum % PRIME_NUMBER;
		remainder = remainder % 10;

		int chk_digit = PRIME_NUMBER - digit_at(jumin_no, jumin_no.length() - 1);
		chk_digit = chk_digit % 10;

		return (remainder == chk_digit);
	}

	private static boolean is_foreign_jumin_no(String jumin_no) {
		return "5678".indexOf(jumin_no.charAt(6)) >= 0;
	}

	private static boolean is_valid_foreign_jumin_no(String jumin_no) {
		int[] numericalized = new int[NPID_LENGTH];
		for (int i = 0; i < NPID_LENGTH; i++) {
			numericalized[i] = digit_at(jumin_no, i);
		}
		if (numericalized[8] % 2 == 0 && 6 <= numericalized[11] && numericalized[11] <= 9) {
			int sum = 0;
			for (int i = 0; i < MULTIPLIERS.length; i++) {
				sum += MULTIPLIERS[i] * numericalized[i];
			}
			int remainder = PRIME_NUMBER - (sum % PRIME_NUMBER) + 2;
			remainder = remainder % 10;
			int chk_digit = numericalized[12];
			return (remainder == chk_digit);
		} else {
			return false;
		}
	}

	private static int digit_at(String value, int index) {
		return value.charAt(index) - '0';
	}

	public static void main(String[] args) {
		String[] valid_npids = {
			"7701202821514",
			"820225-2026319",
			"8206302695713",
			"780404-2898811",
			"7610031520611",
			"700120-1031221",
			"7511101538624",
			"751110-1538619",
			"801106-5120594"
		};
		for (String npid : valid_npids) {
			if (!NpidCheck.is_valid_jumin_no(npid)) System.out.println(npid);
		}

		String[] invalid_npids = {null, "", "123", "ABCDEFGHIJKLM", "770120282151X", "770120--2821514"};
		for (String npid : invalid_npids) {
			if (NpidCheck.is_valid_jumin_no(npid)) System.out.println(npid);
		}
	}
}

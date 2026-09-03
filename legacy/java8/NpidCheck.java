
public class NpidCheck {
	
	public static boolean is_valid_jumin_no(String jumin_no) {
		if (jumin_no == null) return false;
		
		jumin_no = jumin_no.replace("-", "");
		
		if (jumin_no.length() != 13) return false;
		
		if (is_foreign_jumin_no(jumin_no)) {
			return is_valid_foreign_jumin_no(jumin_no);
		}
		
		int[] MULTIPLIERS =  new int[] {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5};
		int PRIME_NUMBER = 11;
		
		int sum = 0;
		for (int i = 0; i < MULTIPLIERS.length; i++) {
			sum += MULTIPLIERS[i] * Integer.parseInt(jumin_no.substring(i, i + 1));
		}
		int remainder = sum % PRIME_NUMBER;
		remainder = remainder % 10;
		
		int chk_digit = PRIME_NUMBER - Integer.parseInt(jumin_no.substring(jumin_no.length() - 1));
		chk_digit = chk_digit % 10;
		
		return (remainder == chk_digit);
	}
	
	private static boolean is_foreign_jumin_no(String jumin_no) {
		return ("5678".contains(jumin_no.substring(6, 7)));
	}
	
	private static boolean is_valid_foreign_jumin_no(String jumin_no) {
		int[] numericalized = new int[13];
		for (int i = 0; i < 13; i++) {
			numericalized[i] = Integer.parseInt(jumin_no.substring(i, i + 1));
		}
		if (numericalized[8] % 2 == 0 && 6 <= numericalized[11] && numericalized[11] <= 9) {
			int[] MULTIPLIERS =  new int[] {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5};
			int PRIME_NUMBER = 11;
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
	
	public static void main(String[] args) {
		String npid = null;
		npid = "7701202821514";
		if (! NpidCheck.is_valid_jumin_no(npid)) {
			System.out.println(npid);
		}
		npid = "820225-2026319";
		if (! NpidCheck.is_valid_jumin_no(npid)) {
			System.out.println(npid);
		}
		npid = "8206302695713";
		if (! NpidCheck.is_valid_jumin_no(npid)) {
			System.out.println(npid);
		}
		npid = "780404-2898811";
		if (! NpidCheck.is_valid_jumin_no(npid)) {
			System.out.println(npid);
		}
		npid = "7610031520611";
		if (! NpidCheck.is_valid_jumin_no(npid)) {
			System.out.println(npid);
		}
		npid = "700120-1031221";
		if (! NpidCheck.is_valid_jumin_no(npid)) {
			System.out.println(npid);
		}
		npid = "7511101538624";
		if (! NpidCheck.is_valid_jumin_no(npid)) {
			System.out.println(npid);
		}
		npid = "751110-1538619";
		if (! NpidCheck.is_valid_jumin_no(npid)) {
			System.out.println(npid);
		}
		npid = "801106-5120594";
		if (! NpidCheck.is_valid_jumin_no(npid)) {
			System.out.println(npid);
		}
	}
}
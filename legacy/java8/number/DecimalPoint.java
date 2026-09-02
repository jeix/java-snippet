package number;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static test.Expect.expect;

public class DecimalPoint {
	
	private static final String MINUS_SIGN = "-";
	private static final String POINT = ".";
	private static final String ZERO = "0";
	private static final String EMPTY = "";
	
	private String sign;
	private String integer;
	private String fraction;
	
	private boolean empty_for_zero = false;
	
	public DecimalPoint(String val) {
		if (val.indexOf(MINUS_SIGN) == 0) {
			sign = MINUS_SIGN;
			val = val.substring(MINUS_SIGN.length());
		} else {
			sign = "";
		}
		int posPoint = val.indexOf(POINT);
		if (posPoint >= 0) {
			integer = val.substring(0, posPoint);
			if (posPoint < val.length() - 1) {
				fraction = val.substring(posPoint + 1);
				cutInvalidFraction();
			} else {
				fraction = "";
			}
		} else {
			integer = val.substring(0);
			fraction = "";
		}
		cutInvalidInteger();
		if (EMPTY.equals(integer)) {
			integer = ZERO;
		}
	}
	
	/**
	 * 무효한 소수점 이하를 잘라낸다<br/>
	 * <br/>
	 * 사실 무효한 소수점 이하를 어떻게 정의할 것인가의 문제가 있다.<br/>
	 * 89.0의 0은 유효한가 유효하지 않은가?<br/>
	 * (처음 이 유틸리티를 요구한 문맥에서는 무효했다)
	 */
	private void cutInvalidFraction() {
		if (fraction != null && fraction.length() > 0) {
			int posCut = fraction.length();
			while (posCut > 0 && fraction.charAt(posCut-1) == '0') {
				posCut = posCut - 1;
			}
			if (posCut > 0) {
				fraction = fraction.substring(0, posCut);
			} else {
				fraction = "";
			}
		}
	}
	
	/**
	 * 정수부에서 무효한 부분을 잘라낸다
	 */
	private void cutInvalidInteger() {
		if (integer != null && integer.length() > 1) {
			int posCut = 0;
			int posEnd = integer.length() - 2;
			while (posCut <= posEnd && integer.charAt(posCut) == '0') {
				posCut = posCut + 1;
			}
			integer = integer.substring(posCut);
		}
	}
	
	/**
	 * 값
	 * @return
	 */
	public String plainValue() {
		StringBuffer sb = new StringBuffer(10);
		if (integer.length() > 0) {
			sb.append(sign);
			sb.append(integer);
			if (fraction.length() > 0) {
				sb.append(POINT);
				sb.append(fraction);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 1,000
	 * @return
	 */
	public String commaValue() {
		return commaValue(fraction.length());
	}
	
	/**
	 * 1,000
	 * @param fractionSize
	 * @return
	 */
	public String commaValue(int fractionSize) {
		if (empty_for_zero && isZero()) return EMPTY;
		StringBuffer sb = new StringBuffer(10);
		if (integer.length() > 0) {
			sb.append(sign);
			try {
				sb.append(comma(Long.parseLong(integer)));
			} catch (NumberFormatException nfe) {
				sb.append(integer);
			}
			if (fractionSize > 0) {
				sb.append(POINT);
				sb.append(fraction(fractionSize));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 억,만 치환
	 * @return
	 */
	public String humanReadable() {
		return humanReadable(fraction.length());
	}
	
	/**
	 * 억,만 치환
	 * @param fractionSize
	 * @return
	 */
	public String humanReadable(int fractionSize) {
		if (empty_for_zero && isZero()) return EMPTY;
		StringBuffer sb = new StringBuffer(10);
		if (integer.length() > 0) {
			sb.append(sign);
			sb.append(humanReadableInteger());
			if (fractionSize > 0) {
				sb.append(POINT);
				sb.append(fraction(fractionSize));
			}
		}
		return sb.toString();
	}
	
	private static long[] SCALES = {100000000L, 10000L};
	private static String[] KR_PREFIXES = {"억", "만"};
	
	private static class Number {
		private static long ZERO = 0L;
		private static long THOUSNAD = 1000L;
		private static long HUNDRED = 100L;
		private static long TEN = 10L;
	}
	
	/**
	 * 억,만 치환된 정수부
	 * @return
	 */
	private String humanReadableInteger() {
		try {
			long num = Long.parseLong(integer);
			if (num == Number.ZERO) return ZERO;
			StringBuffer sb = new StringBuffer(15);
			for (int i = 0; i < SCALES.length; i++) {
				if (num >= SCALES[i]) {
					long div = num / SCALES[i];
					num = num - div * SCALES[i];
					if (div > Number.ZERO) {
						sb.append(comma(div)).append(KR_PREFIXES[i]);
					}
				}
			}
			if (num > Number.ZERO) {
				if (num >= Number.THOUSNAD) {
                    sb.append(comma(num));
                } else {
                    sb.append(num);
                }
			}
			return sb.toString();
		} catch (NumberFormatException nfe) {
			return integer;
		}
	}
	
	/**
	 * 1,000
	 * @param val
	 * @return
	 */
	private String comma(long val) {
		/* v1
		if (val == Number.ZERO) return ZERO;
		List<Long> csv = new ArrayList<Long>();
		while (val > Number.THOUSNAD) {
			long div = val / Number.THOUSNAD;
			long remain = val - div * Number.THOUSNAD;
			csv.add(remain);
			val = div;
		}
		if (val > Number.ZERO) {
			csv.add(val);
		}
		StringBuffer sb = new StringBuffer(10);
		int begin = csv.size() - 1;
		for (int i = begin; i >= 0; i--) {
			long part = csv.get(i);
			if (i < begin) {
				if (part < Number.HUNDRED) sb.append(ZERO);
				if (part < Number.TEN) sb.append(ZERO);
			}
			sb.append(part);
			if (i > 0) {
				sb.append(",");
			}
		}
		return sb.toString();
		//*/
		/* v2
		StringBuffer sb = new StringBuffer(10);
		sb.append(val).reverse();
		int end = sb.length();
		for (int i = 3; i < end; i = i + 3) {
			sb.insert(i, ",");
			i = i + 1;
			end = end + 1;
		}
		return sb.reverse().toString();
		//*/
		//* v3
		return NumberFormat.getInstance().format(val);
		//*/
	}
	
	/**
	 * 소숫점 자리수 적용
	 * @param size
	 * @return
	 */
	private String fraction(int size) {
		/* v1
		if (fraction.length() == size) {
			return fraction;
		} else if (fraction.length() > size) {
			return fraction.substring(0, size); // 버림 (not 반올림)
		} else {
			int sizeDiff = size - fraction.length();
			return new StringBuffer(size).append(fraction).append(repeatZero(sizeDiff)).toString();
		}
		//*/
		//* v2
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(size);
		nf.setMinimumFractionDigits(size);
		return nf.format(Double.parseDouble(ZERO + POINT + fraction)).substring(2);
		//*/
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	private String repeatZero(int n) {
		if (n <= 0) return "";
		StringBuffer sb = new StringBuffer(n);
		for (int i = 0; i < n; i++) {
			sb.append(ZERO);
		}
		return sb.toString();
	}
	
	/**
	 *
	 */
	private boolean isZero() {
		if (ZERO.equals(integer) || EMPTY.equals(integer)) {
			if (ZERO.equals(fraction) || EMPTY.equals(fraction)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 0 대신 ""을 리턴하도록 설정
	 */
	public DecimalPoint preferEmptyToZero() {
		empty_for_zero = true;
		return this;
	}
	
	public static void main(String[] args) {
		expect("100", new DecimalPoint("00100").plainValue());
		
		expect("1,200.1", new DecimalPoint("001200.1").commaValue());
		expect("1,200.10", new DecimalPoint("001200.1").commaValue(2));
		expect("1,020", new DecimalPoint("001020").commaValue());
		expect("1,001,020.00", new DecimalPoint("1001020").commaValue(2));
		expect("11.4", new DecimalPoint("11.45").commaValue(1));
		
		expect("1억4,400만", new DecimalPoint("144000000").humanReadable());
		expect("1억4,400만.0", new DecimalPoint("144000000").humanReadable(1));
		expect("1억4,040만", new DecimalPoint("140400000").humanReadable());
		expect("1억40만", new DecimalPoint("100400000").humanReadable());
		expect("1억400만1,200", new DecimalPoint("104001200").humanReadable());
		
		expect("0", new DecimalPoint("0").plainValue());
		expect("0", new DecimalPoint("0").commaValue());
		expect("0.00", new DecimalPoint("0").commaValue(2));
		expect("0", new DecimalPoint("0").humanReadable());
		expect("0.00", new DecimalPoint("0").humanReadable(2));

		expect("0", new DecimalPoint(".0").plainValue());
		expect("0", new DecimalPoint(".0").commaValue());
		expect("0.00", new DecimalPoint(".0").commaValue(2));
		expect("0", new DecimalPoint(".0").humanReadable());
		expect("0.00", new DecimalPoint(".0").humanReadable(2));
		
		expect("", new DecimalPoint("0").preferEmptyToZero().commaValue());
		expect("", new DecimalPoint("0").preferEmptyToZero().commaValue(2));
		expect("", new DecimalPoint("0").preferEmptyToZero().humanReadable());
		expect("", new DecimalPoint("0").preferEmptyToZero().humanReadable(2));

		expect("", new DecimalPoint(".0").preferEmptyToZero().commaValue());
		expect("", new DecimalPoint(".0").preferEmptyToZero().commaValue(2));
		expect("", new DecimalPoint(".0").preferEmptyToZero().humanReadable());
		expect("", new DecimalPoint(".0").preferEmptyToZero().humanReadable(2));
		
		System.out.println(":wq");
	}
}

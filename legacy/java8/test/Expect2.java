package test;

import java.math.BigDecimal;
import java.sql.Date;

public class Expect2 {
	
	public static Expect2 expect(Object val) {
		Expect2 _exp = new Expect2(val);
		_exp.not = negative(val);
		return _exp;
	}
	
	private static Expect2 negative(Object val) {
		Expect2 _exp = new Expect2(val);
		_exp.negative = true;
		return _exp;
	}
	
	private Object val;
	private boolean negative;
	
	// public -- "Play at Your Own Risk"
	public Expect2 not;
	
	private Expect2(Object val) {
		this.val = val;
	}
	
	public Expect2 nay() {
		negative = ! negative;
		return this;
	}
	
	public void to_equal(Object x) {
		boolean equality = false;
		if (val == x) equality = true;
		else if (val == null && x != null) equality = false;
		else if (val.equals(x)) equality = true;
		
		if (failed(equality)) {
			System.out.println("[" + val + "] != [" + x + "]");
		}
	}
	
	public void to_be(Object x) {
		if (failed(val == x)) {
			System.out.println("[" + val + "] !== [" + x + "]");
		}
	}
	
	// to_match
	// to_be_null
	// to_be_truthy
	// to_be_falsy
	
	public void to_contain(Object x) {
		boolean contain = false;
		for (Object v : (Object[]) val) {
			if (v == x) contain = true;
			else if (v == null && x != null) contain = false;
			else if (v.equals(x)) contain = true;
			
			if (contain) break;
		}
		
		if (failed(contain)) {
			System.out.println("[" + val + "] not contain [" + x + "]");
		}
	}
	
	// to_be_less_than
	// to_be_greater_than
	// to_be_close_to
	
	// to_throw
	
	private boolean failed (boolean result) {
		return (result ^ negative == false);
	}
}
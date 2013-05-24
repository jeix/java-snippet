import java.math.BigDecimal;
import java.sql.Date;

import static test.Expect.expect;

/*
 * test.Expect also used by
 * 
 * collection.ArrayExtendTester
 * lang.void_.until_not_void.UntilNotVoidDemo
 * number.NumberFormatDemo
 * number.DecimalPoint
 */

public class ExpectTest {
	
	public static void main(String[] args) {
		expect("string", "string");
		expect(new BigDecimal(1), BigDecimal.ONE);
		expect(Date.valueOf("2012-07-06"), Date.valueOf("2012-07-06"));
		expect(123, 123);
		
		try {
			Integer.valueOf("0x10");
			expect(NumberFormatException.class);
		} catch (NumberFormatException nfe) {
			expect("java.lang.NumberFormatException", nfe.getClass().getCanonicalName());
		}
		
		System.out.println(":wq");
	}
}
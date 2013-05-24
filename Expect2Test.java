import java.math.BigDecimal;
import java.sql.Date;

import static test.Expect2.expect;

public class Expect2Test {
	
	public static void main(String[] args) {
		expect("string").to_equal("string");
		expect("string").not.to_equal("string~");
		expect(new BigDecimal(1)).to_equal(BigDecimal.ONE);
		expect(new BigDecimal(1)).not.to_equal(BigDecimal.ZERO);
		expect(Date.valueOf("2012-07-06")).to_equal(Date.valueOf("2012-07-06"));
		expect(Date.valueOf("2012-07-06")).not.to_equal(Date.valueOf("2012-07-07"));
		expect(123).to_equal(123);
		expect(123).not.to_equal(1234);
		
		expect(new String[] {"foo","bar","baz"}).to_contain("bar");
		expect(new String[] {"foo","bar","baz"}).not.to_contain("bart");
		expect(new Integer[] {121,122,123}).to_contain(123);
		expect(new Integer[] {121,122,123}).not.to_contain(1234);
		
		System.out.println(":wq");
	}
}
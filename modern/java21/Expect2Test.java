package modern.java21;

import java.math.BigDecimal;
import java.sql.Date;

import static modern.java21.test.Expect2.expect;
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

		expect("hello world").to_match("^hello");
		expect("hello world").not.to_match("^world");

		expect(null).to_be_null();
		expect("not null").not.to_be_null();

		expect("non-empty").to_be_truthy();
		expect(0).not.to_be_truthy();
		expect("").to_be_falsy();
		expect("x").not.to_be_falsy();

		expect(3).to_be_less_than(5);
		expect(5).not.to_be_less_than(3);
		expect(5).to_be_greater_than(3);
		expect(3).not.to_be_greater_than(5);

		expect(3.14159).to_be_close_to(3.14, 2);
		expect(3.14159).not.to_be_close_to(3.0, 2);

		expect((Runnable) () -> { throw new IllegalArgumentException(); }).to_throw(IllegalArgumentException.class);
		expect((Runnable) () -> { }).not.to_throw(IllegalArgumentException.class);

		System.out.println(":wq");
	}
}

package modern.java21;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import modern.java21.test.Expect2.ThrowingRunnable;

import static modern.java21.test.Expect2.expect;

public class Expect2Test {

	public static void main(String[] args) {
		expect("string").to_equal("string");
		expect("string").not.to_equal("string~");
		expect(new BigDecimal(1)).to_equal(BigDecimal.ONE);
		expect(new BigDecimal(1)).not.to_equal(BigDecimal.ZERO);
		expect(LocalDate.of(2012, 7, 6)).to_equal(LocalDate.of(2012, 7, 6));
		expect(LocalDate.of(2012, 7, 6)).not.to_equal(LocalDate.of(2012, 7, 7));
		expect(123).to_equal(123);
		expect(123).not.to_equal(1234);
		expect(null).to_equal(null);

		var same = new Object();
		expect(same).to_be(same);
		expect(same).not.to_be(new Object());

		expect("foo-123").to_match("\\d+");
		expect("foo-123").to_match(Pattern.compile("^foo"));
		expect("foo-123").not.to_match("^bar");

		expect(null).to_be_null();
		expect("not null").not.to_be_null();
		expect(true).to_be_truthy();
		expect("value").to_be_truthy();
		expect(false).to_be_falsy();
		expect(List.of()).to_be_falsy();
		expect(0).not.to_be_truthy();

		expect(new String[] {"foo","bar","baz"}).to_contain("bar");
		expect(new String[] {"foo","bar","baz"}).not.to_contain("bart");
		expect(new Integer[] {121,122,123}).to_contain(123);
		expect(new Integer[] {121,122,123}).not.to_contain(1234);
		expect(new int[] {121,122,123}).to_contain(122);
		expect(List.of("foo", "bar", "baz")).to_contain("baz");
		expect("foo bar baz").to_contain("bar");

		expect(1).to_be_less_than(2L);
		expect("bar").to_be_less_than("foo");
		expect(2L).to_be_greater_than(1);
		expect("foo").to_be_greater_than("bar");
		expect(1).not.to_be_greater_than(2);

		expect(10.001).to_be_close_to(10.0, 0.01);
		expect(10.1).not.to_be_close_to(10.0, 0.01);

		expect((ThrowingRunnable) () -> { throw new IllegalArgumentException("expected"); })
				.to_throw(IllegalArgumentException.class);
		expect((ThrowingRunnable) () -> {}).not.to_throw();

		expect("foo").nay().to_equal("bar");

		System.out.println(":wq");
	}
}

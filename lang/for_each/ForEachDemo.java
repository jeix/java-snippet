package lang.for_each;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForEachDemo {
	
	private void test_for_each() {
		final List<String> xlist = new ArrayList<String>();
		xlist.add("foo");
		xlist.add("bar");
		xlist.add("baz");
		
		new Array<String>(xlist).for_each(new Array.ForEach<String>() {
			public void apply(String param, int index) {
				xlist.set(index, param.toUpperCase());
			}
		});
		
		System.out.println(xlist); // -> [FOO, BAR, BAZ]
	}
	
	private void test_every() {
		List<Integer> numbers = Arrays.asList(0, 1, 2, 3);
		boolean result = new Array<Integer>(numbers).every(new Array.Filter<Integer>() {
			public boolean apply(Integer param, Object... vals) {
				return param % 2 == 0;
			}
		});
		System.out.println(result); // -> false
		
		result = new Array<Integer>(numbers).every(new Array.Filter<Integer>() {
			public boolean apply(Integer param, Object... vals) {
				return param < 5;
			}
		});
		System.out.println(result); // -> true
	}
	
	private void test_some() {
		List<Integer> numbers = Arrays.asList(0, 1, 2, 3);
		boolean result = new Array<Integer>(numbers).some(new Array.Filter<Integer>() {
			public boolean apply(Integer param, Object... vals) {
				return param % 2 == 1;
			}
		});
		System.out.println(result); // -> true
		
		result = new Array<Integer>(numbers).some(new Array.Filter<Integer>() {
			public boolean apply(Integer param, Object... vals) {
				return param > 5;
			}
		});
		System.out.println(result); // -> false
	}
	
	private void test_filter() {
		List<String> xlist = new ArrayList<String>();
		xlist.add("foo");
		xlist.add("bar");
		xlist.add("baz");
		
		List<String> filtered = new Array<String>(xlist).filter(new Array.Filter<String>() {
			public boolean apply(String param, Object... vals) {
				return param.equals((String) vals[0]);
			}
		}, "bar");
		
		System.out.println(filtered); // -> [bar]
	}
	
	private void test_map() {
		List<String> xlist = new ArrayList<String>();
		xlist.add("foo");
		xlist.add("bar");
		xlist.add("baz");
		
		List<String> ylist = new ArrayList<String>();
		
		new Array.Converter<String,String>(xlist, ylist).map(new Array.Map<String,String>() {
			public String apply(String param, Object... vals) {
				return param.toUpperCase();
			}
		});
		
		System.out.println(ylist); // -> [FOO, BAR, BAZ]
	}
	
	private void test_reduce() {
		List<Integer> numbers = Arrays.asList(0, 1, 2, 3);
		
		int sum = new Array.Reducer<Integer,Integer>(numbers).reduce(new Array.Reduce<Integer,Integer>() {
			public Integer apply(Integer sum, Integer number) {
				return sum + number;
			}
		}, 0);
		
		System.out.println(sum); // -> 6
	}
	
	private void test_reduce_2() {
		List<List<Integer>> numbers = new ArrayList<List<Integer>>();
		numbers.add(Arrays.asList(0, 1));
		numbers.add(Arrays.asList(2, 3));
		numbers.add(Arrays.asList(4, 5));
		
		List<Integer> result = new ArrayList<Integer>();
		
		result = new Array.Reducer<List<Integer>,List<Integer>>(numbers).reduce(new Array.Reduce<List<Integer>,List<Integer>>() {
			public List<Integer> apply(List<Integer> dst, List<Integer> src) {
				dst.addAll(src);
				return dst;
			}
		}, result);
		
		System.out.println(result); // -> [0, 1, 2, 3, 4, 5]
	}
	
	private void test_reduce_right() {
		List<Integer> numbers = Arrays.asList(0, 1, 2, 3);
		
		int sum = new Array.Reducer<Integer,Integer>(numbers).reduce_right(new Array.Reduce<Integer,Integer>() {
			public Integer apply(Integer sum, Integer number) {
				return sum + number;
			}
		}, 0);
		
		System.out.println(sum); // -> 6
	}
	
	private void test_reduce_right_2() {
		List<List<Integer>> numbers = new ArrayList<List<Integer>>();
		numbers.add(Arrays.asList(0, 1));
		numbers.add(Arrays.asList(2, 3));
		numbers.add(Arrays.asList(4, 5));
		
		List<Integer> result = new ArrayList<Integer>();
		
		result = new Array.Reducer<List<Integer>,List<Integer>>(numbers).reduce_right(new Array.Reduce<List<Integer>,List<Integer>>() {
			public List<Integer> apply(List<Integer> dst, List<Integer> src) {
				dst.addAll(src);
				return dst;
			}
		}, result);
		
		System.out.println(result); // -> [4, 5, 2, 3, 0, 1]
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_for_each();
		test_every();
		test_some();
		test_filter();
		test_map();
		test_reduce();
		test_reduce_2();
		test_reduce_right();
		test_reduce_right_2();
		test_nothing();
	}
	
	public static void main(String[] args) {
		ForEachDemo worker = new ForEachDemo();
		worker.test();
	}
}
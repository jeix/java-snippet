package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CascadingOptionsBuilderDemo {
	
	class CascadingOptionsBuilder {
		
		private static final String ROOT = "";
		
		private Map<String,Set<String>> kmap;
		private Map<String,List<Option>> map;
		
		public CascadingOptionsBuilder() {
			kmap = new HashMap<String,Set<String>>();
			map = new HashMap<String,List<Option>>();
		}
		
		public void put(String p, Option option) {
			if (option == null || option.getK() == null) return;
			Set<String> ks = kmap.get(p);
			if (ks == null) {
				ks = new HashSet<String>();
				kmap.put(p, ks);
			}
			String k = option.getK();
			if (! ks.contains(k)) {
				ks.add(k);
				add(p, option);
			}
		}
		
		public void add(String p, Option option) {
			List<Option> options = options_for(p);
			if (! options.contains(option)) {
				options.add(option);
			}
		}
		
		public List<Option> options_for(String p) {
			List<Option> options = map.get(p);
			if (options == null) {
				options = new ArrayList<Option>();
				map.put(p, options);
			}
			return options;
		}
		
		public String p(String... ks) {
			StringBuffer sb = new StringBuffer(ROOT);
			int i = 0;
			for (String k : ks) {
				if (i++ > 0) sb.append("_");
				sb.append(k);
			}
			return sb.toString();
		}
		
		public List<Option> sorted_options_for(String p) {
			// List<Option> options = options_for(p);
			// Collections.sort(options);
			// return options;
			return sort(options_for(p));
		}
		
		private List<Option> sort(List<Option> options){
			Option[] a = new Option[] {};
			a = options.toArray(a);
			Arrays.sort(a);
			options.clear();
			for(Option option : a) {
				options.add(option);
			}
			return options;
		}
	}
	
	class Option implements Comparable<Option> {
		
		private final String k;
		private final String v;
		
		public Option(String k, String v) {
			this.k = k;
			this.v = v;
		}
		
		public String getK() {
			return k;
		}
		public String getV() {
			return v;
		}
		
		public String toString() {
			return "{'" + k + "':'" + v + "'}";
		}
		
		public int compareTo(Option o) {
			if (o == null) return 1;
			if (this == o) return 0;
			String o_k = o.getK();
			if (k == null) {
				if (o_k == null) return 0;
				return -1;
			}
			if (o_k == null) return 1;
			return k.compareTo(o_k);
		}
	}
	
	private void print_options(String p, List<Option> options) {
		System.out.println("'" + p + "':[");
		int i = 0;
		for (Option option : options) {
			if (i++ > 0) System.out.println(",");
			System.out.print("  " + option);
		}
		if (options.size() > 0) {
			System.out.println();
		}
		System.out.println("]");
	}
	
	private void test_builder() {
		test_builder_from_hierarchy();
		test_builder_from_matrix();
		test_builder_sort();
	}
	
	private void test_builder_from_hierarchy() {
		String[][] levels_1 = {
			{"1", "X1"},
			{"2", "X2"},
			{"3", "X3"}
		};
		String[][] levels_2 = {
			{"11", "X1Y1"},
			{"12", "X1Y2"},
			{"13", "X1Y3"},
			{"21", "X2Y1"},
			{"31", "X3Y1"},
			{"32", "X3Y2"}
		};
		String[][] levels_3 = {
			{"111", "X1Y1Z1"},
			{"121", "X1Y2Z1"},
			{"122", "X1Y2Z2"},
			{"131", "X1Y3Z1"},
			{"132", "X1Y3Z2"},
			{"133", "X1Y3Z3"},
			{"211", "X2Y1Z1"},
			{"212", "X2Y1Z2"},
			{"213", "X2Y1Z3"},
			{"311", "X3Y1Z1"},
			{"312", "X3Y1Z2"},
			{"321", "X3Y2Z1"},
			{"322", "X3Y2Z2"}
		};
		
		CascadingOptionsBuilder builder = new CascadingOptionsBuilder();
		
		for (String[] level_1 : levels_1) {
			builder.add(builder.p(), new Option(level_1[0], level_1[1]));
			for (String[] level_2 : levels_2) {
				if (level_2[0].indexOf(level_1[0]) == 0) { // simulate where clause
					builder.add(builder.p(level_1[0]), new Option(level_2[0], level_2[1]));
					for (String[] level_3 : levels_3) {
						if (level_3[0].indexOf(level_2[0]) == 0) { // simulate where clause
							builder.add(builder.p(level_1[0], level_2[0]), new Option(level_3[0], level_3[1]));
						}
					}
				}
			}
		}
		
		String p = builder.p();
		print_options(p, builder.options_for(p));
		
		p = builder.p("1");
		print_options(p, builder.options_for(p));
		
		p = builder.p("1", "12");
		print_options(p, builder.options_for(p));
	}
	
	private void test_builder_from_matrix() {
		String[][] rows = {
			{"1", "X1", "11", "X1Y1", "111", "X1Y1Z1"},
			{"1", "X1", "12", "X1Y2", "121", "X1Y2Z1"},
			{"1", "X1", "12", "X1Y2", "122", "X1Y2Z2"},
			{"1", "X1", "13", "X1Y3", "131", "X1Y3Z1"},
			{"1", "X1", "13", "X1Y3", "132", "X1Y3Z2"},
			{"1", "X1", "13", "X1Y3", "133", "X1Y3Z3"},
			{"2", "X2", "21", "X2Y1", "211", "X2Y1Z1"},
			{"2", "X2", "21", "X2Y1", "212", "X2Y1Z2"},
			{"2", "X2", "21", "X2Y1", "213", "X2Y1Z3"},
			{"3", "X3", "31", "X3Y1", "311", "X3Y1Z1"},
			{"3", "X3", "31", "X3Y1", "312", "X3Y1Z2"},
			{"3", "X3", "32", "X3Y2", "321", "X3Y2Z1"},
			{"3", "X3", "32", "X3Y2", "322", "X3Y2Z2"}
		};
		
		CascadingOptionsBuilder builder = new CascadingOptionsBuilder();
		
		for (String[] row : rows) {
			builder.put(builder.p(), new Option(row[0], row[1]));
			builder.put(builder.p(row[0]), new Option(row[2], row[3]));
			builder.put(builder.p(row[0], row[2]), new Option(row[4], row[5]));
		}
		
		String p = builder.p();
		print_options(p, builder.options_for(p));
		
		p = builder.p("3");
		print_options(p, builder.options_for(p));
		
		p = builder.p("3", "32");
		print_options(p, builder.options_for(p));
	}
	
	private void test_builder_sort() {
		CascadingOptionsBuilder builder = new CascadingOptionsBuilder();
		String p = builder.p("2", "21");
		builder.add(p, new Option("213", "X2Y1Z3"));
		builder.add(p, new Option("211", "X2Y1Z1"));
		builder.add(p, new Option("212", "X2Y1Z2"));
		print_options(p, builder.sorted_options_for(p));
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_builder();
		test_nothing();
	}
	
	public static void main(String[] args) {
		CascadingOptionsBuilderDemo worker = new CascadingOptionsBuilderDemo();
		worker.test();
	}
}

package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysSortTest {
	
	private void test_Arrays_sort() {
		test_sort_not_comparable();
		test_sort_comparable();
	}
	
	private void test_sort_not_comparable() {
		System.out.println("// test sort not comparable");
		List<Tomato> list = null;
		for (int capacity = 0; capacity <= 4; capacity++) {
			for (int cnt = 0; cnt <= capacity; cnt++) {
				list = new ArrayList<Tomato>(capacity);
				for (int ix = 0; ix < cnt; ix++) {
					list.add(new_tomato(ix));
				}
				try {
					list = sort_tomato(list);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.out.println(String.valueOf(capacity) + "::" + String.valueOf(cnt));
			}
		}
	}
	
	private void test_sort_comparable() {
		System.out.println("// test sort comparable");
		List<Tomato> list = null;
		for (int capacity = 0; capacity <= 4; capacity++) {
			for (int cnt = 0; cnt <= capacity; cnt++) {
				list = new ArrayList<Tomato>(capacity);
				for (int ix = 0; ix < cnt; ix++) {
					list.add(new_comparable_tomato(ix));
				}
				try {
					list = sort_tomato(list);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.out.println(String.valueOf(capacity) + "::" + String.valueOf(cnt));
			}
		}
	}
	
	private List<Tomato> sort_tomato(List<Tomato> list){
		Tomato[] oa = new Tomato[] {};
		oa = list.toArray(oa);
		Arrays.sort(oa);
		list.clear();
		Tomato t = null;
		for(int i = 0; i < oa.length; i++) {
			t = oa[i];
			list.add(t);
		}
		return list;
	}
	
	private Tomato new_tomato(int i) {
		String name = String.valueOf(i);
		Tomato t = new Tomato(name);
		return t;
	}
	
	private Tomato new_comparable_tomato(int i) {
		String name = String.valueOf(i);
		Tomato t = new ComparableTomato(name);
		return t;
	}
	
	private void test_Comparable_compareTo() {
		System.out.println("// test Comparable.compareTo()");
		ComparableTomato t1 = new ComparableTomato();
		System.out.println(t1.compareTo(null)); // ==> 1  // {null} v. null
		ComparableTomato t2 = new ComparableTomato();
		System.out.println(t1.compareTo(t2));   // ==> 0  // {null} v. {null}
		t1 = new ComparableTomato("one");
		System.out.println(t2.compareTo(t1));   // ==> -1 // {null} v. {"one"}
		System.out.println(t1.compareTo(null)); // ==> 1  // {"one"} v. null
		System.out.println(t1.compareTo(t2));   // ==> 1  // {"one"} v. {null}
		t2 = new ComparableTomato("two");
		System.out.println(t1.compareTo(t2));   // ==> -5 // {"one"} v. {"two"}
		System.out.println(t2.compareTo(t1));   // ==> 5  // {"two"} v. {"one"}
	}
	
	/*
	 * Arrays.sort() uses merge sort. So ...
	 */
	private void test_Arrays_sort_alt() {
		System.out.println("// test Arrays.sort() alt");
		List<Tomato> list = new ArrayList<Tomato>();
		ComparableTomato t = null;
		t = new ComparableTomato("one");
		list.add(t);
		t = new ComparableTomato("two");
		list.add(t);
		t = new ComparableTomato();
		list.add(t);
		//list.add(null); // if null come in between, it's not ok
		t = new ComparableTomato();
		list.add(t);
		t = new ComparableTomato("nil");
		list.add(t);
		t = new ComparableTomato("two");
		list.add(t);
		list.add(null); // if null come last, it's ok
		for (int i = 0; i < list.size(); i++) {
			System.out.println("["+String.valueOf(i)+"]="+list.get(i));
		}
		try {
			list = sort_tomato(list);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println("["+String.valueOf(i)+"]="+list.get(i));
		}
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test_all() {
		test_Arrays_sort();
		test_Comparable_compareTo();
		test_Arrays_sort_alt();
		test_nothing();
	}
	
	class Tomato {
		
		public Tomato() {
			// super();
		}
		public Tomato(String name) {
			this.name = name;
		}
		
		protected String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String toString() {
			if (name == null)
				return "{" + name + "}";
			else
				return "{\"" + name + "\"}";
		}
	}
	
	class ComparableTomato extends Tomato implements Comparable<ComparableTomato> {
		
		public ComparableTomato() {
			super();
		}
		public ComparableTomato(String name) {
			super(name);
		}
		
		public int compareTo(ComparableTomato t) {
			System.out.println(this+".compareTo "+t);
			if (t == null) return 1;
			if (this == t) return 0;
			String t_name = t.getName();
			if (name == null) {
				if (t_name == null) return 0;
				return -1;
			}
			if (t_name == null) return 1;
			return name.compareTo(t.getName());
			
			//if (t == null) {
			//	System.out.println("compareTo::"+this+"::"+t+"::"+"a");
			//	return 1;
			//} else if (this == t) {
			//	System.out.println("compareTo::"+this+"::"+t+"::"+"b");
			//	return 0;
			//} else {
			//	String t_name = t.getName();
			//	if (name == null) {
			//		if (t_name == null) {
			//			System.out.println("compareTo::"+this+"::"+t+"::"+"c");
			//			return 0;
			//		} else {
			//			System.out.println("compareTo::"+this+"::"+t+"::"+"d");
			//			return -1;
			//		}
			//	} else {
			//		if (t_name == null) {
			//			System.out.println("compareTo::"+this+"::"+t+"::"+"e");
			//			return 1;
			//		} else {
			//			System.out.println("compareTo::"+this+"::"+t+"::"+"f");
			//			return name.compareTo(t.getName());
			//		}
			//	}
			//}
		}
	}
	
	public static void main(String[] args) {
		ArraysSortTest worker = new ArraysSortTest();
		worker.test_all();
	}
}
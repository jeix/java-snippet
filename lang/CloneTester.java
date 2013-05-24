package lang;

import java.util.ArrayList;
import java.util.List;

public class CloneTester {
	
	private void test_Something() {
		Foo foo = new Foo("푸 1");
		Bar bar = new Bar("바 11");
		foo.addBar(bar);
		bar = new Bar("바 12");
		foo.addBar(bar);
		
		Foo foo2 = foo.clone();
		if (foo == foo2) {
			System.out.println("OOPS foo == foo2");
		}
		if (! foo.equals(foo2)) {
			System.out.println("OOPS foo is not equal to foo2");
		}
		foo2.setVal("푸 2");
		if (foo.equals(foo2)) {
			System.out.println("OOPS foo is equal to foo2");
		}
		Bar bar2 = foo2.getBarz().get(1);
		if (bar == bar2) {
			System.out.println("OOPS bar == bar2");
		}
		if (! bar.equals(bar2)) {
			System.out.println("OOPS bar is not equal to bar2");
		}
		bar2.setVal("바 22");
		if (bar.equals(bar2)) {
			System.out.println("OOPS bar is equal to bar2");
		}
		foo2.setVal("푸 1");
		if (foo.equals(foo2)) {
			System.out.println("OOPS foo is equal to foo2");
		}
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_Something();
		test_nothing();
	}
	
	public class Foo implements Cloneable {
		
		public Foo(String val) {
			this.val = val;
		}
		
		private String val = null;
		public String getVal() {
			return val;
		}
		public void setVal(String val) {
			this.val = val;
		}
		
		List<Bar> barz = new ArrayList<Bar>();
		public List<Bar> getBarz() {
			return barz;
		}
		public void setBarz(List<Bar> barz) {
			this.barz = barz;
		}
		public void addBar(Bar bar) {
			barz.add(bar);
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) return true;
			if (! (o instanceof Foo)) return false;
			Foo other = (Foo) o;
			if (val == null && val != other.getVal()) return false;
			if (val != null && ! val.equals(other.getVal())) return false;
			if (barz.size() != other.getBarz().size()) return false;
			for (int i = 0; i < barz.size(); i++) {
				Bar bar = barz.get(i);
				if (bar == null && bar != other.getBarz().get(i)) return false;
				if (bar != null && ! bar.equals(other.getBarz().get(i))) return false;
			}
			return true;
		}
		
		@Override
		public Foo clone() {
			try {
				Foo cloned = (Foo) super.clone();
				cloned.setBarz(new ArrayList<Bar>());
				for (Bar bar : barz) {
					Bar cloned_bar = bar.clone();
					cloned.addBar(cloned_bar);
				}
				return cloned;
			} catch(CloneNotSupportedException e) {
				throw new AssertionError();
			}
		}
	}
	
	public class Bar implements Cloneable {
		
		public Bar(String val) {
			this.val = val;
		}
		
		private String val = null;
		public String getVal() {
			return val;
		}
		public void setVal(String val) {
			this.val = val;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) return true;
			if (! (o instanceof Bar)) return false;
			Bar other = (Bar) o;
			if (val == null && val != other.getVal()) return false;
			if (val != null && ! val.equals(other.getVal())) return false;
			return true;
		}
		
		@Override
		public Bar clone() {
			try {
				return (Bar) super.clone();
			} catch(CloneNotSupportedException e) {
				throw new AssertionError();
			}
		}
	}
	
	public static void main(String[] args) {
		CloneTester worker = new CloneTester();
		worker.test();
	}
}

package lang.void_;

import java.util.ArrayList;
import java.util.List;

public class AvoidNullCheck {
	
	class ReturnNull {
		private String s;
		public String getString() {
			return s;
		}
		public void setString(String s) {
			this.s = s;
		}
		
		private List<String> items;
		public List<String> getList() {
			return items;
		}
		public void setList(List<String> items) {
			this.items = items;
		}
	}
	
	class ReturnEmpty {
		private String s;
		public String getString() {
			return (s != null ? s : "");
		}
		public void setString(String s) {
			this.s = s;
		}
		
		private List<String> items;
		public List<String> getList() {
			return (items != null ? items : new ArrayList<String>());
		}
		public void setList(List<String> items) {
			this.items = items;
		}
	}
	
	public String empty_if_null(String s) {
		return (s != null ? s : "");
	}
	
	public String trim_or_empty(String s) {
		return (s != null ? s.trim() : "");
	}
	
	public void test_null_check() {
		ReturnNull obj = new ReturnNull();
		if (obj.getString() != null) {
			System.out.println("NEVER come here");
			System.out.println("[" + obj.getString().trim() + "]");
		}
		System.out.println("[" + empty_if_null(obj.getString()).trim() + "]");
		System.out.println("[" + trim_or_empty(obj.getString()) + "]");
		if (obj.getList() != null) {
			System.out.println("NEVER come here");
			for (String s : obj.getList()) {
				System.out.println("[" + s.trim() + "]");
			}
		}
	}
	
	public void test_no_null_check() {
		ReturnEmpty obj = new ReturnEmpty();
		System.out.println("[" + obj.getString().trim() + "]");
		for (String s : obj.getList()) {
			System.out.println("NEVER come here");
			System.out.println("[" + s.trim() + "]");
		}
	}

	public void test_failed_no_null_check() {
		ReturnEmpty obj = new ReturnEmpty();
		List<String> items = new ArrayList<String>();
		items.add("not null");
		obj.setList(items);
		
		contaminate(obj);

		for (String str : obj.getList()) {
			try {
				System.out.println("[" + str.trim() + "]");
			} catch (NullPointerException nfe) {
				System.out.println("contaminated : " + nfe);
			}
		}
	}

	private void contaminate(ReturnEmpty obj) {
		List<String> items = obj.getList();
		if (items.isEmpty()) {
			items.add(null);
		} else {
			items.set(0, null);
		}
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_null_check();
		test_no_null_check();
		test_failed_no_null_check();
		test_nothing();
	}
	
	public static void main(String[] args) {
		AvoidNullCheck worker = new AvoidNullCheck();
		worker.test();
	}
}

package lang.annotation;

import java.lang.reflect.Field;

public class Ann {
	
	public void test_annotation() {
		
		Target target = new Target();
		target.setS("lunch");
		target.setT("supper");
		
		for (Field f : target.getClass().getDeclaredFields()) {
			boolean annotated = false;
			String prefix = null;
			String suffix = null;
			if (f.isAnnotationPresent(BisInDie.Simple.class)) {
				annotated = true;
				prefix = "twice ";
				suffix = " in a day";
			} else if (f.isAnnotationPresent(BisInDie.Parameter.class)) {
				annotated = true;
				BisInDie.Parameter bid_param = f.getAnnotation(BisInDie.Parameter.class);
				prefix = bid_param.prefix();
				suffix = bid_param.suffix();
			}
			if (annotated) {
				f.setAccessible(true);
				try {
					String txt = (String) f.get(target);
					f.set(target, prefix + txt + suffix);
				} catch (IllegalArgumentException iae) {
					iae.printStackTrace();
				} catch (IllegalAccessException iae2) {
					iae2.printStackTrace();
				}
			}
		}
		
		System.out.println(target.getS());
		System.out.println(target.getT());
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_annotation();
		test_nothing();
	}
	
	public static void main(String[] args) {
		Ann worker = new Ann();
		worker.test();
	}
}
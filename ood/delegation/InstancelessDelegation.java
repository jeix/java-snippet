package ood.delegation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InstancelessDelegation {
	
	static class Config {
		public static final String f = "foo";
		public static String m(String p, String q) {
			return p + q;
		}
	}
	
	static class Worker {
		private Class c;
		public Worker(Class c) {
			this.c = c;
		}
		
		public void work() {
			String x = getField("f");
			System.out.println(x);
			String y = "bar";
			String z = callMethod("m", x, y);
			System.out.println(z);
		}
		
		private String getField(String name) {
			return (String) ClassicDelegation.get(this.c, name);
		}
		
		private String callMethod(String name, String... params) {
			Object[] varargs = new Object[params.length];
			int i = 0;
			for (String param : params) {
				varargs[i++] = param;
			}
			return (String) ClassicDelegation.invoke(this.c, name, varargs);
		}
	}
	
	static class ClassicDelegation {
		public static Object invoke(Class c, String method_name, Object... params) {
			Class[] param_types = new Class[params.length];
			int i = 0;
			for (Object param : params) {
				param_types[i++] = param.getClass();
			}
			Method m = null;
			try {
				m = c.getMethod(method_name, param_types);
			} catch (NoSuchMethodException nsme) {
				return null;
			}
			try {
				return m.invoke(null, params);
			} catch (IllegalAccessException iae) {
				return null;
			} catch (InvocationTargetException ite) {
				return null;
			}
		}
		public static Object get(Class c, String field_name) {
			Field f = null;
			try {
				f = c.getField(field_name);
			} catch (NoSuchFieldException nsfe) {
				return null;
			}
			try {
				return f.get(null);
			} catch (IllegalAccessException iae) {
				return null;
			}
		}
	}
	
	public static void main(String[] args) {
		Worker xw = new Worker(Config.class);
		xw.work();
	}
}
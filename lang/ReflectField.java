package lang;

import java.lang.reflect.Field;

public class ReflectField {
	
	class Foo {
		
		private String s;
		public String getS() {
			return s;
		}
		public void setS(String s) {
			this.s = s;
		}
		
		private int i;
		public int getI() {
			return i;
		}
		public void setI(int i) {
			this.i = i;
		}
		
		private float f;
		public float getF() {
			return f;
		}
		public void setF(float f) {
			this.f = f;
		}
	}
	
	public void test_reflect() {
		
		Foo obj = new Foo();
		obj.setS("a");
		obj.setI(1);
		obj.setF(1.0f);
		
		for (Field f : obj.getClass().getDeclaredFields()) {
			Class<?> c = (Class<?>) f.getGenericType();
			System.out.println(f.getName() + " : " + c.getName());
			f.setAccessible(true);
			try {
				if (c.isPrimitive()) {
					if (boolean.class.equals(c)) {
						f.setBoolean(obj, ! f.getBoolean(obj));
					} else if (byte.class.equals(c)) {
						f.setByte(obj, (byte)(0 - f.getByte(obj)));
					} else if (char.class.equals(c)) {
						f.setChar(obj, (char)(0 - f.getChar(obj)));
					} else if (double.class.equals(c)) {
						f.setDouble(obj, 0 - f.getDouble(obj));
					} else if (float.class.equals(c)) {
						f.setFloat(obj, 0 - f.getFloat(obj));
					} else if (int.class.equals(c)) {
						f.setInt(obj, 0 - f.getInt(obj));
					} else if (long.class.equals(c)) {
						f.setLong(obj, 0 - f.getLong(obj));
					} else if (short.class.equals(c)) {
						f.setShort(obj, (short)(0 - f.getShort(obj)));
					}
				} else {
					if (String.class.equals(c)) {
						String str = (String) f.get(obj);
						if (str != null) {
							f.set(obj, str.toUpperCase());
						}
					}
				}
			} catch (IllegalAccessException iae) {
				iae.printStackTrace();
				return;
			}
		}
		
		System.out.println(obj.getS());
		System.out.println(obj.getI());
		System.out.println(obj.getF());
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_reflect();
		test_nothing();
	}
	
	public static void main(String[] args) {
		ReflectField worker = new ReflectField();
		worker.test();
	}
}
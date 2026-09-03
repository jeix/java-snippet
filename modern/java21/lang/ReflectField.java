package modern.java21.lang;

import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;

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
					switch (c.getName()) {
						case "boolean" -> f.setBoolean(obj, ! f.getBoolean(obj));
						case "byte" -> f.setByte(obj, (byte) (0 - f.getByte(obj)));
						case "char" -> f.setChar(obj, (char) (0 - f.getChar(obj)));
						case "double" -> f.setDouble(obj, 0 - f.getDouble(obj));
						case "float" -> f.setFloat(obj, 0 - f.getFloat(obj));
						case "int" -> f.setInt(obj, 0 - f.getInt(obj));
						case "long" -> f.setLong(obj, 0 - f.getLong(obj));
						case "short" -> f.setShort(obj, (short) (0 - f.getShort(obj)));
						default -> { }
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

	// Foo는 필드를 값으로 덮어써야 하는 데모라 record로 바꿀 수 없다 (record는 불변이다).
	// 대신 구조가 같은 record로 java.lang.reflect.RecordComponent(Java 16+)를 보여준다 -
	// record는 필드 대신 컴포넌트 단위로, 값을 바꾸지 않고 구조만 읽는 리플렉션을 지원한다.
	record FooRecord(String s, int i, float f) {}

	public void test_record_components() {
		for (RecordComponent rc : FooRecord.class.getRecordComponents()) {
			System.out.println(rc.getName() + " : " + rc.getType().getName());
		}
	}

	private void test_nothing() {
		System.out.println(":wq");
	}

	public void test() {
		test_reflect();
		test_record_components();
		test_nothing();
	}

	public static void main(String[] args) {
		ReflectField worker = new ReflectField();
		worker.test();
	}
}

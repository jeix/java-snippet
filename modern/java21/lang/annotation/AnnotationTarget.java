package modern.java21.lang.annotation;

// legacy/java8/lang/annotation/Target.java 를 대체해요.
// 이름 변경 이유: 같은 패키지에 java.lang.annotation.Target과 이름이 겹쳐 혼동을 일으킬
// 소지가 있었다. 필드를 리플렉션으로 덮어써야 하는 데모라 record로는 바꿀 수 없다
// (record는 불변이다).

public class AnnotationTarget {

	@BisInDie.Simple
	private String s;
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}

	@BisInDie.Parameter(prefix="twelve ", suffix=" in a day")
	private String t;
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
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

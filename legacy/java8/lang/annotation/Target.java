package lang.annotation;

public class Target {
	
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
package lang;

public class SystemSetPropertyDemo {
	
	private void test_System_setProperty() {
		String line_separator = System.getProperty("line.separator");
		System.setProperty("line.separator", "\n");
		System.out.print("foo");
		System.out.println(); // print "\r\n"
			// System.out은 시작하면서 초기화되어 있을 것이고
			// 그 과정에서 PrintStream이 사용하는 BufferedWriter의 생성자에서
			// 인스턴스 변수 lineSeparator가 이미 설정되어 있는 상태이므로
			// 		lineSeparator =	(String) java.security.AccessController.doPrivileged(
			// 			new sun.security.action.GetPropertyAction("line.separator"));
			// line.separator를 설정한 것이 영향을 끼치지 못한다.
		System.out.print("bar");
		System.out.print(System.getProperty("line.separator")); // print "\n"
		System.setProperty("line.separator", line_separator);
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_System_setProperty();
		test_nothing();
	}
	
	public static void main(String[] args) {
		SystemSetPropertyDemo demo = new SystemSetPropertyDemo();
		demo.test();
	}
}

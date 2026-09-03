package modern.java21.collection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

// 원형 유지: 키 순서를 보존하는 맵을 직접 구현하지 않고 표준 라이브러리(LinkedHashMap)를
// 쓰면 된다는 결론이다. OrderedKeyValPairsDemo.java, OrderedKeyValPairsDemo2.java와 함께
// "라이브러리를 알고 써라"(Effective Java 47)는 흐름을 보여주는 시리즈의 마지막 단계다.
// Java 21 판: collection/SequencedMapDemo.java

public class LinkedHashMapDemo {
	
	class Code {
		
		private final String k;
		private final String v;
		
		public Code(String k, String v) {
			this.k = k;
			this.v = v;
		}
		
		public String getK() {
			return k;
		}
		public String getV() {
			return v;
		}
		
		public String toString() {
			return "{'" + k + "':'" + v + "'}";
		}
	}
	
	private void test_LinkedHashMap() {
		Map<String,Code> map = new LinkedHashMap<String,Code>();
		
		// pass? keys from empty map
		new ArrayList<String>(map.keySet());
		// pass? values from empty map
		new ArrayList<Code>(map.values());
		
		map.put("1745", new Code("1745", "ADEV"));
		map.put("2371", new Code("2371", "GEJV"));
		map.put("3649", new Code("3649", "PKCF"));
		
		System.out.println("'2371' => " + map.get("2371"));
		
		System.out.println("iteration:");
		for (String key : map.keySet()) {
			System.out.println("\t'" + key + "' => " + map.get(key));
		}
		
		// keys as List instead of Set
		System.out.println("iteration:");
		for (String key : new ArrayList<String>(map.keySet())) {
			System.out.println("\t'" + key + "' => " + map.get(key));
		}
		
		System.out.println("iteration:");
		for (Code code : map.values()) {
			System.out.println("\t" + code);
		}
		
		// values as List instead of Collection
		System.out.println("iteration:");
		for (Code code : new ArrayList<Code>(map.values())) {
			System.out.println("\t" + code);
		}
		
		System.out.println(map);
		
		if (map.containsKey("3649")) {
			System.out.println("contains key '3649'");
		}
		if (! map.containsKey("668")) {
			System.out.println("not contains key '668'");
		}
		
		map.put("2371", new Code("2371", "HVKW"));
		System.out.println("now '2371' => " + map.get("2371"));
		
		System.out.println("remove " + map.remove("3649"));
		
		// get 0th
		for (Code code : map.values()) {
			System.out.println("0 => " + code);
			break;
		}
		
		System.out.println(map);
		
		map.clear();
		
		System.out.println("cleared, " + map.size() + " remains, " + map);
	}
	
	private void test_nothing() {
		System.out.println(":wq");
	}
	
	public void test() {
		test_LinkedHashMap();
		test_nothing();
	}
	
	public static void main(String[] args) {
		LinkedHashMapDemo worker = new LinkedHashMapDemo();
		worker.test();
	}
}
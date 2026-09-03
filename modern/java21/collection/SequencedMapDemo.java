package modern.java21.collection;

import java.util.LinkedHashMap;
import java.util.SequencedMap;

// OrderedKeyValPairsDemo/OrderedKeyValPairsDemo2/LinkedHashMapDemo가 비교하는 "키 순서를
// 보존하는 맵" 시리즈의 Java 21 판. LinkedHashMap은 이미 java.util.SequencedMap(JEP 431)을
// 구현하고 있어서, 첫/마지막 원소를 직접 넣고 꺼내거나 순서를 통째로 뒤집어 보는 것도
// 별도 유틸리티 없이 표준 라이브러리만으로 된다.
public class SequencedMapDemo {

	record Code(String k, String v) {}

	private void test_sequenced_map() {
		SequencedMap<String, Code> map = new LinkedHashMap<>();
		map.put("1745", new Code("1745", "ADEV"));
		map.put("2371", new Code("2371", "GEJV"));
		map.put("3649", new Code("3649", "PKCF"));

		System.out.println("firstEntry: " + map.firstEntry());
		System.out.println("lastEntry: " + map.lastEntry());

		map.putFirst("0000", new Code("0000", "먼저"));
		map.putLast("9999", new Code("9999", "나중"));
		System.out.println(map);

		System.out.println("역순: " + map.reversed());

		System.out.println("iteration:");
		for (String key : map.sequencedKeySet()) {
			System.out.println("\t'" + key + "' => " + map.get(key));
		}
	}

	private void test_nothing() {
		System.out.println(":wq");
	}

	public void test() {
		test_sequenced_map();
		test_nothing();
	}

	public static void main(String[] args) {
		SequencedMapDemo worker = new SequencedMapDemo();
		worker.test();
	}
}

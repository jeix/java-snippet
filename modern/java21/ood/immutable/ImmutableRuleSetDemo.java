package modern.java21.ood.immutable;

import java.util.List;

// WrapperOverCollection_1~4_Freeze(9개)가 시연하는 "내부 컬렉션을 어떻게 보호할까"라는
// 문제를 record + List.copyOf()가 어떻게 통째로 없애버리는지 보여준다. record는 원천적으로
// 불변이라 _1의 "보호 없음", _2의 "파생 뷰만 노출", _3의 "매번 방어적으로 clone",
// _4의 "만든 뒤 freeze" 중 어느 것도 따로 구현할 필요가 없다 - 생성자에서 List.copyOf()로
// 한 번만 방어하면 그걸로 끝이다. ChangeOverview에 setter가 없는 것도 _3_Clone/_4_Freeze의
// "TODO how to prohibit modify(re-set)"에 대한 답이다 - record는 애초에 setter가 없다.
public class ImmutableRuleSetDemo {

	record BizRuleType(String code, String name, boolean selected) {
		public BizRuleType(String code, String name) {
			this(code, name, false);
		}
		// 값을 바꾸는 대신 바뀐 값을 가진 새 인스턴스를 돌려준다("wither" 패턴).
		public BizRuleType asSelected() {
			return new BizRuleType(code, name, true);
		}
	}

	record BizRuleGroup(String code, String name, List<BizRuleType> types) {
		public BizRuleGroup {
			types = List.copyOf(types); // 생성 시점에 한 번만 방어하면 끝
		}
	}

	record BizRuleSet(List<BizRuleType> majors, List<BizRuleType> recents, List<BizRuleGroup> others) {
		public BizRuleSet {
			majors = List.copyOf(majors);
			recents = List.copyOf(recents);
			others = List.copyOf(others);
		}
	}

	record ChangeOverview(BizRuleSet ruleset) {}

	static class BizRuleSetBuilder {
		public static BizRuleSet build() {
			List<BizRuleType> majors = List.of(
					new BizRuleType("D001", "주요유형_1"),
					new BizRuleType("D002", "주요유형_2"),
					new BizRuleType("D003", "주요유형_3"),
					new BizRuleType("D004", "주요유형_4"));
			List<BizRuleType> recents = List.of(
					new BizRuleType("D901", "최신유형_1"),
					new BizRuleType("D902", "최신유형_2"),
					new BizRuleType("D903", "최신유형_3"),
					new BizRuleType("D904", "최신유형_4"));
			BizRuleGroup other_1 = new BizRuleGroup("01", "그룹_1", List.of(
					new BizRuleType("D101", "그룹_1_유형_1"),
					new BizRuleType("D102", "그룹_1_유형_2"),
					new BizRuleType("D103", "그룹_1_유형_3").asSelected(),
					new BizRuleType("D104", "그룹_1_유형_4")));
			BizRuleGroup other_2 = new BizRuleGroup("02", "그룹_2", List.of(
					new BizRuleType("D201", "그룹_2_유형_1"),
					new BizRuleType("D202", "그룹_2_유형_2"),
					new BizRuleType("D203", "그룹_2_유형_3"),
					new BizRuleType("D204", "그룹_2_유형_4")));
			BizRuleGroup other_3 = new BizRuleGroup("03", "그룹_3", List.of(
					new BizRuleType("D301", "그룹_3_유형_1"),
					new BizRuleType("D302", "그룹_3_유형_2"),
					new BizRuleType("D303", "그룹_3_유형_3"),
					new BizRuleType("D304", "그룹_3_유형_4")));
			return new BizRuleSet(majors, recents, List.of(other_1, other_2, other_3));
		}
	}

	public static void main(String[] args) {
		ChangeOverview overview = new ChangeOverview(BizRuleSetBuilder.build());

		// 누구도 이 리스트를 바꿀 수 없다 - List.copyOf()가 만든 불변 리스트라 예외가 난다.
		try {
			overview.ruleset().majors().add(new BizRuleType("D999", "안됨"));
		} catch (UnsupportedOperationException e) {
			System.out.println("바깥에서 majors를 바꿀 수 없다: " + e.getClass().getSimpleName());
		}

		System.out.println(overview.ruleset());
	}
}

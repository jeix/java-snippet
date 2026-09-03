package modern.java21.ood.immutable;

import java.util.List;

public class WrapperOverCollection_1_Nothing {

	private static List<String> ofp = List.of();

	public record BizRuleType(String code, String name, boolean selected) {
		public BizRuleType(String code, String name) {
			this(code, name, false);
		}
		public BizRuleType withSelected(boolean selected) {
			return new BizRuleType(code, name, selected);
		}
	}

	public record BizRuleGroup(String code, String name, List<BizRuleType> types) {
		public BizRuleGroup(String code, String name) {
			this(code, name, List.of());
		}
	}

	public record BizRuleSet(
		List<BizRuleType> majors,
		List<BizRuleType> recents,
		List<BizRuleGroup> others
	) {
		public BizRuleSet {
			majors = List.copyOf(majors);
			recents = List.copyOf(recents);
			others = List.copyOf(others);
		}

		public BizRuleSet() {
			this(List.of(), List.of(), List.of());
		}
	}

	public record ChangeOverview(BizRuleSet ruleset) {}

	public static BizRuleSet build() {
		var majors = List.of(
			new BizRuleType("D001", "주요유형_1"),
			new BizRuleType("D002", "주요유형_2"),
			new BizRuleType("D003", "주요유형_3"),
			new BizRuleType("D004", "주요유형_4")
		);

		var recents = List.of(
			new BizRuleType("D901", "최신유형_1"),
			new BizRuleType("D902", "최신유형_2"),
			new BizRuleType("D903", "최신유형_3"),
			new BizRuleType("D904", "최신유형_4")
		);

		var others = List.of(
			new BizRuleGroup("01", "그룹_1", List.of(
				new BizRuleType("D101", "그룹_1_유형_1"),
				new BizRuleType("D102", "그룹_1_유형_2"),
				new BizRuleType("D103", "그룹_1_유형_3"),
				new BizRuleType("D104", "그룹_1_유형_4")
			)),
			new BizRuleGroup("02", "그룹_2", List.of(
				new BizRuleType("D201", "그룹_2_유형_1"),
				new BizRuleType("D202", "그룹_2_유형_2"),
				new BizRuleType("D203", "그룹_2_유형_3"),
				new BizRuleType("D204", "그룹_2_유형_4")
			)),
			new BizRuleGroup("03", "그룹_3", List.of(
				new BizRuleType("D301", "그룹_3_유형_1"),
				new BizRuleType("D302", "그룹_3_유형_2"),
				new BizRuleType("D303", "그룹_3_유형_3"),
				new BizRuleType("D304", "그룹_3_유형_4")
			))
		);

		return new BizRuleSet(majors, recents, others);
	}

	private static List<String> deepDive(BizRuleSet ruleset) {
		var result = new java.util.ArrayList<String>();
		result.add(ruleset.toString());
		ruleset.majors().forEach(m -> result.add(m.toString()));
		ruleset.recents().forEach(r -> result.add(r.toString()));
		for (var other : ruleset.others()) {
			result.add(other.toString());
			for (var type : other.types()) {
				result.add(type.toString());
			}
		}
		return List.copyOf(result);
	}

	public static void main(String[] args) {
		var overview = new ChangeOverview(build());
		var ruleset = overview.ruleset();
		var fp = deepDive(ruleset);

		if (ofp.isEmpty()) {
			ofp = fp;
		} else if (!ofp.equals(fp)) {
			System.out.println("diff");
		}
		System.out.println(":wq");
	}
}
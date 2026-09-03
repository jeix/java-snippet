package modern.java21.ood.immutable;

import java.util.List;

public class WrapperOverCollection_3_Clone {

	public record BizRuleType(String code, String name, boolean selected) {
		public BizRuleType {
			if (code == null || name == null) throw new NullPointerException();
		}

		public BizRuleType(String code, String name) {
			this(code, name, false);
		}

		public BizRuleType withSelected() {
			return new BizRuleType(code, name, true);
		}

		public BizRuleType copy() {
			return new BizRuleType(code, name, selected);
		}
	}

	public record BizRuleGroup(String code, String name, List<BizRuleType> types) {
		public BizRuleGroup {
			types = List.copyOf(types);
		}

		public BizRuleGroup(String code, String name) {
			this(code, name, List.of());
		}

		public BizRuleGroup copy() {
			return new BizRuleGroup(code, name, List.copyOf(types));
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

		public BizRuleSet copy() {
			return new BizRuleSet(
				List.copyOf(majors),
				List.copyOf(recents),
				List.copyOf(others)
			);
		}
	}

	public static class ChangeOverview {
		private BizRuleSet ruleset;

		public ChangeOverview() {
			this.ruleset = new BizRuleSet();
		}

		public void setRuleset(BizRuleSet ruleset) {
			this.ruleset = ruleset != null ? ruleset.copy() : new BizRuleSet();
		}

		public BizRuleSet getRuleset() {
			return ruleset.copy();
		}
	}

	public static BizRuleSet build() {
		var t1 = new BizRuleType("D101", "그룹_1_유형_1");
		var t2 = new BizRuleType("D102", "그룹_1_유형_2");
		var t3 = new BizRuleType("D103", "그룹_1_유형_3").withSelected();
		var t4 = new BizRuleType("D104", "그룹_1_유형_4");
		var types1 = List.of(t1, t2, t3, t4);

		var types2 = List.of(
			new BizRuleType("D201", "그룹_2_유형_1"),
			new BizRuleType("D202", "그룹_2_유형_2"),
			new BizRuleType("D203", "그룹_2_유형_3"),
			new BizRuleType("D204", "그룹_2_유형_4")
		);

		var types3 = List.of(
			new BizRuleType("D301", "그룹_3_유형_1"),
			new BizRuleType("D302", "그룹_3_유형_2"),
			new BizRuleType("D303", "그룹_3_유형_3"),
			new BizRuleType("D304", "그룹_3_유형_4")
		);

		return new BizRuleSet(
			List.of(
				new BizRuleType("D001", "주요유형_1"),
				new BizRuleType("D002", "주요유형_2"),
				new BizRuleType("D003", "주요유형_3"),
				new BizRuleType("D004", "주요유형_4")
			),
			List.of(
				new BizRuleType("D901", "최신유형_1"),
				new BizRuleType("D902", "최신유형_2"),
				new BizRuleType("D903", "최신유형_3"),
				new BizRuleType("D904", "최신유형_4")
			),
			List.of(
				new BizRuleGroup("01", "그룹_1", types1),
				new BizRuleGroup("02", "그룹_2", types2),
				new BizRuleGroup("03", "그룹_3", types3)
			)
		);
	}

	private static java.util.List<String> ofp = new java.util.ArrayList<>();

	private static List<String> deepDive(BizRuleSet ruleset) {
		var fp = new java.util.ArrayList<String>();
		fp.add(ruleset.toString());
		ruleset.majors().forEach(m -> fp.add(m.toString()));
		ruleset.recents().forEach(r -> fp.add(r.toString()));
		for (var other : ruleset.others()) {
			fp.add(other.toString());
			for (var type : other.types()) {
				fp.add(type.toString());
			}
		}
		return List.copyOf(fp);
	}

	private static void runDeepDive(BizRuleSet ruleset) {
		var fp = deepDive(ruleset);

		if (ofp.isEmpty()) {
			ofp = new java.util.ArrayList<>(fp);
			return;
		}

		for (int i = 0; i < ofp.size() && i < fp.size(); i++) {
			if (!ofp.get(i).equals(fp.get(i))) {
				System.out.println("diff");
				break;
			}
		}
		System.out.println(":wq");
	}

	public static void main(String[] args) {
		var overview = new ChangeOverview();
		overview.setRuleset(build());
		var ruleset = overview.getRuleset();
		runDeepDive(ruleset);
	}
}
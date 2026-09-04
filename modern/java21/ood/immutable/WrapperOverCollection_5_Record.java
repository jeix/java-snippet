package modern.java21.ood.immutable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WrapperOverCollection_5_Record {

	record BizRuleType(String code, String name, boolean selected) {}

	record BizRuleGroup(String code, String name, List<BizRuleType> types) {

		BizRuleGroup {
			types = List.copyOf(types);
		}
	}

	record BizRuleSet(
			List<BizRuleType> majors,
			List<BizRuleType> recents,
			Map<String, BizRuleGroup> others) {

		BizRuleSet {
			majors = List.copyOf(majors);
			recents = List.copyOf(recents);
			others = Map.copyOf(others);
		}
	}

	record ChangeOverview(Map<String, BizRuleSet> rulesets) {

		ChangeOverview {
			rulesets = Map.copyOf(rulesets);
		}
	}

	private void test_immutable_snapshot() {
		var majors = new ArrayList<>(List.of(
				new BizRuleType("D001", "주요유형_1", false),
				new BizRuleType("D002", "주요유형_2", false)));
		var group_types = new ArrayList<>(List.of(
				new BizRuleType("D101", "그룹_1_유형_1", false),
				new BizRuleType("D102", "그룹_1_유형_2", true)));
		var others = new LinkedHashMap<String, BizRuleGroup>();
		others.put("01", new BizRuleGroup("01", "그룹_1", group_types));

		var ruleset = new BizRuleSet(majors, List.of(), others);
		var mutable_rulesets = new LinkedHashMap<String, BizRuleSet>();
		mutable_rulesets.put("default", ruleset);
		var overview = new ChangeOverview(mutable_rulesets);

		majors.add(new BizRuleType("D003", "주요유형_3", false));
		group_types.clear();
		others.clear();
		mutable_rulesets.clear();

		var snapshot = overview.rulesets().get("default");
		System.out.println("majors: " + snapshot.majors().size());
		System.out.println("group types: " + snapshot.others().get("01").types().size());
		System.out.println("rulesets: " + overview.rulesets().size());
		System.out.println("majors immutable: " + rejects_change(
				() -> snapshot.majors().add(new BizRuleType("D004", "주요유형_4", false))));
		System.out.println("others immutable: " + rejects_change(
				() -> snapshot.others().clear()));
		System.out.println("rulesets immutable: " + rejects_change(
				() -> overview.rulesets().clear()));
	}

	private boolean rejects_change(Runnable change) {
		try {
			change.run();
			return false;
		} catch (UnsupportedOperationException expected) {
			return true;
		}
	}

	public void test() {
		test_immutable_snapshot();
		System.out.println(":wq");
	}

	public static void main(String[] args) {
		new WrapperOverCollection_5_Record().test();
	}
}

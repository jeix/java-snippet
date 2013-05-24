package ood.immutable;

import java.util.ArrayList;
import java.util.List;

public class WrapperOverCollection_4 {
	
	static class ChangeOverview {
		private BizRuleSet ruleset;
		public void setRuleset(BizRuleSet ruleset) {
			this.ruleset = ruleset;
		}
		public BizRuleSet getRuleset() {
			return ruleset;
		}
	}
	static class BizRuleSet {
		private List<BizRuleType> majors = new ArrayList<BizRuleType>();
		public void setMajors(List<BizRuleType> majors) {
			if (! this.frozen) {
				this.majors = majors;
			}
		}
		public List<BizRuleType> getMajors() {
			return this.majors;
		}
		private List<BizRuleType> recents = new ArrayList<BizRuleType>();
		public void setRecents(List<BizRuleType> recents) {
			if (! this.frozen) {
				this.recents = recents;
			}
		}
		public List<BizRuleType> getRecents() {
			return this.recents;
		}
		private List<BizRuleGroup> others = new ArrayList<BizRuleGroup>();
		public void setOthers(List<BizRuleGroup> others) {
			if (! this.frozen) {
				this.others = others;
			}
		}
		public List<BizRuleGroup> getOthers() {
			return this.others;
		}
		private boolean frozen;
		public BizRuleSet freeze() {
//			freezeMajors();
//			freezeRecents();
//			freezeOthers();
//			this.frozen = true;
			Freezer.freeze(this);
			return this;
		}
		public boolean frozen() {
			return this.frozen;
		}
//		private void freezeMajors() {
//			for (BizRuleType major : this.majors) {
//				major.freeze();
//			}
//		}
//		private void freezeRecents() {
//			for (BizRuleType recent : this.recents) {
//				recent.freeze();
//			}
//		}
//		private void freezeOthers() {
//			for (BizRuleGroup other : this.others) {
//				other.freeze();
//			}
//		}
		static class Freezer {
			public static void freeze(BizRuleSet set) {
				freezeTypes(set.majors);
				freezeTypes(set.recents);
				freezeGroups(set.others);
				set.frozen = true;
			}
			private static void freezeTypes(List<BizRuleType> types) {
				for (BizRuleType type : types) {
					type.freeze();
				}
			}
			private static void freezeGroups(List<BizRuleGroup> groups) {
				for (BizRuleGroup group : groups) {
					group.freeze();
				}
			}
		}
	}
	static class BizRuleGroup {
		private String code;
		private String name;
		public BizRuleGroup(String code, String name) {
			this.code = code;
			this.name = name;
		}
		public String getCode() {
			return this.code;
		}
		public String getName() {
			return this.name;
		}
		private List<BizRuleType> types = new ArrayList<BizRuleType>();
		public void setTypes(List<BizRuleType> types) {
			if (! this.frozen) {
				this.types = types;
			}
		}
		public List<BizRuleType> getTypes() {
			return this.types;
		}
		private boolean frozen;
		public BizRuleGroup freeze() {
			for (BizRuleType type : this.types) {
				type.freeze();
			}
			this.frozen = true;
			return this;
		}
		public boolean frozen() {
			return this.frozen;
		}
	}
	static class BizRuleType {
		private String code;
		private String name;
		public BizRuleType(String code, String name) {
			this.code = code;
			this.name = name;
		}
		public String getCode() {
			return this.code;
		}
		public String getName() {
			return this.name;
		}
		private boolean selected;
		public void markAsSelected() {
			if (! this.frozen) {
				this.selected = true;
			}
		}
		public boolean getSelected() {
			return this.selected;
		}
		private boolean frozen;
		public BizRuleType freeze() {
			this.frozen = true;
			return this;
		}
		public boolean frozen() {
			return this.frozen;
		}
	}
	
	static class BizRuleSetBuilder {
		public static BizRuleSet build() {
			BizRuleSet set = new BizRuleSet();
			List<BizRuleType> majors = set.getMajors();
			majors.add(new BizRuleType("D001", "주요유형_1"));
			majors.add(new BizRuleType("D002", "주요유형_2"));
			majors.add(new BizRuleType("D003", "주요유형_3"));
			majors.add(new BizRuleType("D004", "주요유형_4"));
			List<BizRuleType> recents = set.getRecents();
			recents.add(new BizRuleType("D901", "최신유형_1"));
			recents.add(new BizRuleType("D902", "최신유형_2"));
			recents.add(new BizRuleType("D903", "최신유형_3"));
			recents.add(new BizRuleType("D904", "최신유형_4"));
			List<BizRuleGroup> others = set.getOthers();
			BizRuleGroup other_1 = new BizRuleGroup("01", "그룹_1");
			List<BizRuleType> types_1 = other_1.getTypes();
			types_1.add(new BizRuleType("D101", "그룹_1_유형_1"));
			types_1.add(new BizRuleType("D102", "그룹_1_유형_2"));
			types_1.add(new BizRuleType("D103", "그룹_1_유형_3"));
			types_1.add(new BizRuleType("D104", "그룹_1_유형_4"));
			types_1.get(2).markAsSelected();
			others.add(other_1);
			BizRuleGroup other_2 = new BizRuleGroup("02", "그룹_2");
			List<BizRuleType> types_2 = other_2.getTypes();
			types_2.add(new BizRuleType("D201", "그룹_2_유형_1"));
			types_2.add(new BizRuleType("D202", "그룹_2_유형_2"));
			types_2.add(new BizRuleType("D203", "그룹_2_유형_3"));
			types_2.add(new BizRuleType("D204", "그룹_2_유형_4"));
			others.add(other_2);
			BizRuleGroup other_3 = new BizRuleGroup("03", "그룹_3");
			List<BizRuleType> types_3 = other_3.getTypes();
			types_3.add(new BizRuleType("D301", "그룹_3_유형_1"));
			types_3.add(new BizRuleType("D302", "그룹_3_유형_2"));
			types_3.add(new BizRuleType("D303", "그룹_3_유형_3"));
			types_3.add(new BizRuleType("D304", "그룹_3_유형_4"));
			others.add(other_3);
			return set;
		}
	}
	
	public static void main(String[] args) {
		// "freeze" version
		ChangeOverview overview = new ChangeOverview();
		overview.setRuleset(BizRuleSetBuilder.build().freeze());
		BizRuleSet ruleset = overview.getRuleset();
		System.out.println(ruleset);
	}
}
package ood.immutable;

import java.util.ArrayList;
import java.util.List;

public class WrapperOverCollection_4_Freeze {
	
	static class ChangeOverview {
		private BizRuleSet ruleset;
		public void setRuleset(BizRuleSet ruleset) {
			// TODO how to prohibit modify(re-set)
			this.ruleset = ruleset.freeze();
		}
		public BizRuleSet getRuleset() {
			return ruleset;
		}
	}
	static class BizRuleSet {
		private List<BizRuleType> majors = new ArrayList<BizRuleType>();
		public void addMajor(BizRuleType type) {
			if (! this.frozen) {
				this.majors.add(type.freeze());
			}
		}
		public List<BizRuleType> getMajors() {
			return this.majors;
		}
		private List<BizRuleType> recents = new ArrayList<BizRuleType>();
		public void addRecent(BizRuleType type) {
			if (! this.frozen) {
				this.recents.add(type.freeze());
			}
		}
		public List<BizRuleType> getRecents() {
			return this.recents;
		}
		private List<BizRuleGroup> others = new ArrayList<BizRuleGroup>();
		public void addOther(BizRuleGroup group) {
			if (! this.frozen) {
				this.others.add(group.freeze());
			}
		}
		public List<BizRuleGroup> getOthers() {
			return this.others;
		}
		private boolean frozen;
		public BizRuleSet freeze() {
			// freezeMajors();
			// freezeRecents();
			// freezeOthers();
			// this.frozen = true;
			Freezer.freeze(this);
			return this;
		}
		public boolean frozen() {
			return this.frozen;
		}
		// private void freezeMajors() {
		// 	for (BizRuleType major : this.majors) {
		// 		major.freeze();
		// 	}
		// }
		// private void freezeRecents() {
		// 	for (BizRuleType recent : this.recents) {
		// 		recent.freeze();
		// 	}
		// }
		// private void freezeOthers() {
		// 	for (BizRuleGroup other : this.others) {
		// 		other.freeze();
		// 	}
		// }
		static class Freezer {
			public static void freeze(BizRuleSet set) {
				//freezeTypes(set.majors);
				//freezeTypes(set.recents);
				//freezeGroups(set.others);
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
			this.code = new String(code);
			this.name = new String(name);
		}
		public String getCode() {
			return new String(this.code);
		}
		public String getName() {
			return new String(this.name);
		}
		private List<BizRuleType> types = new ArrayList<BizRuleType>();
		public void addType(BizRuleType type) {
			if (! this.frozen) {
				this.types.add(type.freeze());
			}
		}
		public List<BizRuleType> getTypes() {
			return this.types;
		}
		private boolean frozen;
		public BizRuleGroup freeze() {
			//for (BizRuleType type : this.types) {
			//	type.freeze();
			//}
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
			this.code = new String(code);
			this.name = new String(name);
		}
		public String getCode() {
			return new String(this.code);
		}
		public String getName() {
			return new String(this.name);
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
			set.addMajor(new BizRuleType("D001", "주요유형_1"));
			set.addMajor(new BizRuleType("D002", "주요유형_2"));
			set.addMajor(new BizRuleType("D003", "주요유형_3"));
			set.addMajor(new BizRuleType("D004", "주요유형_4"));
			set.addRecent(new BizRuleType("D901", "최신유형_1"));
			set.addRecent(new BizRuleType("D902", "최신유형_2"));
			set.addRecent(new BizRuleType("D903", "최신유형_3"));
			set.addRecent(new BizRuleType("D904", "최신유형_4"));
			BizRuleGroup other_1 = new BizRuleGroup("01", "그룹_1");
			other_1.addType(new BizRuleType("D101", "그룹_1_유형_1"));
			other_1.addType(new BizRuleType("D102", "그룹_1_유형_2"));
			other_1.addType(new BizRuleType("D103", "그룹_1_유형_3"));
			other_1.addType(new BizRuleType("D104", "그룹_1_유형_4"));
			other_1.getTypes().get(2).markAsSelected(); // TODO not works cuz already frozen
			set.addOther(other_1);
			BizRuleGroup other_2 = new BizRuleGroup("02", "그룹_2");
			other_2.addType(new BizRuleType("D201", "그룹_2_유형_1"));
			other_2.addType(new BizRuleType("D202", "그룹_2_유형_2"));
			other_2.addType(new BizRuleType("D203", "그룹_2_유형_3"));
			other_2.addType(new BizRuleType("D204", "그룹_2_유형_4"));
			set.addOther(other_2);
			BizRuleGroup other_3 = new BizRuleGroup("03", "그룹_3");
			other_3.addType(new BizRuleType("D301", "그룹_3_유형_1"));
			other_3.addType(new BizRuleType("D302", "그룹_3_유형_2"));
			other_3.addType(new BizRuleType("D303", "그룹_3_유형_3"));
			other_3.addType(new BizRuleType("D304", "그룹_3_유형_4"));
			set.addOther(other_3);
			return set;
		}
	}
	
	public static void main(String[] args) {
		// "freeze" version
		ChangeOverview overview = new ChangeOverview();
		overview.setRuleset(BizRuleSetBuilder.build());
		BizRuleSet ruleset = overview.getRuleset();
		System.out.println(ruleset);
	}
}
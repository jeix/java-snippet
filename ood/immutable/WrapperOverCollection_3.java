package ood.immutable;

import java.util.ArrayList;
import java.util.List;

public class WrapperOverCollection_3 {
	
	static class ChangeOverview {
		private BizRuleSet ruleset;
		public void setRuleset(BizRuleSet ruleset) {
			this.ruleset = ruleset.clone();
		}
		public BizRuleSet getRuleset() {
			return ruleset.clone();
		}
	}
	static class BizRuleSet {
		private List<BizRuleType> majors = new ArrayList<BizRuleType>();
		private void addMajor(BizRuleType type) {
			this.majors.add(type.clone());
		}
		public void setMajors(List<BizRuleType> majors) {
			this.majors = new ArrayList<BizRuleType>();
			if (majors != null) {
				for (BizRuleType major : majors) {
					addMajor(major);
				}
			}
		}
		public List<BizRuleType> getMajors() {
			List<BizRuleType> majors = new ArrayList<BizRuleType>();
			for (BizRuleType major : this.majors) {
				majors.add(major.clone());
			}
			return majors;
		}
		private List<BizRuleType> recents = new ArrayList<BizRuleType>();
		private void addRecent(BizRuleType type) {
			this.recents.add(type.clone());
		}
		public void setRecents(List<BizRuleType> recents) {
			this.recents = new ArrayList<BizRuleType>();
			if (recents != null) {
				for (BizRuleType recent : recents) {
					addRecent(recent);
				}
			}
		}
		public List<BizRuleType> getRecents() {
			List<BizRuleType> recents = new ArrayList<BizRuleType>();
			for (BizRuleType recent : this.recents) {
				recents.add(recent.clone());
			}
			return recents;
		}
		private List<BizRuleGroup> others = new ArrayList<BizRuleGroup>();
		private void addOther(BizRuleGroup group) {
			this.others.add(group.clone());
		}
		public void setOthers(List<BizRuleGroup> others) {
			this.others = new ArrayList<BizRuleGroup>();
			if (others != null) {
				for (BizRuleGroup other : others) {
					addOther(other);
				}
			}
		}
		public List<BizRuleGroup> getOthers() {
			List<BizRuleGroup> others = new ArrayList<BizRuleGroup>();
			for (BizRuleGroup other : this.others) {
				others.add(other.clone());
			}
			return others;
		}
		public BizRuleSet clone() {
			BizRuleSet cloned = new BizRuleSet();
			for (BizRuleType major : this.majors) {
				cloned.addMajor(major.clone());
			}
			for (BizRuleType recent : this.recents) {
				cloned.addRecent(recent.clone());
			}
			for (BizRuleGroup other : this.others) {
				cloned.addOther(other.clone());
			}
			return cloned;
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
		private void addType(BizRuleType type) {
			this.types.add(type.clone());
		}
		public void setTypes(List<BizRuleType> types) {
			this.types = new ArrayList<BizRuleType>();
			if (types != null) {
				for (BizRuleType type : types) {
					addType(type);
				}
			}
		}
		public List<BizRuleType> getTypes() {
			List<BizRuleType> types = new ArrayList<BizRuleType>();
			for (BizRuleType type : this.types) {
				types.add(type.clone());
			}
			return types;
		}
		public BizRuleGroup clone() {
			BizRuleGroup cloned = new BizRuleGroup(this.code, this.name);
			for (BizRuleType type : this.types) {
				cloned.addType(type.clone());
			}
			return cloned;
		}
	}
	static class BizRuleType {
		private String code;
		private String name;
		public BizRuleType(String code, String name) {
			this.code = code;
			this.name = name;
		}
		private boolean selected;
		public void markAsSelected() {
			this.selected = true;
		}
		public boolean getSelected() {
			return this.selected;
		}
		public BizRuleType clone() {
			BizRuleType cloned = new BizRuleType(this.code, this.name);
			if (this.selected) {
				cloned.markAsSelected();
			}
			return cloned;
		}
	}
	
	static class BizRuleSetBuilder {
		public static BizRuleSet build() {
			BizRuleSet set = new BizRuleSet();
			List<BizRuleType> majors = new ArrayList<BizRuleType>();
			majors.add(new BizRuleType("D001", "주요유형_1"));
			majors.add(new BizRuleType("D002", "주요유형_2"));
			majors.add(new BizRuleType("D003", "주요유형_3"));
			majors.add(new BizRuleType("D004", "주요유형_4"));
			set.setMajors(majors);
			List<BizRuleType> recents = new ArrayList<BizRuleType>();
			recents.add(new BizRuleType("D901", "최신유형_1"));
			recents.add(new BizRuleType("D902", "최신유형_2"));
			recents.add(new BizRuleType("D903", "최신유형_3"));
			recents.add(new BizRuleType("D904", "최신유형_4"));
			set.setRecents(recents);
			List<BizRuleGroup> others = new ArrayList<BizRuleGroup>();
			BizRuleGroup other_1 = new BizRuleGroup("01", "그룹_1");
			List<BizRuleType> types_1 = new ArrayList<BizRuleType>();
			types_1.add(new BizRuleType("D101", "그룹_1_유형_1"));
			types_1.add(new BizRuleType("D102", "그룹_1_유형_2"));
			types_1.add(new BizRuleType("D103", "그룹_1_유형_3"));
			types_1.add(new BizRuleType("D104", "그룹_1_유형_4"));
			types_1.get(2).markAsSelected();
			other_1.setTypes(types_1);
			others.add(other_1);
			BizRuleGroup other_2 = new BizRuleGroup("02", "그룹_2");
			List<BizRuleType> types_2 = new ArrayList<BizRuleType>();
			types_2.add(new BizRuleType("D201", "그룹_2_유형_1"));
			types_2.add(new BizRuleType("D202", "그룹_2_유형_2"));
			types_2.add(new BizRuleType("D203", "그룹_2_유형_3"));
			types_2.add(new BizRuleType("D204", "그룹_2_유형_4"));
			other_2.setTypes(types_2);
			others.add(other_2);
			BizRuleGroup other_3 = new BizRuleGroup("03", "그룹_3");
			List<BizRuleType> types_3 = new ArrayList<BizRuleType>();
			types_3.add(new BizRuleType("D301", "그룹_3_유형_1"));
			types_3.add(new BizRuleType("D302", "그룹_3_유형_2"));
			types_3.add(new BizRuleType("D303", "그룹_3_유형_3"));
			types_3.add(new BizRuleType("D304", "그룹_3_유형_4"));
			other_3.setTypes(types_3);
			others.add(other_3);
			set.setOthers(others);
			return set;
		}
	}
	
	public static void main(String[] args) {
		// "Paranoid" version
		ChangeOverview overview = new ChangeOverview();
		overview.setRuleset(BizRuleSetBuilder.build());
		BizRuleSet ruleset = overview.getRuleset();
		System.out.println(ruleset);
	}
}
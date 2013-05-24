package ood.immutable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WrapperOverCollection_2_JSON {
	
	private static final String Q = "\"";
	private static final String C = ":";
	
	static class ChangeOverview {
		private BizRuleSet ruleset;
		public void setRuleset(BizRuleSet ruleset) {
			this.ruleset = ruleset;
		}
		public String getRuleset() {
			return ruleset.asMap();
		}
	}
	static class BizRuleSet {
		private List<BizRuleType> majors = new ArrayList<BizRuleType>();
		public void setMajors(List<BizRuleType> majors) {
			this.majors = majors;
		}
		public List<BizRuleType> getMajors() {
			return this.majors;
		}
		private List<BizRuleType> recents = new ArrayList<BizRuleType>();
		public void setRecents(List<BizRuleType> recents) {
			this.recents = recents;
		}
		public List<BizRuleType> getRecents() {
			return this.recents;
		}
		private List<BizRuleGroup> others = new ArrayList<BizRuleGroup>();
		public void setOthers(List<BizRuleGroup> others) {
			this.others = others;
		}
		public List<BizRuleGroup> getOthers() {
			return this.others;
		}
		public String asMap() {
			return JSON.map(this);
		}
		static class JSON {
			public static String map(BizRuleSet set) {
				StringBuffer sb = new StringBuffer();
				sb.append("{");
				sb.append(Q).append("majors").append(Q).append(C).append(typesAsList(set.majors)).append(",");
				sb.append(Q).append("recents").append(Q).append(C).append(typesAsList(set.recents)).append(",");
				sb.append(Q).append("others").append(Q).append(C).append(groupsAsList(set.others));
				sb.append("}");
				return sb.toString();
			}
			private static String typesAsList(List<BizRuleType> types) {
				StringBuffer sb = new StringBuffer();
				sb.append("[");
				int i = 0;
				for (BizRuleType type : types) {
					if (i++ > 0) sb.append(",");
					sb.append(type.asMap());
				}
				sb.append("]");
				return sb.toString();
			}
			private static String groupsAsList(List<BizRuleGroup> groups) {
				StringBuffer sb = new StringBuffer();
				sb.append("[");
				int i = 0;
				for (BizRuleGroup group : groups) {
					if (i++ > 0) sb.append(",");
					sb.append(group.asMap());
				}
				sb.append("]");
				return sb.toString();
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
			this.types = types;
		}
		public List<BizRuleType> getTypes() {
			return this.types;
		}
		public String asMap() {
			return Mapper.map(this);
		}
		static class Mapper {
			public static String map(BizRuleGroup group) {
				StringBuffer sb = new StringBuffer();
				sb.append("{");
				sb.append(Q).append("code").append(Q).append(C).append(Q).append(group.code).append(Q).append(",");
				sb.append(Q).append("name").append(Q).append(C).append(Q).append(group.name).append(Q).append(",");
				sb.append(Q).append("types").append(Q).append(C).append(typesAsList(group.types));
				sb.append("}");
				return sb.toString();
			}
			private static String typesAsList(List<BizRuleType> types) {
				StringBuffer sb = new StringBuffer();
				sb.append("[");
				int i = 0;
				for (BizRuleType type : types) {
					if (i++ > 0) sb.append(",");
					sb.append(type.asMap());
				}
				sb.append("]");
				return sb.toString();
			}
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
			this.selected = true;
		}
		public boolean getSelected() {
			return this.selected;
		}
		public String asMap() {
			return Mapper.map(this);
		}
		static class Mapper {
			public static String map(BizRuleType type) {
				StringBuffer sb = new StringBuffer();
				sb.append("{");
				sb.append(Q).append("code").append(Q).append(C).append(Q).append(type.code).append(Q).append(",");
				sb.append(Q).append("name").append(Q).append(C).append(Q).append(type.name).append(Q);
				if (type.selected) {
					sb.append(",").append(Q).append("selected").append(Q).append(C).append(type.selected);
				}
				sb.append("}");
				return sb.toString();
			}
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
		// JSON
		ChangeOverview overview = new ChangeOverview();
		overview.setRuleset(BizRuleSetBuilder.build());
		String json = overview.getRuleset();
		System.out.println(json);
	}
}
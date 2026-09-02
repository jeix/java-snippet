package modern.java21.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AvoidNullCheck {
    
    // Modern: Using Objects.requireNonNullElse (Java 9+)
    class ReturnNull {
        private String s;
        public String getString() { return s; }
        public void setString(String s) { this.s = s; }
        
        private List<String> items;
        public List<String> getList() { return items; }
        public void setList(List<String> items) { this.items = items; }
    }
    
    // Modern: Return empty instead of null
    class ReturnEmpty {
        private String s;
        public String getString() { return Objects.requireNonNullElse(s, ""); }
        public void setString(String s) { this.s = s; }
        
        private List<String> items;
        public List<String> getList() { return Objects.requireNonNullElseGet(items, ArrayList::new); }
        public void setList(List<String> items) { this.items = items; }
    }
    
    // Modern: Using Objects.requireNonNullElse
    public String empty_if_null(String s) {
        return Objects.requireNonNullElse(s, "");
    }
    
    public String trim_or_empty(String s) {
        return Objects.requireNonNullElse(s, "").trim();
    }
    
    public void test_null_check() {
        ReturnNull obj = new ReturnNull();
        if (obj.getString() != null) {
            System.out.println("NEVER come here");
            System.out.println("[" + obj.getString().trim() + "]");
        }
        System.out.println("[" + empty_if_null(obj.getString()).trim() + "]");
        System.out.println("[" + trim_or_empty(obj.getString()) + "]");
        if (obj.getList() != null) {
            System.out.println("NEVER come here");
            for (String s : obj.getList()) {
                System.out.println("[" + s.trim() + "]");
            }
        }
    }
    
    public void test_no_null_check() {
        ReturnEmpty obj = new ReturnEmpty();
        System.out.println("[" + obj.getString().trim() + "]");
        for (String s : obj.getList()) {
            System.out.println("NEVER come here");
            System.out.println("[" + s.trim() + "]");
        }
    }
    
    public void test_failed_no_null_check() {
        ReturnEmpty obj = new ReturnEmpty();
        List<String> items = new ArrayList<>();
        items.add("not null");
        obj.setList(items);
        
        contaminate(obj);
        
        for (String str : obj.getList()) {
            try {
                System.out.println("[" + str.trim() + "]");
            } catch (NullPointerException nfe) {
                System.out.println("contaminated : " + nfe);
            }
        }
    }
    
    private void contaminate(ReturnEmpty obj) {
        List<String> items = obj.getList();
        if (items.isEmpty()) {
            items.add(null);
        } else {
            items.set(0, null);
        }
    }
    
    // Modern: Using Optional (Java 8+)
    public void test_with_optional() {
        System.out.println("// Using Optional (Java 8+)");
        ReturnNull obj = new ReturnNull();
        
        String result = java.util.Optional.ofNullable(obj.getString())
            .map(String::trim)
            .orElse("");
        System.out.println("Optional result: [" + result + "]");
        
        List<String> listResult = java.util.Optional.ofNullable(obj.getList())
            .orElseGet(ArrayList::new);
        System.out.println("Optional list size: " + listResult.size());
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_null_check();
        test_no_null_check();
        test_failed_no_null_check();
        test_with_optional();
        test_nothing();
    }
    
    public static void main(String[] args) {
        AvoidNullCheck worker = new AvoidNullCheck();
        worker.test();
    }
}
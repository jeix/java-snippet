package modern.java21.lang.void_;

import java.sql.Date;
import java.util.Objects;

import static modern.java21.lang.void_.NullProof.Nada.*;

// assert requires VM option -ea
public class NullProof {
    
    // Modern: Using Objects.requireNonNullElse, String::isBlank (Java 11+)
    static class Nada {
        public static String trim(String s) {
            return Objects.requireNonNullElse(s, "").trim();
        }
        
        public static String string(Date date) {
            return date != null ? date.toString() : "";
        }
        
        public static String nada(String s, String... alters) {
            String ns = Objects.requireNonNullElse(s, "");
            if (ns.isBlank()) {
                for (String t : alters) {
                    String nt = Objects.requireNonNullElse(t, "");
                    if (nt.isBlank()) continue;
                    return nt;
                }
                return "";
            }
            return ns;
        }
        
        public static String concat(String s, String... args) {
            StringBuilder sb = new StringBuilder(Objects.requireNonNullElse(s, ""));
            for (String t : args) {
                if (Objects.requireNonNullElse(t, "").isBlank()) continue;
                sb.append(t);
            }
            return sb.toString();
        }
    }
    
    // Modern: record for immutable wrapper (Java 16+)
    record Nil<T>(T v) {
        public String trim() {
            return string().trim();
        }
        
        public String string() {
            return Objects.requireNonNullElse(v, "").toString();
        }
        
        public String concat(Object... args) {
            StringBuilder sb = new StringBuilder(string());
            for (Object o : args) {
                if (o != null) sb.append(o);
            }
            return sb.toString();
        }
        
        @SuppressWarnings("unchecked")
        public int compareTo(T o) {
            if (v != null) {
                if (o == null) return 1;
                if (v instanceof Comparable) {
                    return ((Comparable<T>) v).compareTo(o);
                } else {
                    return string().compareTo(o.toString());
                }
            } else {
                return o != null ? -1 : 0;
            }
        }
    }
    
    private void test_nada() {
        String s = null;
        String t = null;
        String res = nada(trim(s), trim(t), "N");
        assert res.equals("N") : "Expected N but got " + res;
        
        Date dt1 = null;
        String dt2 = null;
        res = nada(string(dt1), trim(dt2), "2012-04-24");
        assert res.equals("2012-04-24") : "Expected 2012-04-24 but got " + res;
        
        dt1 = Date.valueOf("2012-04-24");
        dt2 = "2011-07-24";
        assert string(dt1).compareTo(dt2) > 0;
        
        res = "N".concat(nada(null));
        assert res.equals("N") : "Expected N but got " + res;
        
        res = concat(s, "", res, null, "N");
        assert res.equals("NN") : "Expected NN but got " + res;
    }
    
    private void test_nil() {
        String s = null;
        Date dt = Date.valueOf("2012-04-24");
        
        Nil<String> s1 = new Nil<>(s);
        assert s1.trim().equals("");
        assert s1.concat(dt, s, "N", 3, false).equals("2012-04-24N3false");
        
        dt = null;
        Nil<Date> dt1 = new Nil<>(dt);
        assert dt1.string().equals("");
        assert dt1.compareTo(Date.valueOf("2012-04-24")) < 0;
        assert dt1.string().compareTo("2011-07-24") < 0;
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_nada();
        test_nil();
        test_nothing();
    }
    
    public static void main(String[] args) {
        NullProof worker = new NullProof();
        worker.test();
    }
}
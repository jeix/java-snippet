package modern.java21.collection;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class OrderedKeyValPairsDemo {
    
    // Modern: Use LinkedHashMap directly (already maintains insertion order since Java 1.4)
    // Or use a record for the value
    static class Code {
        
        private final String k;
        private final String v;
        
        public Code(String k, String v) {
            this.k = k;
            this.v = v;
        }
        
        public String getK() {
            return k;
        }
        public String getV() {
            return v;
        }
        
        public String toString() {
            return "{'" + k + "':'" + v + "'}";
        }
    }
    
    // Modern implementation using LinkedHashMap directly
    static class OrderedKeyValPairs<K,V> {
        
        private final Map<K,V> map;
        
        public OrderedKeyValPairs() {
            this.map = new LinkedHashMap<>();
        }
        
        public void add(K k, V v) {
            if (k != null) {
                map.put(k, v); // LinkedHashMap maintains insertion order
            }
        }
        
        public V remove(K k) {
            if (k != null && map.containsKey(k)) {
                return map.remove(k);
            }
            return null;
        }
        
        public boolean contains(K k) {
            return map.containsKey(k);
        }
        
        public void clear() {
            map.clear();
        }
        
        public V get(K k) {
            return map.get(k);
        }
        
        public List<K> keys() {
            return new ArrayList<>(map.keySet());
        }
        
        public List<V> values() {
            return new ArrayList<>(map.values());
        }
        
        public int size() {
            return map.size();
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            int i = 0;
            for (Map.Entry<K,V> entry : map.entrySet()) {
                if (i++ > 0) sb.append(",");
                sb.append("{'" + entry.getKey() + "':" + entry.getValue() + "}");
            }
            sb.append("]");
            return sb.toString();
        }
    }
    
    private void test_ordered_kv_pairs() {
        OrderedKeyValPairs<String,Code> pairs = new OrderedKeyValPairs<>();
        pairs.add("1745", new Code("1745", "ADEV"));
        pairs.add("2371", new Code("2371", "GEJV"));
        pairs.add("3649", new Code("3649", "PKCF"));
        
        System.out.println("'2371' => " + pairs.get("2371"));
        
        System.out.println("iteration:");
        for (String key : pairs.keys()) {
            System.out.println("\t'" + key + "' => " + pairs.get(key));
        }
        
        System.out.println("iteration:");
        for (Code code : pairs.values()) {
            System.out.println("\t" + code);
        }
        
        System.out.println(pairs);
        
        if (pairs.contains("3649")) {
            System.out.println("contains key '3649'");
        }
        if (! pairs.contains("668")) {
            System.out.println("not contains key '668'");
        }
        
        pairs.add("2371", new Code("2371", "HVKW"));
        System.out.println("now '2371' => " + pairs.get("2371"));
        
        System.out.println("remove " + pairs.remove("3649"));
        
        System.out.println(pairs);
        
        pairs.clear();
        
        System.out.println("cleared, " + pairs.size() + " remains, " + pairs);
    }
    
    // Modern: Demonstrate Map.ofEntries for immutable ordered map (Java 9+)
    private void test_map_of_entries() {
        System.out.println("// Map.ofEntries (Java 9+) - immutable ordered map");
        Map<String, Code> immutableMap = Map.ofEntries(
            Map.entry("1745", new Code("1745", "ADEV")),
            Map.entry("2371", new Code("2371", "GEJV")),
            Map.entry("3649", new Code("3649", "PKCF"))
        );
        
        System.out.println("'2371' => " + immutableMap.get("2371"));
        System.out.println("Size: " + immutableMap.size());
        System.out.println("Keys: " + immutableMap.keySet());
        System.out.println("Values: " + immutableMap.values());
        System.out.println("Map: " + immutableMap);
        
        // This would throw UnsupportedOperationException
        try {
            immutableMap.put("9999", new Code("9999", "TEST"));
        } catch (UnsupportedOperationException uoe) {
            System.out.println("Immutable map: " + uoe.getClass().getSimpleName());
        }
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_ordered_kv_pairs();
        test_map_of_entries();
        test_nothing();
    }
    
    public static void main(String[] args) {
        OrderedKeyValPairsDemo worker = new OrderedKeyValPairsDemo();
        worker.test();
    }
}
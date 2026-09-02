package modern.java21.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RemoveDuringIterationTest {
    
    class ListTest {
        private void test() {
            // not works
            test_with_Iterator_then_ArrayList_remove(prepare());
            test_with_for_each_then_ArrayList_remove(prepare());
            // works
            test_with_counting_then_ArrayList_remove(prepare());
            test_with_toArray_then_for_each_then_ArrayList_remove(prepare());
            test_with_Iterator_then_Iterator_remove(prepare());
            // Modern Java 21+ ways
            test_with_removeIf(prepare());
            test_with_stream_filter(prepare());
        }
        
        private List<String> prepare() {
            List<String> items = new ArrayList<>();
            items.add("고구마");
            items.add("고도리");
            items.add("고사리");
            return items;
        }
        
        private void test_with_Iterator_then_ArrayList_remove(List<String> items) {
            try {
                for (Iterator<String> iter = items.iterator(); iter.hasNext(); ) {
                    String item = iter.next();
                    if (item.length() > 0) {
                        items.remove(item);
                        System.out.println("removed: " + item);
                    }
                }
            } catch (java.util.ConcurrentModificationException cme) {
                System.err.println(cme.getClass().getCanonicalName());
            }
            System.out.println(items.size() + " items remained");
        }
        
        private void test_with_for_each_then_ArrayList_remove(List<String> items) {
            try {
                for (String item : items) {
                    if (item.length() > 0) {
                        items.remove(item);
                        System.out.println("removed: " + item);
                    }
                }
            } catch (java.util.ConcurrentModificationException cme) {
                System.err.println(cme.getClass().getCanonicalName());
            }
            System.out.println(items.size() + " items remained");
        }
        
        private void test_with_counting_then_ArrayList_remove(List<String> items) {
            int iteration_last = items.size();
            int iteration_cnt = 1;
            int i = 0;
            while (iteration_cnt <= iteration_last) {
                String item = items.get(i);
                if (item.length() > 0) {
                    items.remove(i);
                    System.out.println("removed: " + item);
                } else {
                    i++;
                }
                iteration_cnt++;
            }
            System.out.println(items.size() + " items remained");
        }
        
        private void test_with_toArray_then_for_each_then_ArrayList_remove(List<String> items) {
            String[] item_array = {};
            item_array = items.toArray(item_array);
            Arrays.sort(item_array);
            for (String item : item_array) {
                if (item.length() > 0) {
                    items.remove(item);
                    System.out.println("removed: " + item);
                }
            }
            System.out.println(items.size() + " items remained");
        }
        
        private void test_with_Iterator_then_Iterator_remove(List<String> items) {
            for (Iterator<String> iter = items.iterator(); iter.hasNext(); ) {
                String item = iter.next();
                if (item.length() > 0) {
                    iter.remove();
                    System.out.println("removed: " + item);
                }
            }
            System.out.println(items.size() + " items remained");
        }
        
        // Modern Java 21+ ways
        private void test_with_removeIf(List<String> items) {
            System.out.println("// removeIf (Java 8+)");
            items.removeIf(item -> item.length() > 0);
            System.out.println(items.size() + " items remained");
        }
        
        private void test_with_stream_filter(List<String> items) {
            System.out.println("// stream().filter().collect() (Java 8+)");
            List<String> filtered = items.stream()
                .filter(item -> item.length() == 0)
                .toList();
            System.out.println(filtered.size() + " items remained (filtered)");
        }
    }
    
    class MapTest {
        private void test() {
            // not works
            test_with_entrySet_then_Iterator_then_Set_remove(prepare());
            test_with_entrySet_then_for_each_then_Set_remove(prepare());
            test_with_keySet_then_Iterator_then_HashMap_remove(prepare());
            test_with_keySet_then_Iterator_then_Set_remove(prepare());
            test_with_keySet_then_for_each_then_HashMap_remove(prepare());
            // works
            test_with_keySet_then_toArray_then_for_each_then_HashMap_remove(prepare());
            test_with_entrySet_then_Iterator_then_Iterator_remove(prepare());
            test_with_keySet_then_Iterator_then_Iterator_remove(prepare());
            // Modern Java 21+ ways
            test_with_removeIf_entrySet(prepare());
            test_with_stream_filter_map(prepare());
        }
        
        private Map<String,String> prepare() {
            Map<String,String> items = new HashMap<>();
            items.put("고구마","고구마");
            items.put("고도리","고도리");
            items.put("고사리","고사리");
            return items;
        }
        
        private void test_with_entrySet_then_Iterator_then_Set_remove(Map<String,String> items) {
            Set<Map.Entry<String,String>> entries = items.entrySet();
            try {
                for (Iterator<Map.Entry<String,String>> iter = entries.iterator(); iter.hasNext(); ) {
                    Map.Entry<String,String> entry = iter.next();
                    String key = entry.getKey();
                    String val = entry.getValue();
                    if (val.length() > 0) {
                        entries.remove(entry);
                        System.out.println("removed: " + key);
                    }
                }
            } catch (java.util.ConcurrentModificationException cme) {
                System.err.println(cme.getClass().getCanonicalName());
            }
            System.out.println(items.size() + " items remained");
        }
        
        private void test_with_entrySet_then_for_each_then_Set_remove(Map<String,String> items) {
            Set<Map.Entry<String,String>> entries = items.entrySet();
            try {
                for (Map.Entry<String,String> entry : entries) {
                    String key = entry.getKey();
                    String val = entry.getValue();
                    if (val.length() > 0) {
                        entries.remove(entry);
                        System.out.println("removed: " + key);
                    }
                }
            } catch (java.util.ConcurrentModificationException cme) {
                System.err.println(cme.getClass().getCanonicalName());
            }
            System.out.println(items.size() + " items remained");
        }
        
        private void test_with_keySet_then_Iterator_then_HashMap_remove(Map<String,String> items) {
            Set<String> keys = items.keySet();
            try {
                for (Iterator<String> iter = keys.iterator(); iter.hasNext(); ) {
                    String key = iter.next();
                    String val = items.get(key);
                    if (val.length() > 0) {
                        items.remove(key);
                        System.out.println("removed: " + key);
                    }
                }
            } catch (java.util.ConcurrentModificationException cme) {
                System.err.println(cme.getClass().getCanonicalName());
            }
            System.out.println(items.size() + " items remained");
        }
        
        private void test_with_keySet_then_Iterator_then_Set_remove(Map<String,String> items) {
            Set<String> keys = items.keySet();
            try {
                for (Iterator<String> iter = keys.iterator(); iter.hasNext(); ) {
                    String key = iter.next();
                    String val = items.get(key);
                    if (val.length() > 0) {
                        keys.remove(key);
                        System.out.println("removed: " + key);
                    }
                }
            } catch (java.util.ConcurrentModificationException cme) {
                System.err.println(cme.getClass().getCanonicalName());
            }
            System.out.println(items.size() + " items remained");
        }
        
        private void test_with_keySet_then_for_each_then_HashMap_remove(Map<String,String> items) {
            Set<String> keys = items.keySet();
            try {
                for (String key : keys) {
                    String val = items.get(key);
                    if (val.length() > 0) {
                        items.remove(key);
                        System.out.println("removed: " + key);
                    }
                }
            } catch (java.util.ConcurrentModificationException cme) {
                System.err.println(cme.getClass().getCanonicalName());
            }
            System.out.println(items.size() + " items remained");
        }
        
        private void test_with_keySet_then_toArray_then_for_each_then_HashMap_remove(Map<String,String> items) {
            Set<String> keys = items.keySet();
            String[] key_array = {};
            key_array = keys.toArray(key_array);
            Arrays.sort(key_array);
            for (String key : key_array) {
                String val = items.get(key);
                if (val.length() > 0) {
                    items.remove(key);
                    System.out.println("removed: " + key);
                }
            }
            System.out.println(items.size() + " items remained");
        }
        
        private void test_with_entrySet_then_Iterator_then_Iterator_remove(Map<String,String> items) {
            Set<Map.Entry<String,String>> entries = items.entrySet();
            for (Iterator<Map.Entry<String,String>> iter = entries.iterator(); iter.hasNext(); ) {
                Map.Entry<String,String> entry = iter.next();
                String key = entry.getKey();
                String val = entry.getValue();
                if (val.length() > 0) {
                    iter.remove();
                    System.out.println("removed: " + key);
                }
            }
            System.out.println(items.size() + " items remained");
        }
        
        private void test_with_keySet_then_Iterator_then_Iterator_remove(Map<String,String> items) {
            Set<String> keys = items.keySet();
            for (Iterator<String> iter = keys.iterator(); iter.hasNext(); ) {
                String key = iter.next();
                String val = items.get(key);
                if (val.length() > 0) {
                    iter.remove();
                    System.out.println("removed: " + key);
                }
            }
            System.out.println(items.size() + " items remained");
        }
        
        // Modern Java 21+ ways
        private void test_with_removeIf_entrySet(Map<String,String> items) {
            System.out.println("// removeIf on entrySet (Java 8+)");
            items.entrySet().removeIf(entry -> entry.getValue().length() > 0);
            System.out.println(items.size() + " items remained");
        }
        
        private void test_with_stream_filter_map(Map<String,String> items) {
            System.out.println("// stream().filter().collect() on Map (Java 8+)");
            Map<String,String> filtered = items.entrySet().stream()
                .filter(entry -> entry.getValue().length() == 0)
                .collect(java.util.stream.Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue
                ));
            System.out.println(filtered.size() + " items remained (filtered)");
        }
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        new ListTest().test();
        new MapTest().test();
        test_nothing();
    }
    
    public static void main(String[] args) {
        RemoveDuringIterationTest worker = new RemoveDuringIterationTest();
        worker.test();
    }
}
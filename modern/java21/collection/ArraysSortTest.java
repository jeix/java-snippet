package modern.java21.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArraysSortTest {
    
    private void test_Arrays_sort() {
        test_sort_not_comparable();
        test_sort_comparable();
    }
    
    private void test_sort_not_comparable() {
        System.out.println("// test sort not comparable");
        List<Tomato> list = null;
        for (int capacity = 0; capacity <= 4; capacity++) {
            for (int cnt = 0; cnt <= capacity; cnt++) {
                list = new ArrayList<>(capacity);
                for (int ix = 0; ix < cnt; ix++) {
                    list.add(new_tomato(ix));
                }
                try {
                    list = sort_tomato(list);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(capacity + "::" + cnt);
            }
        }
    }
    
    private void test_sort_comparable() {
        System.out.println("// test sort comparable");
        List<Tomato> list = null;
        for (int capacity = 0; capacity <= 4; capacity++) {
            for (int cnt = 0; cnt <= capacity; cnt++) {
                list = new ArrayList<>(capacity);
                for (int ix = 0; ix < cnt; ix++) {
                    list.add(new_comparable_tomato(ix));
                }
                try {
                    list = sort_tomato(list);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(capacity + "::" + cnt);
            }
        }
    }
    
    // Modern: use List.sort with Comparator or stream().sorted()
    private List<Tomato> sort_tomato(List<Tomato> list) {
        // Using List.sort with Comparator.nullsFirst/Last
        list.sort(Comparator
            .nullsFirst(Comparator.comparing(Tomato::getName, Comparator.nullsFirst(String::compareTo)))
            .thenComparing(t -> t.getClass().getSimpleName())
        );
        return list;
    }
    
    // Modern: stream().sorted() approach
    private List<Tomato> sort_tomato_stream(List<Tomato> list) {
        return list.stream()
            .sorted(Comparator
                .nullsFirst(Comparator.comparing(Tomato::getName, Comparator.nullsFirst(String::compareTo)))
            )
            .toList();
    }
    
    private Tomato new_tomato(int i) {
        String name = String.valueOf(i);
        Tomato t = new Tomato(name);
        return t;
    }
    
    private Tomato new_comparable_tomato(int i) {
        String name = String.valueOf(i);
        Tomato t = new ComparableTomato(name);
        return t;
    }
    
    private void test_Comparable_compareTo() {
        System.out.println("// test Comparable.compareTo()");
        ComparableTomato t1 = new ComparableTomato();
        System.out.println(t1.compareTo(null));
        ComparableTomato t2 = new ComparableTomato();
        System.out.println(t1.compareTo(t2));
        t1 = new ComparableTomato("one");
        System.out.println(t2.compareTo(t1));
        System.out.println(t1.compareTo(null));
        System.out.println(t1.compareTo(t2));
        t2 = new ComparableTomato("two");
        System.out.println(t1.compareTo(t2));
        System.out.println(t2.compareTo(t1));
    }
    
    private void test_Arrays_sort_alt() {
        System.out.println("// test List.sort() alt");
        List<Tomato> list = new ArrayList<>();
        ComparableTomato t = null;
        t = new ComparableTomato("one");
        list.add(t);
        t = new ComparableTomato("two");
        list.add(t);
        t = new ComparableTomato();
        list.add(t);
        t = new ComparableTomato();
        list.add(t);
        t = new ComparableTomato("nil");
        list.add(t);
        t = new ComparableTomato("two");
        list.add(t);
        list.add(null);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("[" + i + "]=" + list.get(i));
        }
        try {
            list = sort_tomato(list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("[" + i + "]=" + list.get(i));
        }
    }
    
    private void test_modern_sort() {
        System.out.println("// test modern stream().sorted()");
        List<Tomato> list = new ArrayList<>();
        list.add(new ComparableTomato("one"));
        list.add(new ComparableTomato("two"));
        list.add(new ComparableTomato());
        list.add(new ComparableTomato());
        list.add(new ComparableTomato("nil"));
        list.add(new ComparableTomato("two"));
        list.add(null);
        
        List<Tomato> sorted = sort_tomato_stream(list);
        for (int i = 0; i < sorted.size(); i++) {
            System.out.println("[" + i + "]=" + sorted.get(i));
        }
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test_all() {
        test_Arrays_sort();
        test_Comparable_compareTo();
        test_Arrays_sort_alt();
        test_modern_sort();
        test_nothing();
    }
    
    static class Tomato {
        
        public Tomato() {
        }
        public Tomato(String name) {
            this.name = name;
        }
        
        protected String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        
        public String toString() {
            if (name == null)
                return "{" + name + "}";
            else
                return "{\"" + name + "\"}";
        }
    }
    
    static class ComparableTomato extends Tomato implements Comparable<ComparableTomato> {
        
        public ComparableTomato() {
            super();
        }
        public ComparableTomato(String name) {
            super(name);
        }
        
        public int compareTo(ComparableTomato t) {
            System.out.println(this + ".compareTo " + t);
            if (t == null) return 1;
            if (this == t) return 0;
            String t_name = t.getName();
            if (name == null) {
                if (t_name == null) return 0;
                return -1;
            }
            if (t_name == null) return 1;
            return name.compareTo(t.getName());
        }
    }
    
    public static void main(String[] args) {
        ArraysSortTest worker = new ArraysSortTest();
        worker.test_all();
    }
}
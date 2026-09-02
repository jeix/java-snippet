package modern.java21.collection;

import static modern.java21.test.Expect.expect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayExtendTester {
    
    private void test_with_declared_to_unknown_size() {
        List<String> list = List.of();
        expect(0, list.size());
        expect("[]", json_of_list(list));
        
        // cannot do this with immutable list - demonstrates the point
        try {
            list.add("1");
            expect(UnsupportedOperationException.class);
        } catch (UnsupportedOperationException uoe) {
            expect(UnsupportedOperationException.class, uoe.getClass());
        }
        
        // do this - create new list with added elements
        list = extend_list(list, "1", "2", "3");
        expect(3, list.size());
        expect("[1, 2, 3]", json_of_list(list));
    }
    
    private void test_with_declared_to_known_size() {
        // For known size with nulls, use array or ArrayList
        String[] a = new String[3];
        expect(3, a.length);
        expect("[null, null, null]", json_of_array(a));
        
        a[0] = "1";
        a[1] = "2";
        a[2] = "3";
        expect(3, a.length);
        expect("[1, 2, 3]", json_of_array(a));
    }
    
    private void test_with_declared_and_initialized() {
        List<String> list = List.of("1", "2", "3");
        expect(3, list.size());
        expect("[1, 2, 3]", json_of_list(list));
        
        list = extend_list(list, "4", "5", "6");
        expect(6, list.size());
        expect("[1, 2, 3, 4, 5, 6]", json_of_list(list));
    }
    
    private String json_of_list(List<String> list) {
        if (list != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(list.get(i));
            }
            sb.append("]");
            return sb.toString();
        }
        return null;
    }
    
    private String json_of_array(String[] a) {
        if (a != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < a.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(a[i]);
            }
            sb.append("]");
            return sb.toString();
        }
        return null;
    }
    
    @SafeVarargs
    private final List<String> extend_list(List<String> list, String... adds) {
        if (adds.length > 0) {
            List<String> newList = new ArrayList<>(list.size() + adds.length);
            newList.addAll(list);
            newList.addAll(Arrays.asList(adds));
            return newList;
        }
        return list;
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_with_declared_to_unknown_size();
        test_with_declared_to_known_size();
        test_with_declared_and_initialized();
        test_nothing();
    }
    
    public static void main(String[] args) {
        ArrayExtendTester worker = new ArrayExtendTester();
        worker.test();
    }
}
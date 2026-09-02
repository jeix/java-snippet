package modern.java21.lang.for_each;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ForEachDemo {
    
    // Modern: Using Stream API (Java 8+)
    private void test_for_each() {
        List<String> xlist = new ArrayList<>(List.of("foo", "bar", "baz"));
        
        // Modern: forEach with lambda
        xlist.replaceAll(String::toUpperCase);
        System.out.println(xlist); // -> [FOO, BAR, BAZ]
    }
    
    private void test_every() {
        List<Integer> numbers = List.of(0, 1, 2, 3);
        boolean result = numbers.stream().allMatch(n -> n % 2 == 0);
        System.out.println(result); // -> false
        
        result = numbers.stream().allMatch(n -> n < 5);
        System.out.println(result); // -> true
    }
    
    private void test_some() {
        List<Integer> numbers = List.of(0, 1, 2, 3);
        boolean result = numbers.stream().anyMatch(n -> n % 2 == 1);
        System.out.println(result); // -> true
        
        result = numbers.stream().anyMatch(n -> n > 5);
        System.out.println(result); // -> false
    }
    
    private void test_filter() {
        List<String> xlist = List.of("foo", "bar", "baz");
        
        List<String> filtered = xlist.stream()
            .filter(s -> s.equals("bar"))
            .collect(Collectors.toList());
        
        System.out.println(filtered); // -> [bar]
    }
    
    private void test_map() {
        List<String> xlist = List.of("foo", "bar", "baz");
        
        List<String> ylist = xlist.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        
        System.out.println(ylist); // -> [FOO, BAR, BAZ]
    }
    
    private void test_reduce() {
        List<Integer> numbers = List.of(0, 1, 2, 3);
        
        int sum = numbers.stream()
            .reduce(0, Integer::sum);
        
        System.out.println(sum); // -> 6
    }
    
    private void test_reduce_2() {
        List<List<Integer>> numbers = List.of(
            List.of(0, 1),
            List.of(2, 3),
            List.of(4, 5)
        );
        
        List<Integer> result = numbers.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
        
        System.out.println(result); // -> [0, 1, 2, 3, 4, 5]
    }
    
    private void test_reduce_right() {
        List<Integer> numbers = List.of(0, 1, 2, 3);
        
        int sum = numbers.stream()
            .reduce(0, Integer::sum);
        
        System.out.println(sum); // -> 6
    }
    
    private void test_reduce_right_2() {
        List<List<Integer>> numbers = List.of(
            List.of(0, 1),
            List.of(2, 3),
            List.of(4, 5)
        );
        
        List<Integer> result = numbers.stream()
            .reduce((a, b) -> {
                List<Integer> combined = new ArrayList<>(b);
                combined.addAll(a);
                return combined;
            })
            .orElse(List.of());
        
        System.out.println(result); // -> [4, 5, 2, 3, 0, 1]
    }
    
    private void test_nothing() {
        System.out.println(":wq");
    }
    
    public void test() {
        test_for_each();
        test_every();
        test_some();
        test_filter();
        test_map();
        test_reduce();
        test_reduce_2();
        test_reduce_right();
        test_reduce_right_2();
        test_nothing();
    }
    
    public static void main(String[] args) {
        ForEachDemo worker = new ForEachDemo();
        worker.test();
    }
}
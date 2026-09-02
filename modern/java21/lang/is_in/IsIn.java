package modern.java21.lang.is_in;

import java.util.Arrays;

public class IsIn {
    // Modern: Using List/Set/Stream (Java 8+)
    public static boolean is_in(String s, String... args) {
        if (args.length == 1 && args[0].contains(",")) {
            // CSV format
            return Arrays.stream(args[0].split(",")).anyMatch(part -> part.trim().equals(s));
        }
        return Arrays.stream(args).anyMatch(arg -> arg.equals(s));
    }
    
    public static boolean is_in(Integer i, String csv) {
        return Arrays.stream(csv.split(",")).anyMatch(part -> part.trim().equals(i.toString()));
    }
    
    public static boolean is_in(Integer i, Integer... args) {
        return Arrays.stream(args).anyMatch(arg -> i.equals(arg));
    }
    
    // Modern: Generic version with proper type handling
    public static <T> boolean isin(T x, T... args) {
        if (args.length == 1 && args[0] instanceof String csv && csv.contains(",")) {
            return Arrays.stream(csv.split(",")).anyMatch(part -> part.trim().equals(x.toString()));
        }
        return Arrays.stream(args).anyMatch(arg -> arg != null && arg.equals(x));
    }
    
    // Overloads for specific types with CSV
    public static boolean isin(Integer i, String csv) {
        return is_in(i, csv);
    }
    public static boolean isin(Long l, String csv) {
        return Arrays.stream(csv.split(",")).anyMatch(part -> part.trim().equals(l.toString()));
    }
    public static boolean isin(Float f, String csv) {
        return Arrays.stream(csv.split(",")).anyMatch(part -> part.trim().equals(f.toString()));
    }
    public static boolean isin(Double d, String csv) {
        return Arrays.stream(csv.split(",")).anyMatch(part -> part.trim().equals(d.toString()));
    }
    
    // Overloads for varargs
    public static boolean isin(String s, String... args) {
        return is_in(s, args);
    }
    public static boolean isin(Integer i, Integer... args) {
        return is_in(i, args);
    }
    public static boolean isin(Long l, Long... args) {
        return Arrays.stream(args).anyMatch(arg -> l.equals(arg));
    }
    public static boolean isin(Float f, Float... args) {
        return Arrays.stream(args).anyMatch(arg -> Float.compare(f, arg) == 0);
    }
    public static boolean isin(Double d, Double... args) {
        return Arrays.stream(args).anyMatch(arg -> Double.compare(d, arg) == 0);
    }
}
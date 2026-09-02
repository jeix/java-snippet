package modern.java21.lang.void_.until_not_void;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public class UntilNotVoid {
    
    public static String until_not_void(String... args) {
        return Arrays.stream(args)
            .filter(Objects::nonNull)
            .filter(s -> !s.isBlank())
            .findFirst()
            .orElse(args.length > 0 ? args[args.length - 1] : null);
    }
    
    public static BigDecimal until_not_void(BigDecimal... args) {
        return Arrays.stream(args)
            .filter(Objects::nonNull)
            .filter(bd -> !BigDecimal.ZERO.equals(bd))
            .findFirst()
            .orElse(args.length > 0 ? args[args.length - 1] : null);
    }
    
    // Original overloaded methods (kept for compatibility)
    public static String unv(String... args) {
        for (String arg : args) {
            if (arg != null && !arg.isBlank()) return arg;
        }
        return args.length > 0 ? args[args.length - 1] : null;
    }
    
    public static Integer unv(Integer... args) {
        for (Integer arg : args) {
            if (arg != null && arg != 0) return arg;
        }
        return args.length > 0 ? args[args.length - 1] : null;
    }
    
    public static Long unv(Long... args) {
        for (Long arg : args) {
            if (arg != null && arg != 0L) return arg;
        }
        return args.length > 0 ? args[args.length - 1] : null;
    }
    
    public static Float unv(Float... args) {
        for (Float arg : args) {
            if (arg != null && arg != 0.0f) return arg;
        }
        return args.length > 0 ? args[args.length - 1] : null;
    }
    
    public static Double unv(Double... args) {
        for (Double arg : args) {
            if (arg != null && arg != 0.0) return arg;
        }
        return args.length > 0 ? args[args.length - 1] : null;
    }
    
    public static Boolean unv(Boolean... args) {
        for (Boolean arg : args) {
            if (arg != null && arg != false) return arg;
        }
        return args.length > 0 ? args[args.length - 1] : null;
    }
    
    public static BigDecimal unv(BigDecimal... args) {
        for (BigDecimal arg : args) {
            if (arg != null && !BigDecimal.ZERO.equals(arg)) return arg;
        }
        return args.length > 0 ? args[args.length - 1] : null;
    }
    
    // Modern: Generic method for any type with predicate
    @FunctionalInterface
    public interface NonVoidPredicate<T> {
        boolean test(T t);
    }
    
    @SafeVarargs
    public static <T> T firstNonVoid(NonVoidPredicate<T> predicate, T... args) {
        for (T arg : args) {
            if (arg != null && predicate.test(arg)) {
                return arg;
            }
        }
        return args.length > 0 ? args[args.length - 1] : null;
    }
}
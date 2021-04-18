package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = i -> i < 0;
        return pred(value, comparator, predicate);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = i -> i > 0;
        return pred(value, comparator, predicate);
    }

    private <T> T pred(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        if (value.size() == 0) {
            return null;
        }
        T rsl = value.get(0);
        for (T val : value) {
            if (predicate.test(comparator.compare(rsl, val))) {
                rsl = val;
            }
        }
        return rsl;
    }
}

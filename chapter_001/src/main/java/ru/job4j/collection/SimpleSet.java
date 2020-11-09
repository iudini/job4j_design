package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<T> implements Iterable<T> {

    private SimpleArray<T> array = new SimpleArray<>();

    void add(T t) {
        if (check(t)) {
            array.add(t);
        }
    }

    private boolean check(T t) {
        Iterator<T> iterator = array.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(t)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }
}

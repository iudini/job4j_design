package ru.job4j.generics;

import org.junit.Test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    @Test
    public void whenAdd() {
        SimpleArray<Integer> rsl = new SimpleArray<>();
        rsl.add(2);
        rsl.add(2);
        rsl.add(2);
        SimpleArray<Integer> expect = new SimpleArray<>(new Integer[]{2, 2, 2});
        assertThat(rsl, is(expect));
    }

    @Test
    public void whenGet() {
        SimpleArray<Integer> rsl = new SimpleArray<>(new Integer[]{2, 4, 3, 5});
        Integer expect = 4;
        assertThat(rsl.get(1), is(expect));
    }

    @Test
    public void whenSet() {
        SimpleArray<Integer> rsl = new SimpleArray<>(new Integer[]{2, 4, 3});
        Integer expect = 16;
        rsl.set(2, 16);
        assertThat(rsl.get(2), is(expect));
    }

    @Test
    public void whenRemove() {
        SimpleArray<Integer> rsl = new SimpleArray<>(new Integer[]{2, 3, 4, 5, 6, 7});
        rsl.remove(3);
        SimpleArray<Integer> expect = new SimpleArray<>(new Integer[]{2, 3, 4, 6, 7});
        assertThat(rsl, is(expect));
    }

    @Test
    public void whenIterate() {
        SimpleArray<String> sourceArray = new SimpleArray<>(new String[]{"a", "b", "c", "d", "e"});
        List<String> rsl = new ArrayList<>();
        sourceArray.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(List.of("a", "b", "c", "d", "e")));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenOutOfBound() {
        SimpleArray<Integer> rsl = new SimpleArray<>(new Integer[]{2, 3, 4, 5, 6, 7});
        rsl.remove(9);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        SimpleArray<String> sourceArray = new SimpleArray<>(new String[]{"a", "b"});
        Iterator iterator = sourceArray.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
    }
}
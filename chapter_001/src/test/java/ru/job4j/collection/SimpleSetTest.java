package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddAndIterate() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(5);
        assertThat(set.iterator().next(), is(5));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterate() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.iterator().next();
    }

    @Test
    public void whenAddSameNumberTwiceAndIterate() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(5);
        set.add(5);
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(false));
    }
}
package ru.job4j.kiss;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class MaxMinTest {
    List<Integer> list = List.of(1, 2, 3, 4);
    MaxMin maxMin = new MaxMin();

    @Test
    public void max() {
        assertThat(maxMin.max(list, Integer::compareTo), is(4));
    }

    @Test
    public void min() {
        assertThat(maxMin.min(list, Integer::compareTo), is(1));
    }

    @Test
    public void thenSize0() {
        assertThat(maxMin.max(new ArrayList<>(), Integer::compareTo), is(nullValue()));
    }
}
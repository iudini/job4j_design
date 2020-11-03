package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {
    SimpleLinkedList<String> list = new SimpleLinkedList<>();

    @Test
    public void whenAddAndThenGet() {
        list.add("Joo");
        list.add("Choo");
        list.add("foo");
        list.add("bar");
        assertThat(list.get(0), is("Joo"));
        assertThat(list.get(1), is("Choo"));
        assertThat(list.get(2), is("foo"));
        assertThat(list.get(3), is("bar"));
    }

    @Test
    public void whenIterate() {
        list.add("Joo");
        list.add("foo");
        list.add("bar");
        Iterator<String> iterator = list.iterator();
        assertThat(iterator.next(), is("Joo"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("foo"));
        assertThat(iterator.next(), is("bar"));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterateOutOfBound() {
        list.add("How");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterateEmpty() {
        Iterator<String> iterator = list.iterator();
        iterator.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenModifiedThenConcurrentModificationException() {
        list.add("How");
        Iterator<String> iterator = list.iterator();
        list.add("baz");
        iterator.next();
    }
}
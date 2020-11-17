package ru.job4j.collection;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void whenInsertThenGet() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("key", "value");
        map.insert("car", "engine");
        map.insert("kata", "bushido");
        assertThat(map.get("key"), is("value"));
        assertThat(map.get("car"), is("engine"));
        assertThat(map.get("kata"), is("bushido"));
    }

    @Test
    public void whenDelete() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("key", "value");
        map.insert("car", "engine");
        map.insert("kata", "bushido");
        map.delete("key");
        map.delete("car");
        map.delete("kata");
        assertThat(map.get("key"), IsNull.nullValue());
        assertThat(map.get("car"), IsNull.nullValue());
        assertThat(map.get("kata"), IsNull.nullValue());
    }

    @Test
    public void whenIterate() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("key", "value");
        map.insert("car", "engine");
        Iterator<SimpleHashMap.Node<String, String>> iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterateThrowNoSuchElementException() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        Iterator<SimpleHashMap.Node<String, String>> iterator = map.iterator();
        iterator.next();
    }
}
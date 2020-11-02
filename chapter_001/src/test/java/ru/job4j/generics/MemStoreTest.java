package ru.job4j.generics;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MemStoreTest {
    MemStore<User> store = new MemStore<>();

    @Test
    public void add() {
        store.add(new User("12"));
        assertThat(store.findById("12").getId(), is("12"));
    }

    @Test
    public void replace() {
        store.add(new User("12"));
        store.replace("12", new User("13"));
        assertThat(store.findById("13").getId(), is("13"));
    }

    @Test
    public void delete() {
        store.add(new User("1"));
        store.delete("1");
        assertThat(store.findById("1"), IsNull.nullValue());
    }

    @Test
    public void findById() {
        store.add(new User("7"));
        store.add(new User("6"));
        store.add(new User("5"));
        assertThat(store.findById("5").getId(), is("5"));
        assertThat(store.findById("6").getId(), is("6"));
        assertThat(store.findById("7").getId(), is("7"));
    }
}
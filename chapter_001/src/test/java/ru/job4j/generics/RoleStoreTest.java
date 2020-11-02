package ru.job4j.generics;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    RoleStore roleStore = new RoleStore();

    @Test
    public void add() {
        roleStore.add(new Role("1"));
        assertThat(roleStore.findById("1").getId(), is("1"));
    }

    @Test
    public void replace() {
        roleStore.add(new Role("1"));
        roleStore.replace("1", new Role("2"));
        assertThat(roleStore.findById("2").getId(), is("2"));
    }

    @Test
    public void delete() {
        roleStore.add(new Role("1"));
        roleStore.delete("1");
        assertThat(roleStore.findById("1"), IsNull.nullValue());
    }

    @Test
    public void findById() {
        roleStore.add(new Role("1"));
        assertThat(roleStore.findById("1").getId(), is("1"));
    }
}
package ru.job4j.gc.cache;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void getName() {
        Cache cache = new Cache("C:\\projects\\job4j_design\\chapter_004\\src\\main\\resources\\");
        String rsl = cache.get("names.txt");
        String expected = "Glen" + System.lineSeparator()
                + "Moran" + System.lineSeparator()
                + "Adam" + System.lineSeparator()
                + "Peter" + System.lineSeparator();
        assertThat(rsl, is(expected));
    }

    @Test
    public void getAddress() {
        Cache cache = new Cache("C:\\projects\\job4j_design\\chapter_004\\src\\main\\resources\\");
        String rsl = cache.get("addresses.txt");
        String expected = "Moscow" + System.lineSeparator()
                + "St.Petersburg" + System.lineSeparator()
                + "Kazan" + System.lineSeparator()
                + "Novgorod" + System.lineSeparator()
                + "Paris" + System.lineSeparator()
                + "London" + System.lineSeparator();
        assertThat(rsl, is(expected));
    }
}
package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void unavailable() {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "unavailable.csv");
        StringJoiner result = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader("unavailable.csv"))) {
            read.lines().forEach(result::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String expected = "10:58:01;10:59:01;" + System.lineSeparator() + "11:01:02;11:02:02;";
        assertThat(result.toString(), is(expected));
    }
}
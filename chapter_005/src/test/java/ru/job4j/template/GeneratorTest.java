package ru.job4j.template;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {
    Generator generator = (template, args) -> null;

    @Test
    public void produce() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Petr Arsentev", "subject", "you");
        String expected = generator.produce(template, map);
        assertThat("I am a Petr Arsentev, Who are you? ", is(expected));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongKey() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Petr Arsentev", "sudject", "you");
        String expected = generator.produce(template, map);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenExtraKeyInMap() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Petr Arsentev", "subject", "you", "map", "key");
        String expected = generator.produce(template, map);
    }
}
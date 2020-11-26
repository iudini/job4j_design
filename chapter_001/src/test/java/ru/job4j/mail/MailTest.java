package ru.job4j.mail;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MailTest {

    @Test
    public void merge() {
        Map<String, Set<String>> map = new LinkedHashMap<>();
        map.put("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        map.put("user2", Set.of("foo@gmail.com", "ups@pisem.net"));
        map.put("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        map.put("user4", Set.of("ups@pisem.net", "aaa@bbb.ru"));
        map.put("user5", Set.of("xyz@pisem.net"));
        Map<String, Set<String>> rsl = new HashMap<>();
        rsl.put("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru"));
        rsl.put("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        assertThat(Mail.merge(map), is(rsl));
    }
}
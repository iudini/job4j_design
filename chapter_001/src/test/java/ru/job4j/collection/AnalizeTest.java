package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void when2UserAdded() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Ivan");
        Analize.User user2 = new Analize.User(2, "Petr");
        Analize.User user3 = new Analize.User(3, "Nick");
        Analize.User user4 = new Analize.User(4, "Roman");
        Analize.User user5 = new Analize.User(5, "Igor");
        List<Analize.User> previous = List.of(user1, user2, user3);
        List<Analize.User> current = List.of(user1, user2, user3, user4, user5);
        assertThat(analize.diff(previous, current), is(new Analize.Info(2, 0, 0)));
    }

    @Test
    public void when2UserAddedAnd1Changed() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Ivan");
        Analize.User user2 = new Analize.User(2, "Petr");
        Analize.User user3 = new Analize.User(3, "Nick");
        Analize.User user4 = new Analize.User(4, "Roman");
        Analize.User user5 = new Analize.User(5, "Igor");
        Analize.User user6 = new Analize.User(2, "Greg");
        List<Analize.User> previous = List.of(user1, user2, user3);
        List<Analize.User> current = List.of(user1, user6, user3, user4, user5);
        assertThat(analize.diff(previous, current), is(new Analize.Info(2, 1, 0)));
    }

    @Test
    public void when1UserDeleted() {
        Analize analize = new Analize();
        Analize.User user1 = new Analize.User(1, "Ivan");
        Analize.User user2 = new Analize.User(2, "Petr");
        Analize.User user3 = new Analize.User(3, "Nick");
        Analize.User user4 = new Analize.User(4, "Roman");
        Analize.User user5 = new Analize.User(5, "Igor");
        List<Analize.User> previous = List.of(user1, user2, user3, user4, user5);
        List<Analize.User> current = List.of(user1, user2, user3);
        assertThat(analize.diff(previous, current), is(new Analize.Info(0, 0, 2)));
    }
}
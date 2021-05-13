package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStore implements Store {
    List<Food> store = new ArrayList<>();

    @Override
    public void add(Food food) {
        store.add(food);
    }

    @Override
    public void clear() {
        store = new ArrayList<>();
    }

    @Override
    public List<Food> get() {
        return store;
    }
}

package ru.job4j.design.lsp;

import java.util.List;

public interface Storage {
    void add(Food food);
    List<Food> get();
}

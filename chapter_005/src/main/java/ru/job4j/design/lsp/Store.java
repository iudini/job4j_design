package ru.job4j.design.lsp;

import java.util.List;

public interface Store {

    List<Food> get();

    void clear();
}

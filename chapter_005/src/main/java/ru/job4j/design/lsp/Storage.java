package ru.job4j.design.lsp;

import java.util.List;

public interface Storage {

    List<Food> get();

    boolean accept(Food food);
}

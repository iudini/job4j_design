package ru.job4j.design.lsp;

public interface Storage extends Store {

    boolean accept(Food food);
}

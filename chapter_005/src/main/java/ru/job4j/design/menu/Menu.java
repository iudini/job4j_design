package ru.job4j.design.menu;

public interface Menu {
    boolean add(String name, Action action);

    boolean add(String parent, String name, Action action);
}

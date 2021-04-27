package ru.job4j.design.lsp;

import java.util.List;

public interface Store {

    <T> List<T> get();
}

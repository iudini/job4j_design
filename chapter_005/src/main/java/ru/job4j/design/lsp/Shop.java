package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    List<Food> stock = new ArrayList<>();

    @Override
    public void add(Food food) {
        stock.add(food);
    }

    @Override
    public List<Food> get() {
        return stock;
    }
}

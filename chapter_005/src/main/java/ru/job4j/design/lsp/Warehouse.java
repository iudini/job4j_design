package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    List<Food> stock = new ArrayList<>();

    @Override
    public List<Food> get() {
        return stock;
    }

    @Override
    public boolean accept(Food food) {
        int check = CheckExpire.check(food);
        if (check > 75) {
            stock.add(food);
            return true;
        }
        return false;
    }
}

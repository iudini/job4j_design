package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    List<Food> stock = new ArrayList<>();

    @Override
    public List<Food> get() {
        return stock;
    }

    @Override
    public boolean accept(Food food) {
        int check = CheckExpire.check(food);
        if (check > 25 && check <= 75) {
            stock.add(food);
            return true;
        }
        if (check > 0 && check <= 25) {
            food.setDiscount(0.25);
            stock.add(food);
            return true;
        }
        return false;
    }
}

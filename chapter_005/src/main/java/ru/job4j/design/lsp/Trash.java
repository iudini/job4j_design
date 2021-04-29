package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {
    List<Food> stock = new ArrayList<>();

    @Override
    public List<Food> get() {
        List<Food> rsl = new ArrayList<>();
        for (Food food : stock) {
            rsl.add(new Food(food.name, food.expireDate, food.createDate, food.price, food.discount));
        }
        return rsl;
    }

    @Override
    public boolean accept(Food food) {
        int check = CheckExpire.check(food);
        if (check <= 0) {
            stock.add(food);
            return true;
        }
        return false;
    }
}

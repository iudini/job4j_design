package ru.job4j.design.lsp;

import java.util.List;

public class Warehouse implements Storage {
    Store store;

    public Warehouse(Store store) {
        this.store = store;
    }

    @Override
    public boolean accept(Food food) {
        int check = CheckExpire.check(food);
        if (check > 75) {
            return true;
        }
        return false;
    }

    @Override
    public void add(Food food) {
        store.add(food);
    }

    @Override
    public void clear() {
        store.clear();
    }

    @Override
    public List<Food> get() {
        return store.get();
    }
}

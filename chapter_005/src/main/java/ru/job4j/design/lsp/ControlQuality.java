package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public void add(Food food) {
        for (Storage storage : storages) {
            if (storage.accept(food)) {
                break;
            }
        }
    }

    public void resort() {
        List<Food> temporaryStorage = new ArrayList<>();
        for (var storage : storages) {
            temporaryStorage.addAll(storage.get());
            storage.clear();
        }
        for (var food : temporaryStorage) {
            add(food);
        }
    }
}

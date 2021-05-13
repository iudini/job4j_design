package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final CQStorage storage;

    public ControlQuality(CQStorage storage) {
        this.storage = storage;
    }

    public void add(Food food) {
        List<Storage> storageList = storage.getStorages();
        for (var storage : storageList) {
            if (storage.accept(food)) {
                break;
            }
        }
    }

    public void resort() {
        List<Storage> storageList = storage.getStorages();
        List<Food> temporaryStorage = new ArrayList<>();
        for (var storage : storageList) {
            temporaryStorage.addAll(storage.get());
            storage.clear();
        }
        for (var food : temporaryStorage) {
            add(food);
        }
    }
}

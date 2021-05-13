package ru.job4j.design.lsp;

import java.util.List;

public class InMemoryCQStorage implements CQStorage {
    List<Storage> storages;

    public InMemoryCQStorage(List<Storage> storages) {
        this.storages = storages;
    }

    @Override
    public List<Storage> getStorages() {
        return storages;
    }
}

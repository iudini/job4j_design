package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = findIndexById(id);
        if (index != -1) {
            mem.set(index, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int index = findIndexById(id);
        if (index != -1) {
            return mem.remove(mem.get(index));
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T m : mem) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public int findIndexById(String id) {
        int index = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
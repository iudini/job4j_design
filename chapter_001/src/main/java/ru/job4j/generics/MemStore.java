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
        if (findById(id) != null) {
            T t = findById(id);
            for (int i = 0; i < mem.size(); i++) {
                if (mem.get(i).equals(t)) {
                    mem.set(i, model);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (findById(id) != null) {
            return mem.remove(findById(id));
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
}
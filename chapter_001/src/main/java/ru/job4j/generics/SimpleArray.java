package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable {
    private int size = 10;
    private int index = 0;
    private T[] tArray;

    public SimpleArray() {
        tArray = (T[]) new Object[size];
    }

    public SimpleArray(T[] tArray) {
        if (tArray.length > size) {
            size = tArray.length;
        }
        this.tArray = tArray;
        index = tArray.length;
    }

    void checkSize() {
        if (index == size) {
            size *= 2;
            tArray = Arrays.copyOf(tArray, size);
        }
    }

    void add(T model) {
        checkSize();
        tArray[index] = model;
        index++;
    }

    void set(int index, T model) {
        Objects.checkIndex(index, size);
        tArray[index] = model;
    }

    void remove(int index) {
        Objects.checkIndex(index, this.index);
        T[] temp = (T[]) new Object[size];
        System.arraycopy(tArray, 0, temp, 0, index);
        System.arraycopy(tArray, index + 1, temp, index, this.index - 1 - index);
        this.index--;
        tArray = temp;
    }

    T get(int index) {
        Objects.checkIndex(index, size);
        return tArray[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArray<?> that = (SimpleArray<?>) o;
        if (size != that.size) {
            return false;
        }
        for (int i = 0; i < index; i++) {
            if (!tArray[i].equals(that.tArray[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, index);
        result = 31 * result + Arrays.hashCode(tArray);
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator() {
            int pointer = 0;
            @Override
            public boolean hasNext() {
                return pointer < index;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return tArray[pointer++];
            }
        };
    }
}

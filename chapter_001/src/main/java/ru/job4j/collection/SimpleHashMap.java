package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>> {

    private final float LOAD_FACTOR = 0.75f;
    private int capacity = 16;
    private int size = 0;
    private int modCount = 0;
    private Node<K, V>[] container = new Node[capacity];

    int index(K key) {
        return key.hashCode() & (capacity - 1);
    }

    boolean insert(K key, V value) {
        if (size >= capacity * LOAD_FACTOR) {
            grow();
        }
        int index = index(key);
        if (container[index] != null) {
            return false;
        }
        container[index] = new Node<>(key, value);
        size++;
        modCount++;
        return true;
    }

    private void grow() {
        capacity = size * 2;
        Node<K, V>[] newSizeMap = new Node[capacity];
        size = 0;
        for (var node : container) {
            int index = node.key.hashCode() & (capacity - 1);
            newSizeMap[index] = node;
            size++;
        }
        container = newSizeMap;
    }

    V get(K key) {
        int index = index(key);
        if (container[index] == null) {
            return null;
        }
        if (Objects.equals(key, container[index].getKey())) {
            return container[index].getValue();
        }
        return null;
    }

    boolean delete(K key){
        int index = index(key);
        if (container[index] == null) {
            return false;
        }
        if (Objects.equals(key, container[index].getKey())) {
            container[index] = null;
            size--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<>() {
            int index = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && container[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }

    public static class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}

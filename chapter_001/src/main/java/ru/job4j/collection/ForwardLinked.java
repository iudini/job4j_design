package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        if (head == null) {
            head = new Node<T>(value, null);
            return;
        }
        Node<T> node = head;
        head = new Node<>(value, node);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T t = head.value;
        head = head.next;
        return t;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T t = head.value;
        if (head.next == null) {
            head = null;
            return t;
        }
        Node<T> prevNode = head;
        Node<T> node = head.next;
        t = node.value;
        while (node.next != null) {
            prevNode = node;
            node = node.next;
            t = node.value;
        }
        prevNode.next = null;
        return t;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
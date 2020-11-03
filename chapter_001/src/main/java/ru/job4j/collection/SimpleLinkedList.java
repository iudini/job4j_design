package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements Iterable<E> {

    private int size = 0;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    void add(E value) {
        if (size == 0) {
            Node<E> newNode = new Node<>(null, value, null);
            first = newNode;
            last = newNode;
            size++;
            modCount++;
        } else {
            Node<E> oldLast = last;
            last = new Node<>(oldLast, value, null);
            oldLast.next = last;
            size++;
            modCount++;
        }
    }

    E get(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            return first.elem;
        }
        if (index == size - 1) {
            return last.elem;
        }
        Node<E> newNode;
        if (size - index < index) {
            newNode = first;
            while (index != 0) {
                index--;
                newNode = newNode.next;
            }
        } else {
            newNode = last;
            while (index != size - 1) {
                index++;
                newNode = newNode.prev;
            }
        }
        return newNode.elem;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            final int expectedModCount = modCount;
            Node<E> node;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (index == 0) {
                    node = first;
                } else {
                    node = node.next;
                }
                index++;
                return node.elem;
            }
        };
    }

    private static class Node<E> {
        E elem;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E elem, Node<E> next) {
            this.elem = elem;
            this.prev = prev;
            this.next = next;
        }
    }
}

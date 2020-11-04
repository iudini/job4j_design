package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                T t = in.pop();
                out.push(t);
            }
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}

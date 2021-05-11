package ru.job4j.design.menu;

import java.util.LinkedList;
import java.util.List;

public class Node {
    private final String name;
    private final String prefix;
    private final List<Node> children = new LinkedList<>();
    private final Action action;

    public Node(String name, Action action, String prefix) {
        this.name = name;
        this.action = action;
        this.prefix = prefix;
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public List<Node> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public Action getAction() {
        return action;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public String toString() {
        return prefix + " " + name;
    }
}

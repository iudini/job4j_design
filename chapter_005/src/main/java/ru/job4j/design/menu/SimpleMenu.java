package ru.job4j.design.menu;

import java.util.*;

public class SimpleMenu implements Menu, PrintMenu {
    List<Node> roots;

    public SimpleMenu() {
        this.roots = new LinkedList<>();
    }

    @Override
    public boolean add(String name, Action action) {
        roots.add(new Node(name, action, "Задача " + (roots.size() + 1) + "."));
        return true;
    }

    @Override
    public boolean add(String parent, String name, Action action) {
        Node parentNode = search(roots, parent);
        if (parentNode == null) {
            return false;
        }
        parentNode.addChild(new Node(name, action, "----" + parentNode.getPrefix() + (parentNode.getChildren().size() + 1) + "."));
        return true;
    }

    private Node search(List<Node> list, String name) {
        for (var node : list) {
            if (node.getName().equals(name)) {
                return node;
            }
            if (node.getChildren() != null) {
                Node childNode = search(node.getChildren(), name);
                if (childNode != null && childNode.getName().equals(name)) {
                    return childNode;
                }
            }
        }
        return null;
    }

    public void doAction(String name) {
        Node node = search(roots, name);
        if (node != null) {
            node.getAction().execute();
        }
    }

    @Override
    public void printMenu() {
        for (var node : roots) {
            System.out.println(node.toString());
            if (node.getChildren() != null) {
                printChild(node.getChildren());
            }
        }
    }

    private void printChild(List<Node> children) {
        for (var node : children) {
            System.out.println(node.toString());
            if (node.getChildren() != null) {
                printChild(node.getChildren());
            }
        }
    }
}

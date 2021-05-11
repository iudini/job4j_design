package ru.job4j.design.menu;

public class DefaultAction implements Action {
    @Override
    public boolean execute() {
        System.out.println("do something");
        return false;
    }
}

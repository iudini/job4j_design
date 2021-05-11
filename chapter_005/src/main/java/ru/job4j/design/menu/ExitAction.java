package ru.job4j.design.menu;

public class ExitAction implements Action {
    @Override
    public boolean execute() {
        System.out.println("bye");
        return true;
    }
}

package ru.job4j.ood.lsp;

public class Lift {
    private int floors;

    public String call(int floor) {
        if (floor < 1) {
            throw new IllegalArgumentException("нет подземного этажа");
        }
        if (floors < 6) {
            throw new IllegalArgumentException("в здании нет лифта");
        }
        return "поехали";
    }
}

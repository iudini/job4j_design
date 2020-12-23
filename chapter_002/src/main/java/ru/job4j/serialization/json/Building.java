package ru.job4j.serialization.json;

public class Building {
    private final int floors;

    public Building(int floors) {
        this.floors = floors;
    }

    public int getFloors() {
        return floors;
    }

    @Override
    public String toString() {
        return "Building{"
                + "floors=" + floors
                + '}';
    }
}

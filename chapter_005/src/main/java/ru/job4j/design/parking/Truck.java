package ru.job4j.design.parking;

public class Truck implements Car {
    private final int size;

    public Truck(int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Size must be bigger than 1");
        }
        this.size = size;
    }

    @Override
    public int getSize(Car car) {
        return size;
    }
}

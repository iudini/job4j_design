package ru.job4j.design.parking;

public class PassengerCar implements Car {
    private final int size = 1;

    @Override
    public int getSize(Car car) {
        return size;
    }
}

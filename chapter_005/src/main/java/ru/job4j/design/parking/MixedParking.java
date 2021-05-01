package ru.job4j.design.parking;

public class MixedParking implements Parking {
    private int carPosition = 0;
    private int truckPosition = 0;
    private final Car[] carParking;
    private final Car[] truckParking;

    public MixedParking(int car, int truck) {
        if (car <= 0 || truck <= 0) {
            throw new IllegalArgumentException("Park must have more than 0 places for each type");
        }
        this.carParking = new Car[car];
        this.truckParking = new Car[truck];
    }

    @Override
    public boolean park(Car car) {
        int size = car.getSize(car);
        if (size == 1) {
            if (carPosition < carParking.length) {
                carParking[carPosition++] = car;
                return true;
            }
        } else {
            if (truckPosition < truckParking.length) {
                truckParking[truckPosition++] = car;
                return true;
            }
            if (carPosition + size <= carParking.length) {
                for (int i = carPosition + size; carPosition < i;) {
                    carParking[carPosition++] = car;
                }
                return true;
            }
        }
        return false;
    }
}

package ru.job4j.design.parking;

public class MixedParking implements Parking {
    private final int passengerCar;
    private final int truck;
    private final Car[] parking;

    public MixedParking(int passengerCar, int truck) {
        if (passengerCar <= 0 || truck <= 0) {
            throw new IllegalArgumentException("Park must have more than 0 places for each type");
        }
        this.passengerCar = passengerCar;
        this.truck = truck;
        this.parking = new Car[passengerCar + truck];
    }

    @Override
    public boolean park(Car car) {
        int size = car.getSize(car);
        if (size == 1) {
            for (int i = 0; i < passengerCar; i++) {
                if (parking[i] == null) {
                    parking[i] = car;
                    return true;
                }
            }
        } else {
            for (int i = passengerCar; i < parking.length; i++) {
                if (parking[i] == null) {
                    parking[i] = car;
                    return true;
                }
            }
            for (int i = 0; i < passengerCar; i++) {
                boolean freePlace = true;
                for (int j = i; j < i + size; j++) {
                    if (j == passengerCar) {
                        freePlace = false;
                        break;
                    }
                    if (parking[j] != null) {
                        freePlace = false;
                        break;
                    }
                }
                if (freePlace) {
                    for (int k = i; k < i + size; k++) {
                        parking[k] = car;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}

package ru.job4j.design.parking;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void whenParkTruckInSmallPassengerParking() {
        Parking parking = new MixedParking(2, 1);
        Car car1 = new Truck(3);
        Car car2 = new Truck(3);
        parking.park(car1);
        assertFalse(parking.park(car2));
    }

    @Test
    public void whenPark() {
        Parking parking = new MixedParking(100, 20);
        Car car = new Truck(3);
        Car car2 = new PassengerCar();
        assertTrue(parking.park(car));
        assertTrue(parking.park(car2));
    }

    @Test
    public void whenParkTruck() {
        Parking parking = new MixedParking(100, 20);
        Car car = new Truck(3);
        assertTrue(parking.park(car));
    }

    @Test
    public void whenParkTruckInPassengerCarPlace() {
        Parking parking = new MixedParking(100, 1);
        Car car1 = new Truck(3);
        Car car2 = new Truck(3);
        parking.park(car1);
        assertTrue(parking.park(car2));
    }
}
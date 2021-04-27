package ru.job4j.design.parking;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void whenParkPassengerCarInPassengerParking() {
        Parking parking = new PassengerCarParking(100);
        Car car = new PassengerCar();
        assertTrue(parking.park(car));
    }

    @Test
    public void whenParkTruckInPassengerParking() {
        Parking parking = new PassengerCarParking(100);
        Car car = new Truck(3);
        assertTrue(parking.park(car));
    }

    @Test
    public void whenParkTruckInSmallPassengerParking() {
        Parking parking = new PassengerCarParking(2);
        Car car = new Truck(3);
        assertFalse(parking.park(car));
    }

    @Test
    public void whenParkInMixedParking() {
        Parking parking = new MixedParking(100, 20);
        Car car = new Truck(3);
        Car car2 = new PassengerCar();
        assertTrue(parking.park(car));
        assertTrue(parking.park(car2));
    }

    @Test
    public void whenParkTruckInTruckParking() {
        Parking parking = new TruckParking(100);
        Car car = new Truck(3);
        assertTrue(parking.park(car));
    }

    @Test
    public void whenParkPassengerCarInTruckParking() {
        Parking parking = new TruckParking(100);
        Car car = new PassengerCar();
        assertFalse(parking.park(car));
    }
}
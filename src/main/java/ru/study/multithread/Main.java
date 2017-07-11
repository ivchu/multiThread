package ru.study.multithread;


import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Parking parking = new Parking(3);
        Random randomStay = new Random();
        Random randomWait = new Random();
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Car car = new Car(parking, randomWait.nextInt(10), randomStay.nextInt(500), "" + i);
            car.start();
        }
    }


}
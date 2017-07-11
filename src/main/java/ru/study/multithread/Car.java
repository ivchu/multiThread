package ru.study.multithread;

public class Car extends Thread{

    private Parking parking;
    private long timeToWait;
    private long timeToStay;
    private boolean isParked;

    public Car(Parking parking, long timeToWait, long timeToStay, String carNumber) {
        super(carNumber);
        this.parking = parking;
        this.timeToWait = timeToWait;
        this.timeToStay = timeToStay;
    }

    public long getTimeToWait() {
        return timeToWait;
    }

    public boolean isParked() {
        return isParked;
    }

    public void setParked(boolean parked) {
        isParked = parked;
    }

    @Override
    public void run() {
        parking.parkIn(this);
        try {
            Thread.sleep(timeToStay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parking.parkOut(this);
    }
}


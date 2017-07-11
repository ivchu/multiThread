package ru.study.multithread;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parking {

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final int capacity;
    private volatile int count = 0;

    public Parking(int capacity) {
        this.capacity = capacity;
    }

    public void parkIn(Car car) {
        lock.lock();
        try {
            while (capacity == count) {
                if (!notFull.await(car.getTimeToWait(), TimeUnit.MILLISECONDS)) {
                    System.out.println(car.getName() + " Мест нет");
                    return;
                }
            }
            car.setParked(true);
            count++;
            System.out.println("Машина " + car.getName() + " припарковалась" + " " + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void parkOut(Car car) {
        if (car.isParked()) {
            lock.lock();
            count--;
            System.out.println("машина " + car.getName() + " уехала" + " " + count);
            notFull.signal();
            lock.unlock();
        }
    }
}

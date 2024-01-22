package org.example.seminarTwo;

public class ProxyPattern {
    public static void main(String[] args) {
        CarI car = new CarProxy();
        car.drive();
    }

    interface CarI{
        void drive();
    }

    static class CarProxy implements CarI{

        CarI car = new Reno();
        @Override
        public void drive() {
            //сюда также можно добавить какие - либо свои настройки, проверки
            System.out.println("proxy code");
            car.drive();
        }
    }

    static class Reno implements CarI{

        @Override
        public void drive() {
            System.out.println("Reno drives");
        }
    }
}

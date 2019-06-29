package com.mayi.jiagousi.ch07_annotation;

interface Car{
    void run();
}

class Aodi implements Car{
    @Override
    public void run() {
        System.out.println("奥迪");
    }
}

class Benci implements Car{
    @Override
    public void run() {
        System.out.println("奔驰");
    }
}

public class FactoryDemo {

    public static Car createCar(String name){
        Car car = null;
        switch (name){
            case "aodi":
                car = new Aodi();
                break;
            case "benchi":
                car = new Benci();
                break;
        }
        return car;
    }

    public static void main(String[] args) {
        Car aodi = FactoryDemo.createCar("aodi");
        aodi.run();
    }
}

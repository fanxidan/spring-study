package com.kuang.demo03;

public class Landlord implements RentInterface {
    @Override
    public void rent() {
        System.out.println("房东出租房子");
    }
}

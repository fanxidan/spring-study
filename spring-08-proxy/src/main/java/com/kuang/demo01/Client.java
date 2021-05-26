package com.kuang.demo01;

public class Client {
    public static void main(String[] args) {
        Landlord landlord = new Landlord();
        landlord.rent();
        System.out.println("使用代理：");
        //使用代理的方式,通过中介租房子，会有一些附属操作，如看房，签合同等
        Proxy proxy = new Proxy(landlord);
        proxy.rent();
    }
}

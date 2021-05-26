package com.kuang.demo01;

public class Proxy implements RentInterface{
    private Landlord landlord;

    public Proxy(Landlord landlord) {
        this.landlord = landlord;
    }

    public Proxy() {
    }

    @Override
    public void rent() {
        seeHouse();
        landlord.rent();
        hetong();
        fare();
    }

    public void seeHouse(){
        System.out.println("中介带你看房");
    }

    public void fare(){
        System.out.println("收中介费");
    }

    public void hetong(){
        System.out.println("签合同");
    }
}

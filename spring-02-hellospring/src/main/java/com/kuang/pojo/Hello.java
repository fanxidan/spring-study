package com.kuang.pojo;

public class Hello {
    private String str;

    @Override
    public String toString() {
        return "hello{" +
                "str='" + str + '\'' +
                '}';
    }

    public void setStr(String str) {
        this.str = str;
    }
}

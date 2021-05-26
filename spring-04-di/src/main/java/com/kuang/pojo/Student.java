package com.kuang.pojo;

import java.util.*;

public class Student {
    private String name;
    private Address address;
    private String[] books;
    private List<String> hobbies;
    private Map<String,String> card;
    private Set<String> games;
    private Properties info;
    private String haswife;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                "\naddress=" + address +
                "\nbooks=" + Arrays.toString(books) +
                "\nhobbies=" + hobbies +
                "\ncard=" + card +
                "\ngames=" + games +
                "\ninfo=" + info +
                "\nhaswife='" + haswife + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public void setCard(Map<String, String> card) {
        this.card = card;
    }

    public void setGames(Set<String> games) {
        this.games = games;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    public void setHaswife(String haswife) {
        this.haswife = haswife;
    }
}

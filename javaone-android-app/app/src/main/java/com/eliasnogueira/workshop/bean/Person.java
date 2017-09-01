package com.eliasnogueira.workshop.bean;

/**
 * Created by eliasnogueira on 4/1/16.
 */
public class Person {

    private String id;
    private String name;
    private String address;
    private String hobbies;


    public Person(String name, String address, String hobbies) {
        this.name = name;
        this.address = address;
        this.hobbies = hobbies;
    }

    public Person(String id, String name, String address, String hobbies) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.hobbies = hobbies;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String toString() {
        return name;
    }

}

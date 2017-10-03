
package com.eliasnogueira.workshop.api.model;

/**
 * Person
 * @author Elias Nogueira | @eliasnogueira
 * @author Edson Yanaga | @yanaga
 */
public class Person {
    
    private int id;
    private String name;
    private String address;
    private String hobbies;

    public Person (int id, String name, String address, String hobbies) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.hobbies = hobbies;
    }
    
    public Person (String name, String address, String hobbies) {
        this.name = name;
        this.address = address;
        this.hobbies = hobbies;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the hobbies
     */
    public String getHobbies() {
        return hobbies;
    }

    /**
     * @param hobbies the hobbies to set
     */
    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
    
}

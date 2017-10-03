/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaone.backend;

import com.javaone.backend.controller.PersonJpaController;
import com.javaone.backend.pojo.Person;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author eliasnogueira
 */
public class PersonPersistenceTest {
    
    private static EntityManagerFactory emf = null;
    private static PersonJpaController jpa = null;
    
    @BeforeClass
    public static void preCondition() {
        emf = Persistence.createEntityManagerFactory("javaone-backend");
        jpa = new PersonJpaController(emf);
    }
    
    @Test
    public void createAndFindPersonTest() {
        jpa.create(
                new Person(
                        "TEST_CREATING_NAME", 
                        "TEST_CREATION_ADDRESS", 
                        "TEST_CREATION_HOBBIES")
        );
        
        jpa.findPerson(1);
    }
    
   
    private Person createPerson(String name, String address, String hobbies) {
        Person person = new Person();
        person.setName(name);
        person.setAddress(address);
        person.setHobbies(hobbies);
        
        return person;
    }
    
    @AfterClass
    public static void postCondition() {
        emf.close();
    }
    
}

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
import static org.junit.Assert.*;
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
        Person person = new Person(
                        "TEST_CREATING_NAME", 
                        "TEST_CREATION_ADDRESS", 
                        "TEST_CREATION_HOBBIES");
        
        jpa.create(person);
        
        Person personReturned = 
                jpa.findPerson(person.getId());
        
        assertEquals("TEST_CREATING_NAME", personReturned.getName());
        assertEquals("TEST_CREATION_ADDRESS", personReturned.getAddress());
        assertEquals("TEST_CREATION_HOBBIES", personReturned.getHobbies());
    }
    
    @AfterClass
    public static void postCondition() {
        emf.close();
    }
    
}

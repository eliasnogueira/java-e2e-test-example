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
import org.junit.Test;
/**
 *
 * @author eliasnogueira
 */
public class PersonPersistenceTest {
    
    
    
    public PersonPersistenceTest() {
    }
    
    
    @Test
    public void createPersonTest() {
        Person person = new Person();
        person.setName("Teste");
        person.setAddress("Teste");
        person.setHobbies("Teste");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.eliasnogueira_javaone-backend_jar_1.0-SNAPSHOTPU");
        
        PersonJpaController jpa = new PersonJpaController(emf);
        jpa.create(person);
    }
    
   
}

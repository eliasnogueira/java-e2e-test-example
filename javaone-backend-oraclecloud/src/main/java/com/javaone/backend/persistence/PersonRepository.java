package com.javaone.backend.persistence;

import com.javaone.backend.pojo.Person;

import java.util.*;

public class PersonRepository {

    private static final Map<Integer, Person> DATA = new TreeMap<>();

    public List<Person> findPeople() {
        return new ArrayList<>(DATA.values());
    }

    public Person findById(Integer id) {
        return DATA.get(id);
    }

    public void save(Person person) {
        DATA.put(person.getId(), person);
    }

    public void remove(Integer id) {
        DATA.remove(id);
    }

}

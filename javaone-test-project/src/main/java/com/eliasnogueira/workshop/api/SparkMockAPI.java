package com.eliasnogueira.workshop.api;

import com.eliasnogueira.workshop.api.model.Person;
import com.google.gson.Gson;
import java.util.ArrayList;
import spark.Request;
import static spark.Spark.*;

/**
 * API Mock
 *
 * @author Elias Nogueira | @eliasnogueira
 * @author Edson Yanaga | @yanaga
 */
public class SparkMockAPI {

    private static ArrayList<Person> persons = null;
    private static Gson gson = null;

    public static void main(String[] args) {
        port(6666);
        
        gson = new Gson();

        // initial data
        persons = initData();
        
        get("/", (req, res) -> {
            res.type("application/json");
            return gson.toJson(new Status("success"));
        });

        get("/api/person", (req, res) -> persons, gson::toJson);
        
        get("/api/person/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));

            res.type("application/json");
            System.out.println(gson.toJson(findPersonById(id)));
            return gson.toJson(findPersonById(id));
        });

        post("/api/person", "application/json", (req, res) -> {
            Person person = gson.fromJson(req.body(), Person.class);

            person.setId(getNextID());
            persons.add(person);

            res.type("application/json");
            res.status(201);
            return gson.toJson(person);
        });

        put("/api/person/:id", (req, res) -> {
            System.out.println("Body: " + req.body());
            
            res.type("application/json");
            return gson.toJson(updatePerson(req));
        });

        delete("/api/person/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));

            persons.remove(findPersonById(id));
            
            res.type("application/json");
            res.status(202);
            return gson.toJson(new Status("success"));
        });
    }

    /**
     * Initial API data
     *
     * @return list of persons
     */
    private static ArrayList<Person> initData() {
        persons = new ArrayList<>();

        persons.add(new Person(1, "Elias Nogueira", "RS", "Automate tests"));
        persons.add(new Person(2, "Vinicius Senger", "SP", "Create robots"));
        persons.add(new Person(3, "Edson Yanaga", "PR", "Help java community"));

        return persons;
    }

    /**
     * Last ID in the list
     *
     * @return last id
     */
    private static int getNextID() {
        return persons.get(persons.size() - 1).getId() + 1;
    }

    /**
     * Find a person by id
     *
     * @return person
     */
    private static Person findPersonById(int id) {
        Person personReturn = null;

        for (Person person : persons) {
            if (id == person.getId()) {
                personReturn = person;
            }
        }

        return personReturn;
    }

    /**
     * Update person from the list
     *
     * @param req request
     * @return Person
     */
    private static Person updatePerson(Request req) {
        int id = Integer.parseInt(req.params("id"));
        Person personRequested = gson.fromJson(req.body(), Person.class);
        
        // get the data from personRequested keeping the id
        Person personFound = findPersonById(id);
        int index = persons.lastIndexOf(personFound);

        personFound = personRequested;
        personFound.setId(id);

        persons.set(index, personRequested);

        System.out.println(personRequested);
        return personRequested;
    }
    
    static class Status {
        private String status;
        
        public Status(String status) {
            this.status = status;
        }
    }
}

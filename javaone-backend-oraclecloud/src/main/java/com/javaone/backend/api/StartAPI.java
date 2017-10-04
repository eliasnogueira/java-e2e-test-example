/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaone.backend.api;

import com.google.gson.Gson;
import com.javaone.backend.persistence.PersonRepository;
import com.javaone.backend.pojo.Person;

import static spark.Spark.*;

/**
 * Simple API
 *
 * @author Elias Nogueira (@eliasnogueira) & Edson Yanaga (@yanaga)
 */
public class StartAPI {

    private static final Gson GSON = new Gson();

    private static final PersonRepository PERSON_REPOSITORY = new PersonRepository();

    private static final String SUCCESS = "success";

    private static final String ERROR = "error";

    public static void main(String[] args) {
        port(Integer.parseInt(System.getProperty("PORT", "4567")));
        enableCORS();

        /**
         * get all persons
         */
        get("api/v1/person", (req, res) -> {
            return GSON.toJson(PERSON_REPOSITORY.findPeople());
        });

        /**
         * get person by id
         */
        get("api/v1/person/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Person person = PERSON_REPOSITORY.findById(id);

            res.type("application/json");
            if (person == null) {
                res.status(200);
                return GSON.toJson(new Status(ERROR, "Person does not exists"));
            } else {
                res.status(200);
                return GSON.toJson(person);
            }
        });

        /**
         * post a person
         */
        post("/api/v1/person", "application/json", (req, res) -> {
            Person person = GSON.fromJson(req.body(), Person.class);

            PERSON_REPOSITORY.save(person);

            res.type("application/json");
            res.status(201);
            return GSON.toJson(person);
        });

        /**
         * update a person thought id
         */
        put("/api/v1/person/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Person personFound = PERSON_REPOSITORY.findById(id);

            Person personInRequest = GSON.fromJson(req.body(), Person.class);
            personInRequest.setId(id);

            PERSON_REPOSITORY.save(personInRequest);

            res.type("application/json");
            return GSON.toJson(personInRequest);
        });

        /**
         * delete a person thought id
         */
        delete("/api/v1/person/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));

            PERSON_REPOSITORY.remove(id);
            res.type("application/json");
            res.status(202);
            return GSON.toJson(new Status(SUCCESS, "Person removed!"));
        });

    }

    /**
     * Status for responses
     */
    static class Status {

        private final String status;
        private final String message;

        public Status(String status, String message) {
            this.status = status;
            this.message = message;
        }
    }

    /**
     * Enable CORS for access the API thought another servers
     */
    private static void enableCORS() {
        options("/*",
                (req, res) -> {

                    String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
                    }

                    return "OK";
                }
        );

        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));
    }
}

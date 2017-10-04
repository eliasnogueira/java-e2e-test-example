package com.eliasnogueira.workshop.rest;

import com.eliasnogueira.workshop.api.model.Person;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import static org.hamcrest.CoreMatchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Run thought the Mocked API
 *
 * @author Elias Nogueira | @eliasnogueira
 * @author Edson Yanaga | @yanaga
 * 
 * @see com.eliasnogueira.workshop.api.SparkMockAPI
 */
public class FunctionalAPITest {

    @BeforeClass
    public static void setUp() {
        baseURI = "http://localhost";
        port = 4567;
        basePath = "/api/v1/";
        
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void getPersonById() {
        int personID = 
            given()
                .contentType(ContentType.JSON)
                .body(new Person("Elias Nogueira", "RS", "Automate tests")).
        when().
            post("person").
        then().
            extract().
                path("id");
        
        when().
            get("person/{id}", personID).
        then().
            contentType("application/json").and().
            body("name", equalTo("Elias Nogueira")).and().
            body("address", equalTo("RS")).and().
            body("hobbies", equalTo("Automate tests")).and().
            statusCode(200);
    }

    @Test
    public void insertPerson() {
        given()
            .contentType(ContentType.JSON)
            .body(new Person("Yara Senger", "SP", "TDC")).
        when()
            .post("person").
        then().
            body("name", equalTo("Yara Senger")).and().
            body("address", equalTo("SP")).and().
            body("hobbies", equalTo("TDC")).and().
            statusCode(201);

    }

    @Test
    public void deletePerson() {
        int personID = 
            given()
                .contentType(ContentType.JSON)
                .body(new Person("Yara Senger", "SP", "TDC")).
        when().
            post("person").
        then().
            extract().
                path("id");
                
        when().
            delete("person/{id}", personID).
        then().
            body("status", equalTo("success")).and().
            statusCode(202);
    }

    @Test
    public void alterPerson() {
        int personID = 
            given()
                .contentType(ContentType.JSON)
                .body(new Person("Elias", "RS", "Automete Tests")).
        when().
            post("person").
        then().
            extract().
                path("id");
        
        given().
            contentType(ContentType.JSON).
            body(new Person("Elias", "RS", "Pair with devs")).
        when().
            put("person/{id}", personID).
        then().
            body("name", equalTo("Elias")).and().
            body("address", equalTo("RS")).and().
            body("hobbies", equalTo("Pair with devs")).and().
            statusCode(200);
    }
}

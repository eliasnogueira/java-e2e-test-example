package com.eliasnogueira.workshop.rest;

import com.eliasnogueira.workshop.api.SparkMockAPI;
import com.eliasnogueira.workshop.api.model.Person;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import static org.hamcrest.CoreMatchers.*;

import org.testng.annotations.*;
import spark.Spark;

/**
 * Run thought the Mocked API
 *
 * @author Elias Nogueira | @eliasnogueira
 * @author Edson Yanaga | @yanaga
 * 
 * @see com.eliasnogueira.workshop.api.SparkMockAPI
 */
public class FunctionalMockAPITest {

    @BeforeMethod
    public static void setUp() {
        baseURI = "http://localhost";
        port = 6666;
        basePath = "/api";
        
        RestAssured.defaultParser = Parser.JSON;
        SparkMockAPI.main(null);
    }

    @AfterMethod
    public static void tearDown() {
        Spark.stop();
    }

    @Test
    public void getPersonById() {
        when().
            get("/person/{id}", 1).
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
            .post("/person").
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
            post("/person").
        then().
            extract().
                path("id");
                
        when().
            delete("/person/{id}", personID).
        then().
            body("status", equalTo("success")).and().
            statusCode(202);
    }

    @Test
    public void alterPerson() {
        given().
            contentType(ContentType.JSON).
            body(new Person("Bruno Souza", "SP", "Comer")).
        when().
            put("/person/{id}", 2).
        then().
            body("name", equalTo("Bruno Souza")).and().
            body("address", equalTo("SP")).and().
            body("hobbies", equalTo("Comer")).and().
            statusCode(200);
    }
}

package com.eliasnogueira.workshop.rest;

import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class MyRestTest {

    @BeforeClass
    public static void setUp() {
        baseURI = "http://localhost";
        basePath = "/api/v1/";
        port = 4567;
    }


    @Test
    public void getPersonById() {
        when()
                .get("/person/{1}", 2)
        .then()
                .contentType(ContentType.JSON).and()
                .body("name", equalTo("Elias"));
    }
}

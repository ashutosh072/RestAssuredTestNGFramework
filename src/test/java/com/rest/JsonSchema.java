package com.rest;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchema {


    @Test
    public void validate_Json_Schema() {
        given().
                baseUri("https://postman-echo.com")

                .log().all().
                when().
                get("/get").
                then().log().all().
                assertThat().statusCode(200).
                body(matchesJsonSchemaInClasspath("EchoGet.Json"));



    }


}
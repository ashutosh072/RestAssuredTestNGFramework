package com.rest;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RestAssuredFilters {
    ResponseSpecification responseSpecification;
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
//

        PrintStream FilreOutputStreame = new PrintStream(new File("restAssured.log"));
        given().
                baseUri("https://postman-echo.com")
                .filter(new RequestLoggingFilter(FilreOutputStreame)).
                filter(new ResponseLoggingFilter(FilreOutputStreame));

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addFilter(new RequestLoggingFilter(FilreOutputStreame));
        requestSpecBuilder.addFilter(new RequestLoggingFilter(FilreOutputStreame));

        RestAssured.requestSpecification =requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder =new ResponseSpecBuilder().expectStatusCode(200);

        responseSpecification = responseSpecBuilder.build();
    }



    @Test
    public void rest_Assured_Filters() {
        given().
                baseUri("https://postman-echo.com")
                        .filter(new RequestLoggingFilter(LogDetail.BODY)).
                filter(new ResponseLoggingFilter(LogDetail.STATUS))
//                .log().all().
                . when().
                get("/get").
                then().
//                log().all().

        assertThat().statusCode(200);



    }
    @Test
    public void rest_Assured_Filters_on_log_file() throws FileNotFoundException {
        //to print on log file
        PrintStream FilreOutputStreame = new PrintStream(new File("restAssured.log"));
        given().
                baseUri("https://postman-echo.com")
                .filter(new RequestLoggingFilter(FilreOutputStreame)).
                filter(new ResponseLoggingFilter(FilreOutputStreame))
//to print on spesif log details
//                .filter(new RequestLoggingFilter(LogDetail.BODY,FilreOutputStreame)).
//                filter(new ResponseLoggingFilter(LogDetail.STATUS,FilreOutputStreame))

                . when().
                get("/get").
                then().
        assertThat().statusCode(200);



    }
    @Test
    public void rest_Assured_Filters_using_Response_specification() throws FileNotFoundException {

        given(requestSpecification).
                baseUri("https://postman-echo.com")

                . when().
                get("/get").
                then().spec(responseSpecification).
                assertThat().statusCode(200);



    }


}

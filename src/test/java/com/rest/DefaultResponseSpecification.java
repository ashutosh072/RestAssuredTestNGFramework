package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.get;

public class DefaultResponseSpecification {



    @BeforeClass
    public void beforeClass() {
//    requestSpecification = given().baseUri("https://api.getpostman.com").header("x-api-key", "PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be").log().all();

//        responseSpecification= RestAssured.expect().statusCode(200).log().all();

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.getpostman.com");
        requestSpecBuilder.addHeader("x-api-key", "PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be");
        requestSpecBuilder.log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200);
        responseSpecBuilder.log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @org.testng.annotations.Test
    public void validate_get_statusCode() {
        get("workspaces");

    }

    @org.testng.annotations.Test
    public void extractResponse() {
        get("workspaces");
    }
}
package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class Post_Request {


    @BeforeClass
    public void beforeClass() {


        String payload = "{\"workspace\": \n" +
                "        {\n" +
                "            \n" +
                "            \"name\": \"Team Workspace\",\n" +
                "            \"type\": \"team\",\n" +
                "            \"description\": \"Rest assuerd created this \"\n" +
                "            \n" +
                "        }\n" +
                "}";

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.getpostman.com");
        requestSpecBuilder.addHeader("x-api-key", "PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be");
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecBuilder.setBody(payload);
        requestSpecBuilder.setContentType(ContentType.JSON);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON);
        responseSpecBuilder.log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @org.testng.annotations.Test
    public void post_request() {
        post("workspaces");

    }

@Test
    public void post_requestBy_Normal_Methods() {

    String payload = "{\"workspace\": \n" +
            "        {\n" +
            "            \n" +
            "            \"name\": \"My Workspace\",\n" +
            "            \"type\": \"team\",\n" +
            "            \"description\": \"Rest assuerd created this \"\n" +
            "            \n" +
            "        }\n" +
            "}";

    given().body(payload).
           when().post("workspaces").
            then().assertThat().body("workspace.name",equalTo("My Workspace"),"workspace.id",matchesPattern("^[a-z0-9-]{36}$"));




}

}

package com.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class JactionApi_JactionObject {

    @BeforeClass
    public void beforeClass() {



        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.getpostman.com");
        requestSpecBuilder.addHeader("x-api-key", "PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be");
        requestSpecBuilder.log(LogDetail.ALL);

        requestSpecBuilder.setContentType(ContentType.JSON);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON);
        responseSpecBuilder.log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_post_request_payload_as_map_using_Jaction() throws JsonProcessingException {
        HashMap<String,Object> mainObject =new HashMap<String,Object>();
        HashMap<String,String> nestedObject = new HashMap<String,String>();
        nestedObject.put("name","Workspace created by map object");
        nestedObject.put("type","team");
        nestedObject.put("description","Rest assuerd created this");
        mainObject.put("workspace",nestedObject);

        ObjectMapper objectMapper = new ObjectMapper();
        String mainObjectString = objectMapper.writeValueAsString(mainObject);


        //to send payload from map jaction databind dependecies is needed
        given().body(mainObjectString).log().all().
                when().post("workspaces").
                then().assertThat().body("workspace.name", equalTo("Workspace created by map object"), "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));


    }



}

package com.rest;

import com.rest.pojo.workspace.Workspace;
import com.rest.pojo.workspace.WorkspaceRoot;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ComplexPojoTest {



    @BeforeClass
    public void beforeClass() {


        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON);
        responseSpecBuilder.log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }
    @Test
    public void nested_pojo_example_serialization(){

        String payload = "{\n" +
                "    \"collection\": {\n" +
                "        \"info\": {\n" +
                "            \"name\": \"Test Collection\",\n" +
                "            \"description\": \"This collection makes a request to the Postman Echo service to get a list of request headers sent by an HTTP client.\",\n" +
                "            \"schema\": \"https://schema.getpostman.com/json/collection/v2.1.0/collection.json\"\n" +
                "        },\n" +
                "        \"item\": [\n" +
                "            {\n" +
                "                \"name\": \"Test GET Response\",\n" +
                "                \"event\": [\n" +
                "                    {\n" +
                "                        \"listen\": \"test\",\n" +
                "                        \"script\": {\n" +
                "                            \"id\": \"7d2334fc-a84a-4c3d-b26c-7529afa4c0ae\",\n" +
                "                            \"exec\": [\n" +
                "                                \"pm.test(\\\"Status code is 200\\\", function () {\",\n" +
                "                                \"    pm.response.to.have.status(200);\",\n" +
                "                                \"});\"\n" +
                "                                ],\n" +
                "                            \"type\": \"text/javascript\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ],\n" +
                "                \"request\": {\n" +
                "                    \"url\": \"https://echo.getpostman.com/headers\",\n" +
                "                    \"method\": \"GET\",\n" +
                "                    \"header\": [\n" +
                "                        {\n" +
                "                            \"key\": \"Content-Type\",\n" +
                "                            \"value\": \"application/json\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
         given().baseUri("https://api.getpostman.com").body(payload).
                header("x-api-key", "PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be").
        log().all().


                when().post("/collections").
                then().spec(responseSpecification);

    }
}

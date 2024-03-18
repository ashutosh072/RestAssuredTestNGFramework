package com.rest;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



public class Test {

    RequestSpecification RequestSpecification;

    @BeforeClass
    public void beforeClass(){
        RequestSpecification = given().baseUri("https://api.getpostman.com").header("x-api-key", "PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be");

    }



    @org.testng.annotations.Test(description = "should be able to validate status code")
    public void validate_get_statusCode(){
        given().baseUri("https://api.getpostman.com").header("x-api-key","PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be").
         when().get("workspaces").
         then().log().all().assertThat().statusCode(200).
                body("workspaces.name",hasItems("Team Workspace","My Workspace"),"workspaces.type",hasItems("team", "personal"),"workspaces[1].name", is(equalTo("My Workspace")),
                        "workspaces.size()",equalTo(45));



    }
@Description("THIS IS DESCRIPTION")
    @org.testng.annotations.Test
    public void extractResponse(){
        Response res = given().baseUri("https://api.getpostman.com").header("x-api-key", "PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be").
                given().when().get("workspaces").


                then().log().all().assertThat().statusCode(200).
                extract().response();

        System.out.println("work space name  ="+res.path("workspaces[1].name"));
        JsonPath  jp = new JsonPath(res.asString());
        System.out.println("By json path ="+ jp.get("workspaces[1].name"));



    }
    @org.testng.annotations.Test
    public void requestSpecification() {
//        RequestSpecification RequestSpecification = given().baseUri("https://api.getpostman.com").header("x-api-key", "PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be");


        given().spec(RequestSpecification).when().get("workspaces").
                then().log().all().assertThat().statusCode(200).
                extract().response();

    }

    @org.testng.annotations.Test
    public void requestSpecification2nd() {


        given().spec(RequestSpecification).when().get("workspaces").
                then().log().all().assertThat().statusCode(200).
                extract().response();

    }

    @org.testng.annotations.Test
    public void bbdToNonBBD() {
        Response res = RequestSpecification.get("workspaces");
        

    }
    }

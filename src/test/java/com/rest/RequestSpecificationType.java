package com.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RequestSpecificationType {


  RequestSpecification requestSpecification ;
  @BeforeClass
public void beforeClass() {
//    requestSpecification = given().baseUri("https://api.getpostman.com").header("x-api-key", "PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be").log().all();

    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    requestSpecBuilder.setBaseUri("https://api.getpostman.com");
    requestSpecBuilder.addHeader("x-api-key", "PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be");
    requestSpecBuilder.log(LogDetail.ALL);
    requestSpecification=requestSpecBuilder.build();

  }



  @Test
    public void bbToNonBBd(){
    Response response = given().spec(requestSpecification).get("workspaces").then().log().all().extract().response();
    assertThat(response.statusCode(),is(equalTo(200)));
    assertThat(response.path("workspaces[0].name").toString(),equalTo("Team Workspace"));



  }


  @Test
  public void requestSpecification2(){
    given().spec(requestSpecification).header("dummyHeader","ashutosh").
            when().get("workspaces").
            then().log().all().assertThat().statusCode(200);


  }
}

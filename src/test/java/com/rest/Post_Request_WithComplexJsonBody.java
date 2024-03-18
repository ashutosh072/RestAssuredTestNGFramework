package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Post_Request_WithComplexJsonBody {


    ResponseSpecification responseSpecification;
    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass(){
//    requestSpecification = given().baseUri("https://api.getpostman.com").header("x-api-key", "PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be").log().all();

//        responseSpecification= RestAssured.expect().statusCode(200).log().all();

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://f0dfdf36-dc56-4bb5-810c-8b99970be1f1.mock.pstmn.io");
        requestSpecBuilder.addHeader("x-mock-match-request-body", "true");
        requestSpecBuilder.addHeader("Content-Type", "application/json;charset=utf-8");
        requestSpecBuilder.log(LogDetail.ALL);
        RestAssured.requestSpecification =requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON);
        responseSpecBuilder.log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }




         @Test
public void validate_post_request_payload_with_complex_json_object(){

            List<Integer> al = new ArrayList<Integer>();
             al.add(5);
             al.add(9);

             HashMap <String,Object> betterHashMap2 = new HashMap<String,Object>();
             betterHashMap2.put("id",al);
             betterHashMap2.put("type","Chocolate");


             HashMap <String,Object> betterHashMap1 = new HashMap<String,Object>();
             betterHashMap1.put("id","1001");
             betterHashMap1.put("type","Regular");

             List<HashMap<String,Object>> betterArrayList= new ArrayList<HashMap<String, Object>>();
             betterArrayList.add(betterHashMap1);
             betterArrayList.add(betterHashMap2);


             HashMap<String,List<HashMap<String,Object>>> bettersHashMap = new HashMap<String,List<HashMap<String,Object>>>();
             bettersHashMap.put("batter", betterArrayList);


List <String> typeArrayList = new ArrayList<String>();

typeArrayList.add("test1");
typeArrayList.add("test2");
HashMap<String,Object> toppingHashMap2 = new HashMap<String,Object>();
             toppingHashMap2.put("id","5002");
             toppingHashMap2.put("type",typeArrayList);


             HashMap<String,Object> toppingHashMap1 = new HashMap<String,Object>();
             toppingHashMap1.put("id","5001");
             toppingHashMap1.put("type","None");

             List<HashMap<String,Object>> toppingArrayList= new ArrayList<HashMap<String, Object>>();
             toppingArrayList.add(toppingHashMap1);
             toppingArrayList.add(toppingHashMap2);

HashMap<String,Object> mainHashMap = new HashMap<String,Object>();

             mainHashMap.put("id", "0001");
             mainHashMap.put("type", "donut");
             mainHashMap.put("name", "Cake");
             mainHashMap.put("ppu", 0.55);
             mainHashMap.put("batters",bettersHashMap );
             mainHashMap.put("topping",toppingArrayList );


given().
        body(mainHashMap).log().all().
        post("/postComplexJson").
        then().spec(responseSpecification).
        assertThat().body("msg",equalTo("success"));


         }


}
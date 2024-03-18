package com.rest;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RequestQueryParameter {

    @Test
    public void single_Query_parameter(){
given().
        baseUri("https://postman-echo.com")
        .param("foo1","bar1")
        .log().all().
        when().
        get("/get").
        then().log().all().
        assertThat().statusCode(200);

    }
    @Test


    public void multiple_Query_parameter(){
        HashMap<String,String> para= new HashMap<String,String>();
        para.put("foo1","bar1");
        para.put("foo2","bar2");
        given().
                baseUri("https://postman-echo.com")
                .queryParams(para)
                .log().all().
                when().
                get("/get").
                then().log().all().
                assertThat().statusCode(200);


    }
    @Test
    public void multipleValue_Query_parameter(){
        given().
                baseUri("https://postman-echo.com")
                .param("foo1","bar1 ,bar2, bar3")
                .log().all().
                when().
                get("/get").
                then().log().all().
                assertThat().statusCode(200);

    }
    }

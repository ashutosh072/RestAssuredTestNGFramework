package com.rest;

import com.rest.pojo.simple.SimplePojo;
import com.rest.pojo.workspace.Workspace;
import com.rest.pojo.workspace.WorkspaceRoot;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class PojoClass {
@BeforeClass
    public void beforeClass() {


        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://f0dfdf36-dc56-4bb5-810c-8b99970be1f1.mock.pstmn.io");
        requestSpecBuilder.log(LogDetail.ALL);

        requestSpecBuilder.setContentType(ContentType.JSON);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON);
        responseSpecBuilder.log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }
//serialization
    @Test
    public void simple_pojo_example_searlization(){

        SimplePojo simplePojo = new SimplePojo("value1","value2");

//        SimplePojo simplePojo = new SimplePojo();
//         simplePojo.setKey1("value1");
//        simplePojo.setKey2("value2");

        given().body(simplePojo).
                when().post("/post").
                then().spec(responseSpecification).assertThat().

//        body("key1",equalTo(simplePojo.getKey1()),"key2",equalTo(simplePojo.getKey2()));

                body("key1",equalTo("value1"),
                        "key2", equalTo("value2"));




    }

    //Deserialization
    @Test
    public void simple_pojo_example_Desearlization(){

        SimplePojo simplePojo = new SimplePojo("value1","value2");


        SimplePojo deserializePojo = given().body(simplePojo).
                when().post("/post").
                then().spec(responseSpecification).assertThat().
                extract().response().as(SimplePojo.class);



    }

    @Test

    public void nested_pojo_example_serialization(){
        Workspace workspace = new Workspace("Team Workspace","team","Rest assuerd created this ");
        WorkspaceRoot workspaceRoot = new WorkspaceRoot(workspace);
        WorkspaceRoot desearalizeWorkspaceRoot = given().baseUri("https://api.getpostman.com").body(workspaceRoot).
                header("x-api-key", "PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be").
                when().post("/workspaces").
                then().spec(responseSpecification).extract().response().as(WorkspaceRoot.class);
                assertThat(desearalizeWorkspaceRoot.getWorkspace().getName(), equalTo(workspaceRoot.getWorkspace().getName()));

    }

}

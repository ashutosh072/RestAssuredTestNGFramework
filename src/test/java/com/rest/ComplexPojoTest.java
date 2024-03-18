package com.rest;

import com.rest.pojo.workspace.Workspace;
import com.rest.pojo.workspace.WorkspaceRoot;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ComplexPojoTest {

    @Test



    public void nested_pojo_example_serialization(){
         given().baseUri("https://api.getpostman.com").body("/workspace").
                header("x-api-key", "PMAK-65e46441623c670001b9f4fa-424a12379ba7ef7f1e2f59708fb88934be").



                when().post("/workspaces").
                then().spec(responseSpecification).extract().response().as(WorkspaceRoot.class);

    }
}

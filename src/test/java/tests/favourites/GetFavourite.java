package tests.favourites;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.ContentType;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import utils.Constants;
import utils.Helpers;

import java.awt.*;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasKey;

@Feature("The get favourite request in the dog api.")
public class GetFavourite {

    RequestSpecification requestSpec;

    @Before
    public void setup() {
        requestSpec = Helpers.getRequestSpec();
    }


    @Test
    public void get_favourite_with_sub() {
        //put parameters to test with different sub_ids
        Response response = given()
                .when()
                .spec(requestSpec)
                .param("sub_id", "test")
                .get();
        //response.prettyPrint();
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void get_favourite_with_favourite_id(){
        //put parameters to test with different favourite_id
        Response response = given()
                .when()
                .spec(requestSpec)
                .and()
                .basePath("favourites/1")
                .get();
        response.then().log().all();
        assertThat(response.getStatusCode(), equalTo(200));
    }

}

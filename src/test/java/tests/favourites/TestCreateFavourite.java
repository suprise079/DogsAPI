package tests.favourites;

import io.qameta.allure.Feature;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import utils.HTTPConfig;

import static io.restassured.RestAssured.given;

@Feature("Create favourite request in the dog api.")
public class TestCreateFavourite {

    RequestSpecification requestSpec;
    JSONObject requestParams;

    @Before
    public void initialiseObjects(){
        requestSpec = HTTPConfig.getFavouriteRequestSpec();

        requestParams = new JSONObject();
        requestParams.put("image_id", "MTYwNj0NQ");
        requestParams.put("sub_id", "test");
    }

    @Test
    public void create_favourite_with_sub_id(){
        //put parameters to test with different sub_id
        given()
                .when()
                .spec(requestSpec)
                .and()
                .body(requestParams.toJSONString())
                .post()
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void create_favourite_with_existing_sub_id(){
        given()
                .when()
                .spec(requestSpec)
                .and()
                .body(requestParams.toJSONString())
                .post()
                .then().log().all()
                .statusCode(400);
    }

    @Test
    public void create_favourite_with_extra_parameters(){

        requestParams = new JSONObject();
        requestParams.put("image_id", "MTYwNj0NQ");
        requestParams.put("sub_id", "test");
        requestParams.put("user_id", "extra");
        requestParams.put("image", "{\"www.picture.com\"}");

        requestParams.put("extra", "extra");
        given()
                .when()
                .spec(requestSpec)
                .and()
                .body(requestParams.toJSONString())
                .post()
                .then().log().all()
                .statusCode(400);
    }


}

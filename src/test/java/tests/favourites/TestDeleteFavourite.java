package tests.favourites;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import utils.HTTPConfig;

import static io.restassured.RestAssured.given;

public class TestDeleteFavourite {

    RequestSpecification requestSpec;

    @Before
    public void initialiseObjects(){
        requestSpec = HTTPConfig.getFavouriteRequestSpec();
    }

    @Test
    @Story("Delete favourite with id")
    @Description("Delete favourite dog using an existing id")
    public void delete_with_id(){
        String favouriteId = "70406";
        given()
                .when()
                .spec(requestSpec)
                .and()
                .basePath("favourites/" + favouriteId)
                .delete()
                .then().log().all()
                .statusCode(200);
    }

    @Test
    @Description("Delete favourite dog using a non-existing id")
    public void delete_with_non_existing_id(){
        String favouriteId = "123456";
        given()
                .when()
                .spec(requestSpec)
                .and()
                .basePath("favourites/" + favouriteId)
                .delete()
                .then().log().all()
                .statusCode(400);
    }
}

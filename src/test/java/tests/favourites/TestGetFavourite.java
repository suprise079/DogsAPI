package tests.favourites;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import utils.HTTPConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasKey;

@Feature("The get favourite request in the dog api.")
public class TestGetFavourite {

    RequestSpecification requestSpec;

    @Before
    public void setup() {
        requestSpec = HTTPConfig.getFavouriteRequestSpec();
    }


    @Test
    @Description("This test will get all the favourites for a given sub id.")
    @DisplayName("Test get favourite with sub id")
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
    @Description("This test will get all the favourites for a given favourite id.")
    @DisplayName("Test get favourite with favourite id")
    public void get_favourite_with_favourite_id(){
        //put parameters to test with different favourite_id
        Response response = given()
                .when()
                .spec(requestSpec)
                .and()
                .basePath("favourites/"+"1")
                .get();
        response.then().log().all();

        assertThat(response.getStatusCode(), equalTo(200));
    }

}

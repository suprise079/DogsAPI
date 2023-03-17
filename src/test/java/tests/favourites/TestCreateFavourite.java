package tests.favourites;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import utils.HTTPConfig;
import utils.Requests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Feature("Create favourite request in the dog api.")
public class TestCreateFavourite {

    RequestSpecification requestSpec;
    JSONObject requestParams;

    @Before
    public void initialiseObjects(){
        requestSpec = HTTPConfig.getFavouriteRequestSpec();


    }

    @BeforeEach
    public void clearRequestParams(){
        requestParams.clear();
    }

    @Test
    @Description("This test will create a favourite for a given image id and sub id.")
    @DisplayName("Test create favourite endpoint")
    public void create_favourite_with_sub_id(){
        //put parameters to test with different sub_id
        requestParams = new JSONObject();
        requestParams.put("image_id", "MTYwNj0NQ");
        requestParams.put("sub_id", "test");

        Response response = Requests.createFavourite(requestParams);

        //Test assertions
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.getBody().asString(), containsString("SUCCESS"));
    }

    @Test
    @Description("This test will create a favourite for a given sub id and already existing image id.")
    @DisplayName("Test create favourite endpoint with existing image_id")
    public void create_favourite_with_existing_image_id(){
        requestParams = new JSONObject();
        requestParams.put("image_id", "MTYwNj0NQ");
        requestParams.put("sub_id", "test");

        Response response = Requests.createFavourite(requestParams);

        //Test assertions
        assertThat(response.getStatusCode(), equalTo(400));
        assertThat(response.getBody().asString(), containsString("DUPLICATE_FAVOURITE"));

    }

    @Test
    @Description("This test will create a favourite for a given image id, sub id and extra unspecified parameters.")
    @DisplayName("Test create favourite endpoint with extra (invalid) parameters")
    public void create_favourite_with_extra_parameters(){

        requestParams = new JSONObject();
        requestParams.put("image_id", "MTYwNj0NQ");
        requestParams.put("sub_id", "test");
        requestParams.put("user_id", "extra");
        requestParams.put("image", "{\"www.picture.com\"}");
        requestParams.put("extra", "extra");

        Response response = Requests.createFavourite(requestParams);

        //Test assertions
        assertThat(response.getStatusCode(), equalTo(400));
        assertThat(response.getBody().asString(), containsString("is not allowed"));

    }


}

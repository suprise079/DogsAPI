package tests.breeds;

import data.APIData;
import data.DataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Constants;
import utils.HTTPConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.hamcrest.collection.IsMapContaining.hasValue;


@Feature("The get breeds requests in the dog api.")
public class TestGetBreeds {

    RequestSpecification requestSpec;
    JSONObject requestParams;

    @BeforeClass
    public void initialiseObjects(){
        requestSpec = HTTPConfig.getBreedsRequestSpec();
    }


    @Test (dataProvider = "breedData", dataProviderClass = DataProviders.class)
    @Description("This test will get all the breeds from the dog api, using breedData data provider.")
    @DisplayName("Test get all breeds endpoint")
    public void get_all_breeds(String limit, String page, String description){


        Response response = given()
                .when()
                .spec(requestSpec)
                .and()
                .param("limit", limit)
                .param("page", page)
                .get();

        //Test assertions
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(description, response.jsonPath().get("[0]"), allOf(hasKey("name"),hasKey("id"),hasKey("image")));


    }

    @Test
    @Description("Test that search breeds endpoint is working as expected.")
    @DisplayName("Test search breeds endpoint")
    public void search_breeds(){
        Response response = given()
                .when()
                .spec(requestSpec)
                .and()
                .basePath(Constants.BREEDS_PATH+"search")
                .param("q", "Hunting dog")
                .get();

        //Test assertions
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().get("name"), hasItem(containsString("Hunting Dog")));
    }

    @Test
    @Description("Test that get breeds by id endpoint is working as expected.")
    @DisplayName("Test get breeds by id endpoint")
    public void get_breeds_by_id(){
        int breedId = APIData.GetBreedId();
        System.out.println("Breed ID: "+breedId);
        Response response = given()
                .when()
                .spec(requestSpec)
                .and()
                .basePath(Constants.BREEDS_PATH+breedId)
                .get();

        //Test assertions
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().get("id"), equalTo(breedId));
        assertThat(response.jsonPath().get(), allOf(hasKey("name"),hasKey("id"),hasKey("reference_image_id")));

    }

}

package tests.favourites;

import data.DataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.HTTPConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Feature("The delete favourite requests in the dog api.")
public class TestDeleteFavourite {

    RequestSpecification requestSpec;

    @BeforeClass
    public void initialiseObjects(){
        requestSpec = HTTPConfig.getFavouriteRequestSpec();
    }

    @Test(dataProvider = "favouriteIdData", dataProviderClass = DataProviders.class)
    @Story("Delete favourite with id")
    @Description("Testing the delte favourite endPoint with id using parameterized tests")
    @DisplayName("Test delete favourite with ID")
    public void delete_with_id(String favourite_id,String code, String expectedOutput){

        Response response = given()
                .when()
                .spec(requestSpec)
                .and()
                .basePath("favourites/" + favourite_id)
                .delete();

        //Tests assertions
        assertThat(response.getStatusCode(), equalTo(Integer.valueOf(code)));
        assertThat(response.getBody().asString(), containsString(expectedOutput));
    }

}

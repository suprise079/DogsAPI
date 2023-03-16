package tests.categories;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.HTTPConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("The get categories request in the dog api.")
public class TestGetCategories {

    RequestSpecification requestSpec;

    @BeforeClass
    public void initialiseObjects(){
        requestSpec = HTTPConfig.getCategoriesRequestSpec();
    }

    @Test
    @Description("This test will get all the categories from the dog api.")
    public void get_all_categories(){
        Response response = given()
                .when()
                .spec(requestSpec)
                .get();

        //Test assertions
        assertThat(response.getStatusCode(), equalTo(200));
        response.then().body("size()", greaterThan(0));
    }


}

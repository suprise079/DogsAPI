package tests.votes;

import data.DataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.HTTPConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Feature("The create vote request in the dog api.")
public class TestCreateVote {

    RequestSpecification requestSpec;

    @BeforeClass
    public void initialiseObjects(){
        requestSpec = HTTPConfig.getVotesRequestSpec();
    }



    @Test (dataProvider = "voteData", dataProviderClass = DataProviders.class)
    @Description("This test will create a vote for a given image id, sub id and value.")
    @DisplayName("Test create vote endpoint")
    public void vote_image(String image_id, String sub_id, String value,String expectedStatus, String expectedOutput){
        JSONObject requestParams = new JSONObject();
        if (image_id != "false")
            requestParams.put("image_id", image_id);
        if (sub_id != "false")
            requestParams.put("sub_id", sub_id);
        if (value != "false")
            requestParams.put("value", value);

        Response response = given()
                .when()
                .spec(requestSpec)
                .body(requestParams.toJSONString())
                .post();

        //Tests assertions
        assertThat(response.getStatusCode(), equalTo(Integer.valueOf(expectedStatus)));
        assertThat(response.getBody().asString(), containsString(expectedOutput));

        requestParams.clear();

    }
}

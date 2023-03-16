package tests.votes;

import data.DataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.HTTPConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@Feature("The delete votes requests in the dog api.")
public class TestDeleteVote {

    RequestSpecification requestSpec;

    @BeforeClass
    public void initialiseObjects(){
        requestSpec = HTTPConfig.getVotesRequestSpec();
    }



    @Test(dataProvider = "voteIdData", dataProviderClass = DataProviders.class)
    @Description("This test will delete a vote for a given vote id.")
    @DisplayName("Test delete vote endpoint")
    public void delete_vote_by_id(String vote_id,String code, String expectedOutput){
        Response response = given()
                .when()
                .spec(requestSpec)
                .and()
                .basePath("votes/" + vote_id)
                .delete();

        //Tests assertions
        assertThat(response.getStatusCode(), equalTo(Integer.valueOf(code)));
        assertThat(response.getBody().asString(), containsString(expectedOutput));

    }

}

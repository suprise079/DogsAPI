package tests.votes;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Constants;
import utils.HTTPConfig;

import static io.restassured.RestAssured.given;

public class TestGetVotes {

    RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        requestSpec = HTTPConfig.getVotesRequestSpec();
    }

    @DataProvider(name = "subIdData")
    private Object[][] getSubIdData(){
        //provide a second element in each array that will expalin the data
        String[][] data = {{"my-user-1234"}, {"Invalid"}, {""}};
        return data;
    }

    @DataProvider(name = "voteIdData")
    public Object[][] getVoteIdData(){
        //provide a second element in each array that will expalin the data
        String[][] data = {{"145525", "valid vote ID"},
                {"38579387589", "Non-Existent ID"},
                {"kewjrfhjkdsfh", "Invalid ID"}};
        return data;
    }

    @Test(dataProvider = "subIdData")
    public void get_votes_with_sub_id(String subId) {
        given()
                .when()
                .spec(requestSpec)
                .and()
                .param("sub_id", subId)
                .get()
                .then()
                .log().all()
                .statusCode(200);

        //Write a test to get votes with sub_id
        //Put assertions for all expected data using hamcrest matchers
    }

    @Test (dataProvider = "voteIdData")
    public void get_votes_with_vote_id(String voteId, String description) {
        given()
                .when()
                .spec(requestSpec)
                .and()
                .basePath(Constants.VOTES_PATH + voteId)
                .get()
                .then()
                .log().all()
                .statusCode(400);

        //Write a test to get votes with invalid limit
        //Put assertions for all expected data using hamcrest matchers
        //put the description in the assertion message
    }



}

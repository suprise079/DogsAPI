package tests.votes;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.HTTPConfig;

import static io.restassured.RestAssured.given;

public class TestDeleteVote {

    RequestSpecification requestSpec;

    @BeforeClass
    public void initialiseObjects(){
        requestSpec = HTTPConfig.getVotesRequestSpec();
    }

    @DataProvider(name="voteIdData")
    public Object[][] getVoteIdData(){
        //provide a second element in each array that will expalin the data
        String[][] data = {
                {"145526", "Testing with valid existing data"},
                {"145526", "Testing with alredy deleted Id"},
                {"", "Testing with empty vote id"},
                {"id01", "Testing with string instead of integer"}
        };
        return data;
    }

    @Test(dataProvider = "voteIdData")
    public void delete_vote_by_id(String vote_id, String description){
        given()
                .when()
                .spec(requestSpec)
                .and()
                .basePath("votes/" + vote_id)
                .delete()
                .then().log().all()
                .statusCode(200);
    }

}

package tests.votes;

import data.DataProviders;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.HTTPConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class TestDeleteVote {

    RequestSpecification requestSpec;

    @BeforeClass
    public void initialiseObjects(){
        requestSpec = HTTPConfig.getVotesRequestSpec();
    }



    @Test(dataProvider = "voteIdData", dataProviderClass = DataProviders.class)
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

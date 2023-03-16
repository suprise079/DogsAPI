package tests.votes;

import data.APIData;
import data.DataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Constants;
import utils.HTTPConfig;
import utils.Requests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsMapContaining.hasKey;


@Feature("The get votes requests in the dog api.")
public class TestGetVotes {

    RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        requestSpec = HTTPConfig.getVotesRequestSpec();
    }


    @Test(dataProvider = "voteSubIdData", dataProviderClass = DataProviders.class)
    @Description("This test will get all the votes for a given sub id.")
    @DisplayName("Test get votes by sub id endpoint")
    public void get_votes_with_sub_id(String subId) {
        Response response = given()
                .when()
                .spec(requestSpec)
                .and()
                .param("sub_id", subId)
                .get();

        //Test assertions
        assertThat(response.getStatusCode(), equalTo(200));
        response.then().body("size()", greaterThan(0));

    }

    @Test
    @Description("This test will get all the votes for a given vote id.")
    @DisplayName("Test get votes by vote id endpoint")
    public void get_votes_with_vote_id() {
        int ExistingVoteId = APIData.GetVoteId();
        Response response = Requests.getVote(ExistingVoteId);

        //Test assertions
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().get("id"), equalTo(ExistingVoteId));
        assertThat(response.jsonPath().get(), allOf(hasKey("id"), hasKey("image_id"), hasKey("sub_id"), hasKey("value"), hasKey("created_at")));
    }

    @Test
    @Description("This test will get all the votes for a given image id.")
    @DisplayName("Test get votes by image id endpoint")
    public void get_votes_with_non_existing_id() {
        int InvalidVoteId = 38579387;
        Response response = Requests.getVote(InvalidVoteId);

        //Test assertions
        assertThat(response.getStatusCode(), equalTo(404));
        assertThat(response.getBody().asString(), equalTo("NOT_FOUND"));
    }

    @Test
    @Description("This test will get all the votes for a given image id.")
    @DisplayName("Test get votes by image id endpoint")
    public void get_votes_with_invalid_id() {
        int InvalidVoteId = -10;
        Response response = Requests.getVote(InvalidVoteId);

        //Test assertions
        assertThat(response.getStatusCode(), equalTo(404));
        assertThat(response.getBody().asString(), equalTo("INVALID_ID"));
    }





}

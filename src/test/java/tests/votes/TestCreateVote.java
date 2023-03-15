package tests.votes;

import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.HTTPConfig;

import static io.restassured.RestAssured.given;

public class TestCreateVote {

    RequestSpecification requestSpec;

    @BeforeClass
    public void initialiseObjects(){
        requestSpec = HTTPConfig.getVotesRequestSpec();
    }

    @DataProvider(name="voteData")
    public Object[][] getUpVoteData(){
        //provide a second element in each array that will expalin the data
        String[][] data = {
                {"asf100","my-user-1234", "2", "Testing with valid data"},
                {"asf100","my-user-1234", "2", "Testing with duplicate data"},
                {"","my-user-1234", "2", "Testing with empty image id"},
                {"asf200","my-user-1234", "", "Testing with empty vote value"},
                {"asf200","", "5", "Testing with empty sub_id"}
        };
        return data;
    }

    @Test (dataProvider = "voteData")
    public void down_vote_image(String image_id, String sub_id, String value, String description){
        JSONObject requestParams = new JSONObject();
        requestParams.put("image_id", image_id);
        requestParams.put("sub_id", sub_id);
        requestParams.put("value", value);

        given()
                .when()
                .spec(requestSpec)
                .body(requestParams.toJSONString())
                .post()
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test (dataProvider = "voteData")
    public void up_vote_image(String image_id, String sub_id, String value, String description){
        JSONObject requestParams = new JSONObject();
        requestParams.put("image_id", image_id);
        requestParams.put("sub_id", sub_id);
        requestParams.put("value", value);
        System.out.println("Description: " + description);
        given()
                .when()
                .spec(requestSpec)
                .body(requestParams.toJSONString())
                .post()
                .then()
                .log().all()
                .statusCode(201);
    }
}

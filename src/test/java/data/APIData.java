package data;

import io.restassured.response.Response;
import utils.HTTPConfig;

import static io.restassured.RestAssured.given;

public class APIData {

    public static int GetBreedId(){
        Response response = given()
                .when()
                .spec(HTTPConfig.getBreedsRequestSpec())
                .get();

        return response.jsonPath().get("[0][\"id\"]");
    }

    public static int GetVoteId(){
        Response response = given()
                .when()
                .spec(HTTPConfig.getVotesRequestSpec())
                .get();

        return response.jsonPath().get("[0][\"id\"]");
    }


}

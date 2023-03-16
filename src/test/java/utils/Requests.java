package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Requests {

        public static Response sendPost(RequestSpecification requestSpec, String path, String body) {

            return null;
        }

        public static Response getVote(int voteId){
            return given()
                    .when()
                    .spec(HTTPConfig.getVotesRequestSpec())
                    .and()
                    .basePath(Constants.VOTES_PATH + voteId)
                    .get();
        }
}

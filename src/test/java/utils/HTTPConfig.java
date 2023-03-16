package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class HTTPConfig {

    private static RequestSpecification getRequestSpec(String path){
         return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .addHeader("x-api-key", Constants.APIKEY)
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .setContentType("application/json")
                .setAccept("application/json")
                .setBaseUri(Constants.URL)
                .setBasePath(path)
                .build();
    }

    public static RequestSpecification getFavouriteRequestSpec(){
        return getRequestSpec(Constants.FAVOURITE_PATH);
    }


    public static RequestSpecification getVotesRequestSpec() {
        return getRequestSpec(Constants.VOTES_PATH);
    }


    public static RequestSpecification getCategoriesRequestSpec() {
        return getRequestSpec(Constants.CATEGORIES_PATH);
    }

    public static RequestSpecification getBreedsRequestSpec() {
        return getRequestSpec(Constants.BREEDS_PATH);
    }
}

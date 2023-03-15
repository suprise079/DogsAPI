package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Helpers {

    private static RequestSpecification getRequestSpec(String path){
         return new RequestSpecBuilder()
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




}

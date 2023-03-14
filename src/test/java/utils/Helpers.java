package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Helpers {

    public static RequestSpecification getRequestSpec(){
         return new RequestSpecBuilder()
                .addHeader("x-api-key", Constants.APIKEY)
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .setContentType("application/json")
                .setAccept("application/json")
                .setBaseUri(Constants.URL)
                .setBasePath("favourites/")
                .build();
    }
}

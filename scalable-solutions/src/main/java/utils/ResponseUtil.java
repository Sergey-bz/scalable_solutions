package utils;

import io.restassured.response.Response;

public class ResponseUtil {

    public static <T> T getResponseBodyAsClass(Response response, Class<T> var) {
        return response.then().extract().body().as(var);
    }
}
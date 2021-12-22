package http;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestExecutor {

    RequestSpecification requestSpecification;
    Filter filter;

    private final static String BASE_URI = "http://94.130.158.237:43587";

    public RequestExecutor() {
        this.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
        filter = new LogFilter();
        requestSpecification.filter(filter);
    }

    public Response sendGetRequest(String path) {
        return given()
                .spec(requestSpecification)
                .get(path);
    }

    public Response sendGetRequest(String path, Map<String, Object> queryParams) {
        return given()
                .spec(requestSpecification)
                .queryParams(queryParams).log().all()
                .get(path);
    }

    public Response sendPostRequest(String path, Object body) {
        return given()
                .spec(requestSpecification)
                .body(body).log().all()
                .post(path);
    }

    public Response sendDeleteRequest(String path) {
        return given()
                .spec(requestSpecification)
                .delete(path);
    }

    public Response sendDeleteRequest(String path, Map<String, Object> queryParams) {
        return given()
                .spec(requestSpecification)
                .queryParams(queryParams)
                .delete(path);
    }
}

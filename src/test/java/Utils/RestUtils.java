package Utils;

import io.qameta.allure.Step;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class RestUtils {

    @Step("Performing POST request to: {path}")
    public static Response performPost(String path, Object requestPayload) {
        return RestAssured.given().log().all()
                .basePath(path)
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .when()
                .post()
                .then().log().all().extract().response();
    }
    @Step("Performing PUT request to: {path}")
    public static Response performPut(String path, Object requestPayload, Map<String, Object> pathParams) {
        return RestAssured.given().log().all()
                .basePath(path)
                .contentType(ContentType.JSON)
                .pathParams(pathParams)
                .body(requestPayload)
                .when()
                .put()
                .then().log().all().extract().response();
    }


    @Step("Performing GET request to: {path}")
    public static Response performGet(String path) {
        return RestAssured.given().log().all()
                .basePath(path)
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then().log().all().extract().response();
    }

    @Step("Performing GET with path params to: {path}")
    public static Response performGet(String path, Map<String, Object> pathParams) {
        return RestAssured.given().log().all()
                .basePath(path)
                .contentType(ContentType.JSON)
                .pathParams(pathParams)
                .when()
                .get()
                .then().log().all().extract().response();
    }

    @Step("Performing DELETE request to: {path}")
    public static Response performDelete(String path, Map<String, Object> pathParams) {
        return RestAssured.given().log().all()
                .basePath(path)
                .contentType(ContentType.JSON)
                .pathParams(pathParams)
                .when()
                .delete()
                .then().log().all().extract().response();
    }
}


package com.bilyoner.api;

import com.bilyoner.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class BaseApiClient {

    static {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    protected Response sendPost(String endpoint, Map<String, ?> body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    protected Response sendGet(String endpoint) {
        return given()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    protected Response sendPut(String endpoint, Map<String, ?> body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    protected Response sendDelete(String endpoint) {
        return given()
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
}

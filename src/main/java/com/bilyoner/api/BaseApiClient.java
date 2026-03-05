package com.bilyoner.api;

import com.bilyoner.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class BaseApiClient {

    private static final PrintStream apiLogStream;

    static {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
        try {
            new File("logs").mkdirs();
            apiLogStream = new PrintStream(new FileOutputStream("logs/api-responses.log", true));
        } catch (Exception e) {
            throw new RuntimeException("API log dosyası oluşturulamadı", e);
        }
    }

    private RequestSpecification loggedRequest() {
        return given()
                .filter(new RequestLoggingFilter(apiLogStream))
                .filter(new ResponseLoggingFilter(apiLogStream));
    }

    protected Response sendPost(String endpoint, Map<String, ?> body) {
        return loggedRequest()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    protected Response sendGet(String endpoint) {
        return loggedRequest()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    protected Response sendPut(String endpoint, Map<String, ?> body) {
        return loggedRequest()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    protected Response sendDelete(String endpoint) {
        return loggedRequest()
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
}

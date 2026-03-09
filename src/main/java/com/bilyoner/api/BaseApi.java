package com.bilyoner.api;

import com.bilyoner.utils.ConfigReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
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
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseApi {

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
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .config(RestAssuredConfig.config().objectMapperConfig(
                        objectMapperConfig().jackson2ObjectMapperFactory(
                                ((type, s) -> new com.fasterxml.jackson.databind.ObjectMapper()
                                        .enable(SerializationFeature.INDENT_OUTPUT))
                        )
                ));
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
    public void checkStatusCode(Response response, int statusCode){
                 assertThat(response.getStatusCode())
                .as("Status Code")
                .isEqualTo(statusCode);
    }
}

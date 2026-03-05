package com.bilyoner.api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginApiClient extends BaseApiClient {

    private static final String BASE_URL = "https://dummyjson.com";
    private static final String LOGIN_PATH = "/user/login";

    public Response login(String username, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);
        body.put("clientId", "mobile.bilyoner.com");
        return sendPost(BASE_URL + LOGIN_PATH, body);
    }
}

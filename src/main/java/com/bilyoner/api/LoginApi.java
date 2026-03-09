package com.bilyoner.api;

import com.bilyoner.utils.DataProvider;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginApi extends BaseApi {

    private static final String BASE_URL = "https://dummyjson.com";
    private static final String LOGIN_PATH = "/user/login";

    public Response login(String jsonKey) {
        Map<String, String> body = new HashMap<>(DataProvider.getData("login",jsonKey));

        return sendPost(BASE_URL + LOGIN_PATH, body);
    }
}

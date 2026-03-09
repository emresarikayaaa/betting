package com.bilyoner.api;

import com.bilyoner.utils.DataProvider;
import com.bilyoner.utils.DataStore;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class RegisterApi extends BaseApi{
    private static final String BASE_URL = "https://dummyjson.com";
    private static final String PATH = "/users/add";

    public Response sendRegisterRequest(String jsonKey){
        Map<String, String> userInfo = new HashMap<>(DataProvider.getData("register",jsonKey));
        return sendPost(BASE_URL + PATH, userInfo);
    }

}

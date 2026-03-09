package com.bilyoner.api;

import io.restassured.response.Response;

public class ProductApi extends BaseApi{
    private static final String BASE_URL = "https://dummyjson.com";
    private static final String PATH = "/products";

    public Response getAllProducts(){

       return sendGet(BASE_URL + PATH);
    }
}

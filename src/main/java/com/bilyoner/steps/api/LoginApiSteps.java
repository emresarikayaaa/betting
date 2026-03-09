package com.bilyoner.steps.api;

import com.bilyoner.api.LoginApi;
import com.bilyoner.utils.DataProvider;
import com.bilyoner.utils.DataStore;
import com.thoughtworks.gauge.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class LoginApiSteps {
    private final LoginApi loginApi = new LoginApi();
    private Response lastResponse;

    @Step("POST login <dataKey> verisi ile")
    public void loginAndGetToken(String jsonKey) {

      //  Map<String, String> data = DataProvider.getData("login", jsonKey);
        lastResponse = loginApi.login(jsonKey);
        String token = lastResponse.jsonPath().getString("accessToken");
        if (token != null) {
            DataStore.set("token", token);
        }
    }

    @Step("API yanıtının <statusCode> olduğunu doğrula")
    public void verifyStatusCode(int statusCode) {
        assertThat(lastResponse.getStatusCode())
                .as("API status code")
                .isEqualTo(statusCode);
    }
}

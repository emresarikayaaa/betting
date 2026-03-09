package com.bilyoner.steps.api;

import com.bilyoner.api.RegisterApi;
import com.bilyoner.utils.DataStore;
import com.thoughtworks.gauge.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterApiSteps {

    RegisterApi registerApi = new RegisterApi();

    @Step("<jsonKey> ile kayıt olma")
    public void successfullRegister(String jsonKey) {
        DataStore.set("lastResponse", registerApi.sendRegisterRequest(jsonKey));
    }

    @Step("Status code <statusCode> olmalı")
    public void checkStatusCode(int statusCode) {
        Response response = DataStore.get("lastResponse");
        assertThat(response.getStatusCode())
                .as("Status code")
                .isEqualTo(statusCode);
    }

    @Step("Response body <parameter> alanı boş olmamalı")
    public void checkFieldNotEmpty(String parameter) {
        Response response = DataStore.get("lastResponse");
        assertThat(response.jsonPath().getString(parameter))
                .as(parameter + "alanı")
                .isNotEmpty();
    }

    @Step("Yaş kontrolü <age> 'dan büyük olmalı")
    public void checkAge(int age) {
        Response response = DataStore.get("lastResponse");
        assertThat(response.jsonPath().getInt("age"))
                .as("Yaş")
                .isGreaterThanOrEqualTo(age);
    }
}

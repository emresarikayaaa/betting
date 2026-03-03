package com.bilyoner.steps;

import com.bilyoner.driver.DriverManager;
import com.thoughtworks.gauge.Step;

public class CommonSteps {

    @Step("Ana sayfaya git")
    public void getHomePage() {
        System.out.println("Bilyoner açıldı: " + DriverManager.getDriver().getTitle());
    }

    @Step("Giriş yap butonunun görünür olduğunu doğrula")
    public void checkLoginButton() {
    }
}

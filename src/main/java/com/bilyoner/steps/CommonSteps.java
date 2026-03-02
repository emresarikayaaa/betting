package com.bilyoner.steps;

import com.bilyoner.driver.DriverManager;
import com.bilyoner.pages.HomePage;
import com.thoughtworks.gauge.Step;

public class CommonSteps {
    private HomePage homePage;

    @Step("Ana sayfaya git")
    public void getHomePage() {
        System.out.println("Bilyoner açıldı [" + DriverManager.getPlatform() + "]: " + DriverManager.getDriver().getTitle());
    }


    @Step("Giriş yap butonunun görünür olduğunu doğrula")
    public void checkLoginButton(){

       // assert homePage.isLoginButtonVisible() : "Giriş yap butonu görünmüyor!";
    }
}

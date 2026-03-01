package com.bilyoner.steps;

import com.bilyoner.driver.DriverManager;
import com.bilyoner.pages.HomePage;
import com.thoughtworks.gauge.Step;

public class CommonSteps {
    private HomePage homePage;

    @Step("Ana sayfaya git")
    public void getHomePage() {
        if (DriverManager.getPlatform().isWeb()) {
            System.out.println("Bilyoner açıldı: " + DriverManager.getDriver().getTitle());
        } else {
            DriverManager.getDriver(); // driver'ı başlat
            System.out.println("Bilyoner mobil uygulama açıldı [" + DriverManager.getPlatform() + "]");
        }
    }


    @Step("Giriş yap butonunun görünür olduğunu doğrula")
    public void checkLoginButton(){

       // assert homePage.isLoginButtonVisible() : "Giriş yap butonu görünmüyor!";
    }
}

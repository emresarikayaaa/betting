package com.bilyoner.steps;


import com.bilyoner.pages.HomePage;
import com.thoughtworks.gauge.Step;

public class HomeSteps {
    HomePage homePage = new HomePage();

    @Step("Giriş yap butonuna tıkla")
    public void navigateToLoginPage() {
        homePage.cliclLoginButton();

    }




}
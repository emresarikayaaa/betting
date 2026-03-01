package com.bilyoner.pages;

import com.bilyoner.constants.HomePageConstants;



public class HomePage extends BasePage {
    HomePageConstants constants = new HomePageConstants();

    public void cliclLoginButton(){
        driver.findElements(constants.loginButton).get(1).click();
    }
}
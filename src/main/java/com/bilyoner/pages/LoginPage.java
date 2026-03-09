package com.bilyoner.pages;

import com.bilyoner.constants.LocatorFactory;


public class LoginPage extends BasePage {

    public void setUsername(String username) {
        sendKeys(LocatorFactory.loginPage().getUsernameField(), username);
    }
    public void setPassword(String password) throws InterruptedException {
        sendKeys(LocatorFactory.loginPage().getPasswordField(), password);

    }
    public void clickLoginButton() throws InterruptedException {
        click(LocatorFactory.loginPage().getUsernameField());
        click(LocatorFactory.loginPage().getLoginButton());
    }

}

package com.bilyoner.pages;

import com.bilyoner.constants.HomePageConstants;

public class HomePage extends BasePage {

    public void clickLoginButton() {
        driver.findElements(HomePageConstants.LOGIN_BUTTON).get(1).click();
    }
}

package com.bilyoner.pages;

import com.bilyoner.constants.HomePageConstants;
import com.bilyoner.driver.DriverManager;

public class HomePage extends BasePage {

    public void clickLoginButton() {
        if (DriverManager.getPlatform().isWeb()) {
            driver.findElements(HomePageConstants.LOGIN_BUTTON).get(1).click();
        } else {
            click(HomePageConstants.LOGIN_BUTTON);
        }
    }
}

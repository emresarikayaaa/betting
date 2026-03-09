package com.bilyoner.constants.ios;

import com.bilyoner.constants.LoginLocators;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class IosLoginLocators implements LoginLocators {
    @Override
    public By getUsernameField() {
        return AppiumBy.className("XCUIElementTypeTextField");
    }
    @Override
    public By getPasswordField() {
        return AppiumBy.className("XCUIElementTypeSecureTextField");
    }
    @Override
    public By getLoginButton() {
        return AppiumBy.name("Login");
    }


}

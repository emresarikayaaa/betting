package com.bilyoner.constants.android;

import com.bilyoner.constants.LoginLocators;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class AndroidLoginLocators implements LoginLocators {
    @Override
    public By getUsernameField() {
        return AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET");
    }

    @Override
    public By getPasswordField() {
        return AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET");
    }

    @Override
    public By getLoginButton() {
        return AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginBtn");
    }

}

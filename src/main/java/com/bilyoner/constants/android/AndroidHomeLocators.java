package com.bilyoner.constants.android;

import com.bilyoner.constants.HomeLocators;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.util.List;

public class AndroidHomeLocators implements HomeLocators {

    @Override
    public By getMenuButton() {
        return AppiumBy.accessibilityId("View menu");
    }

    @Override
    public By getMenuItems() {
        return null;
    }

    @Override
    public By getFilterButton() {
        return null;
    }


}

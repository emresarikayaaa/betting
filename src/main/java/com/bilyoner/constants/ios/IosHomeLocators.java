package com.bilyoner.constants.ios;

import com.bilyoner.constants.HomeLocators;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.util.List;

public class IosHomeLocators implements HomeLocators {


    @Override
    public By getMenuButton() {
        return AppiumBy.accessibilityId("More-tab-item");
    }
    @Override
    public By getMenuItems() {return AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeOther'");}
    @Override
    public By getFilterButton() {return AppiumBy.name("Button");}

}

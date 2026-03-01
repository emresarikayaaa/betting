package com.bilyoner.constants;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class HomePageConstants {

    public static final By BULLETIN_MENU_BUTTON = LocatorResolver.resolve(
            By.xpath("//a[contains(text(),'Spor')]"),
            AppiumBy.id("com.bilyoner.android:id/btnSports"),
            AppiumBy.accessibilityId("Spor")
    );

    public static final By LOGIN_BUTTON = LocatorResolver.resolve(
            By.cssSelector(".btn.btn-login"),
            AppiumBy.id("com.bilyoner.android:id/btnLogin"),
            AppiumBy.accessibilityId("Giriş Yap")
    );

    public static final By SEARCH_BUTTON = LocatorResolver.resolve(
            By.cssSelector(".search-icon"),
            AppiumBy.id("com.bilyoner.android:id/btnSearch"),
            AppiumBy.accessibilityId("Ara")
    );
}

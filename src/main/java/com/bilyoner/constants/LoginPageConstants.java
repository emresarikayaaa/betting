package com.bilyoner.constants;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class LoginPageConstants {

    public static final By LOGIN_BUTTON = LocatorResolver.resolve(
            By.cssSelector("a[title='Giriş Yap']"),
            AppiumBy.id("com.bilyoner.android:id/btnLogin"),
            AppiumBy.accessibilityId("Giriş Yap")
    );

    public static final By TCKN_NO = LocatorResolver.resolve(
            By.id("username"),
            AppiumBy.id("com.bilyoner.android:id/etTckn"),
            AppiumBy.accessibilityId("TCKN")
    );

    public static final By PASSWORD = LocatorResolver.resolve(
            By.id("password"),
            AppiumBy.id("com.bilyoner.android:id/etPassword"),
            AppiumBy.accessibilityId("Şifre")
    );

    public static final By SUBMIT_LOGIN_BUTTON = LocatorResolver.resolve(
            By.cssSelector("button[title='GİRİŞ YAP']"),
            AppiumBy.id("com.bilyoner.android:id/btnSubmitLogin"),
            AppiumBy.accessibilityId("GİRİŞ YAP")
    );

    public static final By ERROR_MESSAGE = LocatorResolver.resolve(
            By.cssSelector("p.text-error"),
            AppiumBy.id("com.bilyoner.android:id/tvErrorMessage"),
            AppiumBy.accessibilityId("Hata Mesajı")
    );
}

package com.bilyoner.driver;

import com.bilyoner.utils.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String platform = ConfigReader.get("platform");
            switch (platform.toLowerCase()) {
                case "android" -> driver = createAndroidDriver();
                case "ios" -> driver = createIOSDriver();
                default -> throw new RuntimeException("Geçersiz platform: " + platform + ". 'Android' veya 'iOS' olmalı.");
            }
        }
        return driver;
    }

    private static WebDriver createAndroidDriver() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(ConfigReader.get("deviceName"));
        options.setApp(new File(ConfigReader.get("androidAppPath")).getAbsolutePath());
        options.setAutoGrantPermissions(true);
        options.setCapability("appium:appWaitActivity", "*");
        options.setCapability("appium:appWaitDuration", 60000);

        return new AndroidDriver(getAppiumUrl(), options);
    }

    private static WebDriver createIOSDriver() {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(ConfigReader.get("deviceName"));
        options.setApp(new File(ConfigReader.get("iosAppPath")).getAbsolutePath());
        options.setAutoAcceptAlerts(true);

        return new IOSDriver(getAppiumUrl(), options);
    }

    private static URL getAppiumUrl() {
        try {
            return new URL(ConfigReader.get("appiumServerUrl"));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Appium server URL hatalı", e);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

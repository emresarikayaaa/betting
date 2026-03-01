package com.bilyoner.driver;

import com.bilyoner.utils.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    private static WebDriver driver;
    private static final Platform platform = Platform.fromConfig(ConfigReader.get("platform"));

    public static Platform getPlatform() {
        return platform;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = switch (platform) {
                case ANDROID -> createAndroidDriver();
                case IOS -> createIOSDriver();
                default -> createWebDriver();
            };
        }
        return driver;
    }

    private static WebDriver createWebDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new ChromeDriver(options);
        driver.get(ConfigReader.get("baseUrl"));
        return driver;
    }

    private static WebDriver createAndroidDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(ConfigReader.get("deviceName"))
                .setPlatformVersion(ConfigReader.get("platformVersion"))
                .setAppPackage(ConfigReader.get("appPackage"))
                .setAppActivity(ConfigReader.get("appActivity"))
                .setAutomationName("UiAutomator2");

        try {
            return new AndroidDriver(new URL(ConfigReader.get("appiumServerUrl")), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Geçersiz Appium server URL: " + ConfigReader.get("appiumServerUrl"), e);
        }
    }

    private static WebDriver createIOSDriver() {
        XCUITestOptions options = new XCUITestOptions()
                .setDeviceName(ConfigReader.get("iosDeviceName"))
                .setPlatformVersion(ConfigReader.get("iosPlatformVersion"))
                .setBundleId(ConfigReader.get("iosBundleId"))
                .setAutomationName("XCUITest");

        try {
            return new IOSDriver(new URL(ConfigReader.get("appiumServerUrl")), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Geçersiz Appium server URL: " + ConfigReader.get("appiumServerUrl"), e);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

package com.bilyoner.driver;

import com.bilyoner.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    private static WebDriver driver;
    // WebDriver: Selenium'un tarayıcı arayüzü
    // AndroidDriver yerine WebDriver kullanıyoruz artık

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
            // Singleton Pattern: tek bir driver instance'ı
        }
        return driver;
    }

    private static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        // Chrome driver'ı otomatik indir ve ayarla
        // Manuel driver indirmeye gerek yok

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new ChromeDriver(options);
        driver.get(ConfigReader.get("baseUrl"));
        // config.properties'teki baseUrl'e git

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
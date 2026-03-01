package com.bilyoner.utils;

import com.bilyoner.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtils {

    private static WebDriver driver = DriverManager.getDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(
            Long.parseLong(ConfigReader.get("explicitWait"))
    ));

    public static WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        // Element görünür olana kadar bekle
    }

    public static WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
        // Element tıklanabilir olana kadar bekle
    }

    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            // Sabit bekleme - sadece zorunlu durumlarda kullan
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static boolean waitForInvisibility(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        // Element kaybolana kadar bekle
        // Örnek: loading spinner kapanana kadar
    }
}
package com.bilyoner.pages;

import com.bilyoner.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    // WebDriver: Selenium'un tarayıcı arayüzü

    protected WebDriverWait wait;
    // WebDriverWait: element beklemek için

    public BasePage() {
        this.driver = DriverManager.getDriver();
        // DriverManager'dan mevcut driver'ı al

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // 10 saniye bekle
    }

    protected WebElement waitAndFind(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        // Element görünür olana kadar bekle
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        // Tıklanabilir olana kadar bekle, sonra tıkla
    }

    protected void sendKeys(By locator, String text) {
        waitAndFind(locator).clear();
        waitAndFind(locator).sendKeys(text);
        // Temizle ve yaz
    }

    protected String getText(By locator) {
        return waitAndFind(locator).getText();
        // Elementin yazısını döndür
    }

    protected boolean isElementVisible(By locator) {
        try {
            waitAndFind(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
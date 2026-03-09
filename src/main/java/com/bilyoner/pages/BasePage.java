package com.bilyoner.pages;

import com.bilyoner.driver.DriverManager;
import com.bilyoner.utils.ConfigReader;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class BasePage {

    private static final long WAIT_SECONDS = Long.parseLong(ConfigReader.get("explicitWait"));

    private WebDriverWait getWait() {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_SECONDS));
    }

    protected WebElement waitAndFind(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void click(By locator) {
        getWait().until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void clickByText(By locator, String menuName){
        List<WebElement> items = getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

        items.stream()
                .filter(name -> {
                    String label = name.getAttribute("name");
                    return label != null && label.contains(menuName);
                })
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("Menu itemi bulunamadı:" + menuName))
                .click();
    }

    protected void sendKeys(By locator, String text) {
        WebElement element = waitAndFind(locator);
        element.clear();
        element.sendKeys(text);
    }
    protected void scrollToElement(By locator) {
        WebElement element = DriverManager.getDriver().findElement(locator);
        Map<String, Object> params = Map.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", "down"
        );
        ((IOSDriver) DriverManager.getDriver()).executeScript("mobile: scrollToElement", params);
    }

    protected String getText(By locator) {
        return waitAndFind(locator).getText();
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

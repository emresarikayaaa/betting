package com.bilyoner.pages;

import com.bilyoner.constants.LocatorFactory;
import com.bilyoner.driver.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import java.util.List;


public class HomePage extends BasePage {



    public void openHamburgerMenu(){
        click(LocatorFactory.homePage().getMenuButton());
    }
    public void clickMenuItem(String itemName) {
        clickByText(LocatorFactory.homePage().getMenuItems(), itemName);
    }
    public void clickFilterButton(String filterName){
        click(AppiumBy.iOSNsPredicateString("name == '" + filterName + "'"));
    }
    public void getProduct(String color){
        DriverManager.getDriver().findElements(
                AppiumBy.iOSNsPredicateString("name == 'Product Name' AND label CONTAINS '" + color + "'"))
                .stream()
                .peek(e -> System.out.println(e.getText()))
                .filter(product -> product.getText().contains(color))
                .findFirst()
                .orElseThrow()
                .click();

    }
}

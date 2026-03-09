package com.bilyoner.steps.ui;

import com.bilyoner.pages.HomePage;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.AppiumBy;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeUiSteps {
    private HomePage homePage;

    private HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    @Step("Menüyü aç")
    public void openHamburgerMenu() {
        getHomePage().openHamburgerMenu();
    }

    @Step("Menüden <menuItem> sayfasına git")
    public void goToLoginFromMenu(String menuItem) {
        getHomePage().clickMenuItem(menuItem);
    }

    @Step("Listelemeden ürün seçilir")
    public void getProductDetail() {
        getHomePage().click(AppiumBy.name("Button"));
        getHomePage().clickFilterButton("Price - Ascending");
        getHomePage().getProduct("Red");

    }
}

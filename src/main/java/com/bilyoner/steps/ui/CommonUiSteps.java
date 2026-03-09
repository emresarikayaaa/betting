package com.bilyoner.steps.ui;

import com.bilyoner.driver.DriverManager;
import com.thoughtworks.gauge.Step;

public class CommonUiSteps {

    @Step("Uygulama açılır")
    public void launchApp() {
        DriverManager.getDriver();
    }
}

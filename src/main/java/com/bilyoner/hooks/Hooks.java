package com.bilyoner.hooks;

import com.bilyoner.driver.DriverManager;
import com.bilyoner.pages.HomePage;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;

public class Hooks {
    private HomePage homePage;

    @BeforeScenario
    public void setUp() {
        homePage = new HomePage();

    }

    @AfterScenario
    public void tearDown() {
        DriverManager.quitDriver();

    }
}

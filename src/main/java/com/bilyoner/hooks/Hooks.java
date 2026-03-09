package com.bilyoner.hooks;

import com.bilyoner.driver.DriverManager;
import com.bilyoner.utils.DataStore;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;

public class Hooks {

    @BeforeScenario
    public void setUp() {
    }

    @AfterScenario
    public void tearDown() {
        DriverManager.quitDriver();
        DataStore.clear();
    }
}

package com.bilyoner.constants;

import com.bilyoner.driver.DriverManager;
import org.openqa.selenium.By;

public class LocatorResolver {

    public static By resolve(By web, By android, By ios) {
        return switch (DriverManager.getPlatform()) {
            case ANDROID -> android;
            case IOS -> ios;
            default -> web;
        };
    }
}

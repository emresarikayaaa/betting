package com.bilyoner.constants;

import org.openqa.selenium.By;

public class LocatorResolver {

    public static By resolve(By web, By android, By ios) {
        // Mobile browser'lar aynı DOM'u kullandığı için her zaman web locator döner
        return web;
    }
}

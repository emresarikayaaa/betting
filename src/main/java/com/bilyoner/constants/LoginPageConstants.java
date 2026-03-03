package com.bilyoner.constants;

import org.openqa.selenium.By;

public class LoginPageConstants {

    public static final By LOGIN_BUTTON = By.cssSelector("a[title='Giriş Yap']");

    public static final By TCKN_NO = By.id("username");

    public static final By PASSWORD = By.id("password");

    public static final By SUBMIT_LOGIN_BUTTON = By.cssSelector("button[title='GİRİŞ YAP']");

    public static final By ERROR_MESSAGE = By.cssSelector("p.text-error");
}

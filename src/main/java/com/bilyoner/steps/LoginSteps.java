package com.bilyoner.steps;

import com.bilyoner.api.LoginApiClient;
import com.bilyoner.driver.DriverManager;
import com.bilyoner.pages.LoginPage;
import com.thoughtworks.gauge.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class LoginSteps {
    LoginPage loginPage = new LoginPage();
    LoginApiClient loginApiClient = new LoginApiClient();

    private String currentTckn;
    private String currentPassword;

    @Step("Kullanıcı adını gir <tckn>")
    public void setUsername(String tckn) {
        this.currentTckn = tckn;
        loginPage.setTCKN(tckn);
    }

    @Step("Şifreyi gir <password>")
    public void setPassword(String password) {
        this.currentPassword = password;
        loginPage.setPassword(password);
    }

    @Step("Giriş yap butonuna tıkla login sayfasında")
    public void submitLoginForm() {
        WebDriver driver = DriverManager.getDriver();
        loginApiClient.interceptLoginApi(driver);
        loginPage.submitLoginForm();
    }

    @Step("Girişin başarısız olduğunu doğrula")
    public void validateLoggedIn() {
        loginPage.verifyErrorMessage();
    }

    @Step("Hata mesajının görüntülendiğini doğrula")
    public void checkFailMessage() {

    }

    @Step("Servis tarafında girişin başarısız olduğunu doğrula")
    public void verifyLoginFailed() {
        if (DriverManager.getPlatform().isMobile()) {
            boolean failed = loginApiClient.verifyLoginFailedViaApi(currentTckn, currentPassword);
            assert failed : "Login API response beklendiği gibi değil (REST Assured ile doğrulandı).";
        } else {
            WebDriver driver = DriverManager.getDriver();
            int statusCode = loginApiClient.getLoginApiStatusCode(driver);
            int errorCode = loginApiClient.getLoginApiErrorCode(driver);
            assert statusCode == 400 && errorCode == 401 :
                "Login API response beklendiği gibi değil. Status: " + statusCode + ", Error code: " + errorCode;
        }
    }
}

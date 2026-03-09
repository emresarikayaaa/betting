package com.bilyoner.steps.ui;

import com.bilyoner.pages.LoginPage;
import com.bilyoner.utils.DataProvider;
import com.thoughtworks.gauge.Step;

import java.util.Map;

public class LoginUiSteps {
    private LoginPage loginPage;

    private LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    @Step("Login formunu doldur <dataKey> verisi ile")
    public void fillLoginForm(String dataKey) throws InterruptedException {
        Map<String, String> data = DataProvider.getData("login", dataKey);
        getLoginPage().setUsername(data.get("username"));
        getLoginPage().setPassword(data.get("password"));
    }

    @Step("Login butonuna tıkla")
    public void clickLoginButton() throws InterruptedException {
        getLoginPage().clickLoginButton();
    }


}

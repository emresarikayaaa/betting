package com.bilyoner.pages;

import com.bilyoner.constants.LoginPageConstants;

public class LoginPage extends BasePage {

    public void setTCKN(String tckn) {
        sendKeys(LoginPageConstants.TCKN_NO, tckn);
    }

    public void setPassword(String password) {
        sendKeys(LoginPageConstants.PASSWORD, password);
    }

    public void submitLoginForm() {
        click(LoginPageConstants.SUBMIT_LOGIN_BUTTON);
    }

    public boolean verifyErrorMessage() {
        String errorText = getText(LoginPageConstants.ERROR_MESSAGE);
        return errorText.equals("Üye / TC Kimlik Numaranız veya Şifreniz hatalıdır! Lütfen tekrar deneyiniz.");
    }
}

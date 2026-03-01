package com.bilyoner.pages;

import com.bilyoner.constants.LoginPageConstants;
import org.openqa.selenium.By;

public class LoginPage extends BasePage{

    LoginPageConstants constants = new LoginPageConstants();

    public void setTCKN(String tckn){
        sendKeys(constants.TCKN_NO,tckn);
    }

    public void setPassword(String password){
        sendKeys(constants.PASSWORD,password);
    }

    public void submitLoginForm(){
        click(constants.SUBMIT_LOGIN_BUTTON);
    }

    public boolean verifyErrorMessage(){
        return driver.findElement(By.className("text-error")).getText()
                .equals("Üye / TC Kimlik Numaranız veya Şifreniz hatalıdır! Lütfen tekrar deneyiniz.");
    }
}

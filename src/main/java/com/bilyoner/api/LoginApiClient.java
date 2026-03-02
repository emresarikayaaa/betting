package com.bilyoner.api;

import io.restassured.response.Response;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class LoginApiClient extends BaseApiClient {

    private static final String LOGIN_ENDPOINT = "/api/v6/oauth-manager/users/login";

    public Response login(String tckn, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("username", tckn);
        body.put("password", password);
        body.put("clientId", "mobile.bilyoner.com");
        return sendPost(LOGIN_ENDPOINT, body);
    }

    public boolean verifyLoginFailedViaApi(String username, String password) {
        Response response = login(username, password);
        return response.getStatusCode() == 400
                && response.jsonPath().getInt("code") == 401;
    }

    // --- Tarayıcı network intercept metodları (tüm platformlar) ---

    public void interceptLoginApi(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript(
            "window.__loginApiResponse = null;" +
            "var origOpen = XMLHttpRequest.prototype.open;" +
            "var origSend = XMLHttpRequest.prototype.send;" +
            "XMLHttpRequest.prototype.open = function(method, url) {" +
            "  this._url = url;" +
            "  return origOpen.apply(this, arguments);" +
            "};" +
            "XMLHttpRequest.prototype.send = function() {" +
            "  if (this._url && this._url.indexOf('/login') !== -1) {" +
            "    var xhr = this;" +
            "    xhr.addEventListener('load', function() {" +
            "      try {" +
            "        window.__loginApiResponse = {" +
            "          status: xhr.status," +
            "          body: JSON.parse(xhr.responseText)" +
            "        };" +
            "      } catch(e) {" +
            "        window.__loginApiResponse = { status: xhr.status, body: null };" +
            "      }" +
            "    });" +
            "  }" +
            "  return origSend.apply(this, arguments);" +
            "};" +
            "var origFetch = window.fetch;" +
            "window.fetch = function(input, init) {" +
            "  var url = typeof input === 'string' ? input : input.url;" +
            "  return origFetch.apply(this, arguments).then(function(response) {" +
            "    if (url.indexOf('/login') !== -1) {" +
            "      response.clone().text().then(function(text) {" +
            "        try {" +
            "          window.__loginApiResponse = {" +
            "            status: response.status," +
            "            body: JSON.parse(text)" +
            "          };" +
            "        } catch(e) {" +
            "          window.__loginApiResponse = { status: response.status, body: null };" +
            "        }" +
            "      });" +
            "    }" +
            "    return response;" +
            "  });" +
            "};"
        );
    }

    public int getLoginApiStatusCode(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d ->
            ((JavascriptExecutor) d).executeScript("return window.__loginApiResponse !== null;").equals(true)
        );
        Object status = ((JavascriptExecutor) driver).executeScript("return window.__loginApiResponse.status;");
        return ((Long) status).intValue();
    }

    public int getLoginApiErrorCode(WebDriver driver) {
        Object code = ((JavascriptExecutor) driver).executeScript("return window.__loginApiResponse.body.code;");
        return ((Long) code).intValue();
    }
}

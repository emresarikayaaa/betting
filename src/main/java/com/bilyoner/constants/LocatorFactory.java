package com.bilyoner.constants;

import com.bilyoner.constants.android.AndroidHomeLocators;
import com.bilyoner.constants.android.AndroidLoginLocators;
import com.bilyoner.constants.ios.IosHomeLocators;
import com.bilyoner.constants.ios.IosLoginLocators;
import com.bilyoner.utils.ConfigReader;

public class LocatorFactory {
    private static LoginLocators loginLocators;
    private static HomeLocators homeLocators;

    private static boolean isAndroid(){
        return ConfigReader.get("platform").equalsIgnoreCase("Android");
    }
    public static LoginLocators loginPage(){
        if (loginLocators == null){
            loginLocators = isAndroid() ? new AndroidLoginLocators() : new IosLoginLocators();
        }
        return loginLocators;
    }
    public static HomeLocators homePage(){
        if (homeLocators == null){
            homeLocators = isAndroid() ? new AndroidHomeLocators() : new IosHomeLocators();
        }
        return homeLocators;
    }
}

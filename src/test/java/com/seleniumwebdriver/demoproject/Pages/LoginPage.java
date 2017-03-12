package com.seleniumwebdriver.demoproject.Pages;

import com.seleniumwebdriver.demoproject.Selenium.Driver;

/**
 * Implements actions related to Login page
 */
public class LoginPage {
    public static void goTo() {
        Driver.Instance.get(Driver.baseAddress + "wp-login.php");
    }

    public static LoginCommand loginAs(String userName){
        return new LoginCommand(userName);
//        return LoginCommand;
    }
}


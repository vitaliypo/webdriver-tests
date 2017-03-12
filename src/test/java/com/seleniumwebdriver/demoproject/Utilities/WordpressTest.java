package com.seleniumwebdriver.demoproject.Utilities;

import com.seleniumwebdriver.demoproject.Pages.LoginPage;
import com.seleniumwebdriver.demoproject.Selenium.Driver;
import org.junit.After;
import org.junit.Before;

/**
 * Common test class, implements actions which should be executed before and after every test
 */
public class WordpressTest {
    @Before
    public void Init() {
        Driver.initialize();
        LoginPage.goTo();
        LoginPage.loginAs("admin").withPassword("password").login();
    }
    @After
    public void cleanUp()
    {
        Driver.close();
    }
}

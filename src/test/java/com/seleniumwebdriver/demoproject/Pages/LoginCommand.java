package com.seleniumwebdriver.demoproject.Pages;

import com.seleniumwebdriver.demoproject.Selenium.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
/**
* class implements low-level login functionality
*/
public class LoginCommand {
    private String userName;
    private String password;

    public LoginCommand(String userName) {
        this.userName = userName;
    }

    public LoginCommand withPassword(String password) {
        this.password = password;
        return this;
    }

    public void login() {
        WebElement loginInput = Driver.Instance.findElement(By.id("user_login"));
        Driver.Wait(1000);
        loginInput.sendKeys(userName);
        WebElement passwordInput = Driver.Instance.findElement(By.id("user_pass"));
        Driver.Wait(1000);
        passwordInput.sendKeys(password);
        WebElement loginButton = Driver.Instance.findElement(By.id("wp-submit"));
        loginButton.click();
        WebElement dashboard = Driver.Instance.findElement(By.tagName("h1"));
        Assert.assertTrue("Login failed!", dashboard.getText().equals("Dashboard"));
    }
}
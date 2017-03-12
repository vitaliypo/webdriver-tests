package com.seleniumwebdriver.demoproject.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * implements Driver interactions
 */
public class Driver {
    public static WebDriver Instance;
    public static String baseAddress = "http://localhost:25601/"; //URL of our WordPress web server

    public static void Wait(long millisecondsToSleep) {
        try {
            Thread.sleep(millisecondsToSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void initialize()
    {
        System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
        setInstance(new ChromeDriver());
        Instance.manage().window().maximize();
    }

    public static WebDriver getInstance() {
        return Instance;
    }

    public static void setInstance(WebDriver instance) {
        Instance = instance;
    }

    public static void close()
    {
        Instance.close();
    }
}
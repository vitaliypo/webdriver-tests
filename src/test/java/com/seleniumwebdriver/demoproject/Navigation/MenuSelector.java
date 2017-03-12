package com.seleniumwebdriver.demoproject.Navigation;

import com.seleniumwebdriver.demoproject.Selenium.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

/**
 * This class implements selection of points for navigation
 */
public class MenuSelector {
    public static void select(String topLevelMenuId, String subMenuLinkText) {
        Driver.Instance.findElement(By.id(topLevelMenuId)).click();
        WebElement subMenu = Driver.Instance.findElement(By.linkText(subMenuLinkText));
        try {
            subMenu.click();
        } catch (StaleElementReferenceException e) { //if subMenu element been lost we'll re-find it here
            WebElement subMenuRetry = Driver.Instance.findElement(By.linkText(subMenuLinkText));
            subMenuRetry.click();
        }
    }
}
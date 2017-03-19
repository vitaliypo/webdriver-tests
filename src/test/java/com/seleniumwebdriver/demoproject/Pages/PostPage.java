package com.seleniumwebdriver.demoproject.Pages;

import com.seleniumwebdriver.demoproject.Selenium.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * implements functionality of single post viewing page
 * TODO: refactor CreatePostTest to use view instead of NewPostPage?
 */
public class PostPage {
    public static String getCurrentPostTitle() {
        WebElement title = Driver.Instance.findElement(By.className("entry-title"));
        if (title != null) return title.getText();
        else {
                String s = "";
                return s;
        }
    }
}

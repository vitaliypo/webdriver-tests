package com.seleniumwebdriver.demoproject.Pages;

import com.seleniumwebdriver.demoproject.Selenium.Driver;
import com.seleniumwebdriver.demoproject.Navigation.LeftNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Functionality of post or page creation and editing page
 */
public class NewPostPage {
    public static String getTitle() {
        WebElement title = Driver.Instance.findElement(By.id("title"));
        if (title != null) return title.getAttribute("value");
        else return null;
    }

    public static void goTo() {
        LeftNavigation.Posts.AddNew.select();
    }

    public static CreatePostCommand createPost(String title) {
        return new CreatePostCommand(title);
    }

    public static void GoToNewPost() {
        WebElement message = Driver.Instance.findElement(By.id("message"));
        List<WebElement> links = message.findElements(By.tagName("a"));
        links.get(0).click();
    }

    public static boolean IsInEditMode() {
        return Driver.Instance.findElement(By.tagName("h1")).getText().contains("Edit ");
    }

//    class implements high-level post creation functionality
    public static class CreatePostCommand {
        private String title;
        private String body;

        private CreatePostCommand(String title) {
            this.title = title;
        }

        public CreatePostCommand withBody(String body) {
            this.body = body;
            return this;
        }

        public void publish() {
            Driver.Instance.findElement(By.id("title")).sendKeys(title);
            Driver.Instance.switchTo().frame("content_ifr");
            Driver.Instance.switchTo().activeElement().sendKeys(body);
            Driver.Instance.switchTo().defaultContent();
            Driver.Wait(2500);
            Driver.Instance.findElement(By.id("publish")).click();
        }
    }
}
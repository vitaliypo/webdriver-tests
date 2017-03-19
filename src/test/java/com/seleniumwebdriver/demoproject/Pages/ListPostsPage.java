package com.seleniumwebdriver.demoproject.Pages;

import com.seleniumwebdriver.demoproject.Selenium.Driver;
import com.seleniumwebdriver.demoproject.Navigation.LeftNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

/**
 * Implements actions related to "All Posts" and "All Pages" pages
 */
public class ListPostsPage {
    private static int previousPostCount;
    private static int currentPostCount;

    public static void selectByTitle(String contentType, String title) {
        makeSureIsAt(contentType+"s");
        WebElement link = Driver.Instance.findElement(By.linkText(title));
        link.click();
    }

    //method checks that current location is Posts or Pages (depends on passed expectedLocation value)
    private static void makeSureIsAt(String expectedLocation) {
        WebElement currentPageHeader = Driver.Instance.findElement(By.tagName("h1"));
        if (currentPageHeader.getText().equals(expectedLocation)) { // are we already at the expected page?
            return;
        }
        else {  // if we're not at desired location we'll navigate there:
            if (expectedLocation.equals("Pages")) {
                LeftNavigation.Pages.AllPages.select();
            }
            else if (expectedLocation.equals("Posts")) {
                LeftNavigation.Posts.AllPosts.select();
            }
        }
    }

    public static boolean doesPostExistsWithTitle(String title) {
        makeSureIsAt("Posts");
        return Driver.Instance.findElements(By.linkText(title)).size()!=0;
    }

    public static void trashPost(String title) {
        makeSureIsAt("Posts");
        try {
            List<WebElement> allPosts = Driver.Instance.findElements(By.tagName("tr")); //get all posts to the list
            for (WebElement row : allPosts) {//find a post with specified title to trash
                List<WebElement> postsWithDesiredTitle = row.findElements(By.linkText(title));
                if (postsWithDesiredTitle.size()>0) { //remove found post
                    Actions action = new Actions(Driver.Instance);
                    action.moveToElement(postsWithDesiredTitle.get(0));
                    action.perform();
                    Driver.Wait(2000);
                    row.findElement(By.className("submitdelete")).click();
                    return; //exit method when post removed
                }
            }
        }
        catch (StaleElementReferenceException e) {
            System.err.println("Something changed on a page during removal. Retrying trashPost...");
            trashPost(title);
            return;
        }
        System.err.println("No post been deleted during trashPost."); //this line executed only if no post found to delete in try block
    }

    //update previousPostCount and currentPostCount with fresh values if any changes exist
    public static void refreshPostsCounter() {
        makeSureIsAt("Posts");
        int tempCurrentCounter = getCounter(); //get current quantity of posts
        if (tempCurrentCounter!= currentPostCount) { //refresh counter value if it's been changed after the last check
            previousPostCount = currentPostCount;
            currentPostCount = tempCurrentCounter;
        }
    }

//    helper method for refreshPostCount
    private static int getCounter() {
        String countText = Driver.Instance.findElement(By.className("displaying-num")).getText();
        try {
            return Integer.parseInt(countText.replaceAll("[\\D]", "")); //extract only digits and return it
        }
        catch (NumberFormatException E) {
            return 0; //return zero in case if there is no posts (and post counter as well)
        }
    }

    public static int getPreviousPostCount() {
        return previousPostCount;
    }

    public static int getCurrentPostCount() {
        return currentPostCount;
    }

//    method to search posts with search box
    public static void searchPostByKeyword(String searchKeyword) {
        makeSureIsAt("Posts");
        WebElement searchBox = Driver.Instance.findElement(By.id("post-search-input"));
        searchBox.sendKeys(searchKeyword);
        WebElement searchButton = Driver.Instance.findElement(By.id("search-submit"));
        searchButton.click();
    }
}
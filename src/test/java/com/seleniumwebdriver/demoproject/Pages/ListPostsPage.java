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
//    public enum PostType {
//        Posts, Page
//    }

    public static void selectByTitle(String contentType, String title) {
//        String currentLocation=contentType+"s"; //unite these two rows to one
        makeSureIsAt(contentType+"s");
        WebElement link = Driver.Instance.findElement(By.linkText(title));
        link.click();
    }

    private static void makeSureIsAt(String expectedLocation) {
//        System.out.println("run makeSureIsAt");
        WebElement currentPageHeader = Driver.Instance.findElement(By.tagName("h1"));
//        System.out.println("element located");
//        Driver.Wait(10);
//        System.out.println(currentPageHeader.getText());
        if (currentPageHeader.getText().equals(expectedLocation)) { // are we already on expected page?
//            System.out.println("1st if");
            return;
        }
        else {  // if we're not at desired location we'll navigate there here:
            if (expectedLocation.equals("Pages")) {
                LeftNavigation.Pages.AllPages.select();
//                System.out.println("2nd if");
            }
            else if (expectedLocation.equals("Posts")) {
                LeftNavigation.Posts.AllPosts.select();
//                System.out.println("3rd if");
            }
        }
    }

    public static boolean doesPostExistsWithTitle(String title) {
        makeSureIsAt("Posts");
        return Driver.Instance.findElements(By.linkText(title)).size()!=0;
    }

    public static void trashPost(String title) {
//        TODO: test negative case
        makeSureIsAt("Posts");
        Boolean postRemoved = false;
        try {
            List<WebElement> allPosts = Driver.Instance.findElements(By.tagName("tr")); //get all posts in the list
            for (WebElement row : allPosts) {//find a post with specified title to trash
                List<WebElement> postsWithDesiredTitle = null;
                postsWithDesiredTitle = row.findElements(By.linkText(title)); //unite these two expressions?
                if (postsWithDesiredTitle.size()>0) {
                    Actions action = new Actions(Driver.Instance);
                    action.moveToElement(postsWithDesiredTitle.get(0));
                    action.perform();
                    Driver.Wait(2000);
                    row.findElement(By.className("submitdelete")).click();
                    postRemoved = true;
                    //find message that post deleted. If not found, then message has not been deleted: fail test
                    //WebElement removedMessage = Driver.Instance.findElement(By.id("message"));
//                  if (removedMessage!=null) postRemoved=true;
                    return;
                }
            } //should this exception be Runtime or another type??? which is checked and which is unchecked?
        }
        catch (StaleElementReferenceException e) {
            System.err.println("Something changed on a page during removal. Retrying trashPost...");
            trashPost(title);
            return;
        }
//        catch (NoSuchElementException e) {
//            System.err.println("No post been deleted during trashPost. 1");
//        }
        if(!postRemoved) System.err.println("No post been deleted during trashPost.");
    }

    public static void refreshPostsCounter() {
        makeSureIsAt("Posts");
        int tempCurrentCounter = getCounter(); //get current quantity of posts
        if (tempCurrentCounter!= currentPostCount) { //refresh counter value if it's been changed after the last check
            previousPostCount = currentPostCount;
            currentPostCount = tempCurrentCounter;
        }
    }

    private static int getCounter() {
        String countText = Driver.Instance.findElement(By.className("displaying-num")).getText();
        try {
            return Integer.parseInt(countText.replaceAll("[\\D]", ""));
        }
        catch (NumberFormatException E) {
            return 0;
        }
        //clarify how this magic regex works
    }

    public static int getPreviousPostCount() {
        return previousPostCount;
    }

    public static int getCurrentPostCount() {
        return currentPostCount;
    }

    public static void searchPostByKeyword(String searchKeyword) {
        makeSureIsAt("Posts");
        WebElement searchBox = Driver.Instance.findElement(By.id("post-search-input"));
        searchBox.sendKeys(searchKeyword);
        WebElement searchButton = Driver.Instance.findElement(By.id("search-submit"));
        searchButton.click();
    }
}
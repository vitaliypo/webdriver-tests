package com.seleniumwebdriver.demoproject.SmokeTests;

import com.seleniumwebdriver.demoproject.Pages.NewPostPage;
import com.seleniumwebdriver.demoproject.Pages.AllPostsAllPagesPage;
import com.seleniumwebdriver.demoproject.Utilities.WordpressTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Checks that is is possible to edit a page
 */
public class PageTest extends WordpressTest {
    @Test
    public void Can_Enter_Edit_Mode_At_Pages() {
        String title = "Sample Page"; //get rid of it
        AllPostsAllPagesPage.selectByTitle("Page", title);
        Assert.assertTrue("Not in edit mode", NewPostPage.isInEditMode());
        Assert.assertEquals("Title didn't match", title, NewPostPage.getTitle()); //make sure that page we edit is the desired one
    }
}
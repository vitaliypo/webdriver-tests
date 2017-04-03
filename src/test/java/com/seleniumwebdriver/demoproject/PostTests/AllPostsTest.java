package com.seleniumwebdriver.demoproject.PostTests;

import com.seleniumwebdriver.demoproject.Workflow.PostCreator;
import com.seleniumwebdriver.demoproject.Pages.AllPostsAllPagesPage;
import com.seleniumwebdriver.demoproject.Pages.NewPostPage;
import com.seleniumwebdriver.demoproject.Selenium.Driver;
import com.seleniumwebdriver.demoproject.Utilities.WordpressTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Main posts functionality tests
 */
public class AllPostsTest extends WordpressTest {
    @Test
    public void added_Posts_Show_Up() {
        AllPostsAllPagesPage.refreshPostsCounter(); //get fresh values of posts counter
        PostCreator.createPost();
        Driver.Wait(2000); //give some time to create a post
        //we have to refresh counter values manually now but this should be refactored later
        AllPostsAllPagesPage.refreshPostsCounter();

        //let's make sure that counter been increased by 1 and created post exists
        Assert.assertEquals("Count of posts not equal", AllPostsAllPagesPage.getPreviousPostCount() + 1, AllPostsAllPagesPage.getCurrentPostCount());
        Assert.assertTrue(AllPostsAllPagesPage.doesPostExistsWithTitle(PostCreator.lastTitle));

        //let's remove created post and check that counter decreased by 1
        AllPostsAllPagesPage.trashPost(PostCreator.lastTitle);
        AllPostsAllPagesPage.refreshPostsCounter();
        Assert.assertEquals("Count of posts not reverted back after post deletion", AllPostsAllPagesPage.getPreviousPostCount() - 1, AllPostsAllPagesPage.getCurrentPostCount());
    }

    @Test
    public void can_Search_Posts_By_Title() {
        PostCreator.createPost();
        Driver.Wait(2000);

        AllPostsAllPagesPage.searchPostByKeyword(PostCreator.lastTitle); //use search to find our post
        AllPostsAllPagesPage.selectByTitle("Post", PostCreator.lastTitle); //select our post (in edit mode)

        Assert.assertEquals("Title didn't match", NewPostPage.getTitle(), PostCreator.lastTitle);

        AllPostsAllPagesPage.trashPost(PostCreator.lastTitle); //clean-up
        //We could move trashPost to PostCreator level but it's not always necessary to remove created post
    }
}
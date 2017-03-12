package com.seleniumwebdriver.demoproject.PostTests;

import com.seleniumwebdriver.demoproject.Workflow.PostCreator;
import com.seleniumwebdriver.demoproject.Pages.ListPostsPage;
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
    public void added_Posts_Show_Up()
    {
        ListPostsPage.refreshPostsCounter(); //get fresh values of posts counter
        PostCreator.createPost();
        Driver.Wait(2000); //give some time to create a post
        //we have to refresh counter values manually now but this should be refactored later
        ListPostsPage.refreshPostsCounter();

        //let's make sure that counter been increased by 1 and created post exists
        Assert.assertEquals("Count of posts not equal", ListPostsPage.getPreviousPostCount() + 1, ListPostsPage.getCurrentPostCount());
        Assert.assertTrue(ListPostsPage.doesPostExistsWithTitle(PostCreator.lastTitle));

        //let's remove created post and check that counter decreased by 1
        ListPostsPage.trashPost(PostCreator.lastTitle);
        ListPostsPage.refreshPostsCounter();
        Assert.assertEquals("Count of posts not reverted back after post deletion", ListPostsPage.getPreviousPostCount() - 1, ListPostsPage.getCurrentPostCount());
    }

    @Test
    public void can_Search_Posts_By_Title()
    {
        PostCreator.createPost();
        Driver.Wait(2000);

        ListPostsPage.searchPostByKeyword(PostCreator.lastTitle); //use search to find our post
        ListPostsPage.selectByTitle("Post", PostCreator.lastTitle); //select our post (in edit mode)

        Assert.assertEquals("Title didn't match", NewPostPage.getTitle(), PostCreator.lastTitle);

        ListPostsPage.trashPost(PostCreator.lastTitle);
        //We could move trashPost to PostCreator level but it's not always necessary to remove created post
    }
}
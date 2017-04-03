package com.seleniumwebdriver.demoproject.SmokeTests;

import com.seleniumwebdriver.demoproject.Pages.NewPostPage;
import com.seleniumwebdriver.demoproject.Pages.PostPage;
import com.seleniumwebdriver.demoproject.Workflow.PostCreator;
import com.seleniumwebdriver.demoproject.Utilities.WordpressTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Checks that is is possible to create a post
 */
public class CreatePostTest extends WordpressTest {
    @Test
    public void CreatePostTest()
    {
        PostCreator.createPost();
        NewPostPage.goToNewPost();
        Assert.assertEquals("Title not matched.", PostCreator.lastTitle, PostPage.getCurrentPostTitle());
    }
}
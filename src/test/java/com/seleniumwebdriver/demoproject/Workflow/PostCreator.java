package com.seleniumwebdriver.demoproject.Workflow;
import com.seleniumwebdriver.demoproject.Pages.NewPostPage;
import java.util.Random;

/**
 * Class used for easy post creation
 */
public class PostCreator {
    public static String lastTitle;
    public static String lastBody;

    public static void createPost()
    {
        NewPostPage.goTo();
        lastTitle = CreateTitle();
        lastBody = CreateBody();
        NewPostPage.createPost(lastTitle).withBody(lastBody).publish();
    }

    private static String CreateBody() {
        return CreateRandomString() + ", body";
    }

    private static String CreateTitle() {
        return CreateRandomString() + ", title";
    }

    private static String CreateRandomString() {
        StringBuilder s = new StringBuilder();
        Random random = new Random();
        int cycles = random.nextInt(5+1);

        for (int i = 0; i<cycles; i++)
        {
            s.append(Words[random.nextInt(Words.length)]);
            s.append(" ");
            s.append(Articles[random.nextInt(Articles.length)]);
            s.append(" ");
            s.append(Words[random.nextInt(Words.length)]);
            s.append(" ");
        }
        return s.toString();
    }

    private static String[] Words = {"boy", "cat", "dog", "rabbit", "baseball", "throw", "find", "scary", "code", "mustard"};
    private static String[] Articles = {"the", "an", "and", "a", "of", "to", "it", "as"};
}

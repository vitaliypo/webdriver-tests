package com.seleniumwebdriver.demoproject.Workflow;
import com.seleniumwebdriver.demoproject.Pages.NewPostPage;
import java.util.Random;

/**
 * Class used for easy post creation
 */
public class PostCreator {
    public static String lastTitle;
    public static String lastBody;

    public static void createPost() {
        NewPostPage.goTo();
        lastTitle = createTitle();
        lastBody = createBody();
        NewPostPage.createPost(lastTitle).withBody(lastBody).publish();
    }

    private static String createBody() {
        return createRandomString() + ", body";
    }

    private static String createTitle() {
        return createRandomString() + ", title";
    }

    private static String createRandomString() { //generate random string from predefined words and articles
        StringBuilder resultString = new StringBuilder();
        Random randomGenerator = new Random();
        int cycles = randomGenerator.nextInt(5+1);
        for (int i = 0; i<cycles; i++) {
            resultString.append(words[randomGenerator.nextInt(words.length)]);
            resultString.append(" ");
            resultString.append(articles[randomGenerator.nextInt(articles.length)]);
            resultString.append(" ");
            resultString.append(words[randomGenerator.nextInt(words.length)]);
            resultString.append(" ");
        }
        return resultString.toString();
    }

    private static String[] words = {"boy", "cat", "dog", "rabbit", "baseball", "throw", "find", "scary", "code", "mustard"};
    private static String[] articles = {"the", "an", "and", "a", "of", "to", "it", "as"};
}
package com.seleniumwebdriver.demoproject.Navigation;

/**
 * Class implements left menu navigation
 */

public class LeftNavigation {
    public static class Posts {
        public static class AddNew {
            public static void select() {
                MenuSelector.select("menu-posts", "Add New");   //go to Add New Post page
            }
        }

        public static class AllPosts {
            public static void select() {
                MenuSelector.select("menu-posts", "All Posts"); //go to All Posts page
            }
        }
    }

    public static class Pages {
        public static class AllPages {
            public static void select() {
                MenuSelector.select("menu-pages", "All Pages"); //go to All Pages page
            }
        }
    }
}
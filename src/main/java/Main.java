import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    Reddit reddit;
     Main() {
         reddit = new Reddit();
     }

    public static void main(String[] args) {
        Account account;
        int option;
        boolean logOut = false;
        Scanner scanner = new Scanner(System.in);

        do {
            account = authPage();
            if (account == null) {
                continue;
            } else {
                break;
            }
        } while (true);


        while (!logOut) {
            option = menuPage();

            switch (option) {
                case 1 :
                    timelinePage(account);
                    break;

                case 2 :
                    account.createSubreddit();
                    break;

                case 3 :
                    if (account.joinedSubreddits.isEmpty()) {
                        System.out.println("You have not joined any subreddits </3");
                        break;

                    } else {
                        int choose;
                        int number = 1;

                        System.out.println("Please choose between one of the above subreddits : ");

                        for (Subreddit subreddit : account.joinedSubreddits) {
                            System.out.println(number + ") r/" + subreddit.name);
                            number++;

                        }

                        System.out.print(": ");
                        do {
                            choose = scanner.nextInt();

                            if (choose > 0 & choose <= account.joinedSubreddits.size()) {
                                Subreddit subreddit = account.joinedSubreddits.get(choose - 1);
                                account.createPost(subreddit);
                                System.out.println("Post successfully created <3");
                                break;

                            } else {
                                System.out.print("Pleasae choose between one of the above : ");
                            }
                        } while(true);
                    }
                    break;

                case 4 :
                    String name;
                    char nameStartswith;

                    System.out.print("Enter what you want to search (starts with r/ for subreddits or u/ for users) : ");

                    do {
                        name = scanner.nextLine();
                        nameStartswith = name.charAt(0);

                        if (nameStartswith == 'u' | nameStartswith == 'r') {
                            break;
                        } else {
                            System.out.print("Please the name you want starts with r/ or u/ : ");

                        }
                    } while (true);

                    Reddit.search(name , account);

                    break;

                case 5 :
                    if (!account.posts.isEmpty()) {
                        Post post = Reddit.postScroller(account.posts , account);

                        if (post != null) {
                            post.viewPost();
                            post.explorePost(account);
                        }
                    }
                    System.out.println("you haven't created any post </3");
                    break;

                case 6 :
                    logOut = true;
                    break;
            }
        }

        main(new String[0]);


    }

    public static Account authPage() {
        int authMethod;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Hey welcome to JReddit , to continue choose one of the above options : \n\t1) Log in\t\t2) Sign up\n: ");

        do {
            authMethod = scanner.nextInt();

            if (authMethod == 1 | authMethod == 2) {
                Account account;
                switch (authMethod) {
                    case 1 :
                        account = Reddit.authenticate();
                        if (account == null) {
                            System.out.println("One or more of the following credentials is incorrect : \n\tusername , email , or password");
                            return null;
                        } else {
                            return account;

                        }

                    case 2 :
                        account = Reddit.signUp();
                        if (account == null) {
                            System.out.println("PROBLEMMMMM !!!!!");

                        } else {
                            return account;
                        }
                }


            } else {
                System.out.print("Please choose between 1 or 2 : ");
            }
        } while (true);

    }


    public static void timelinePage(Account account) {
        ArrayList<Post> posts = new ArrayList<>();

        for (Post redditPost : Reddit.posts) {
            for (Subreddit subreddit : account.joinedSubreddits) {
                for (Post accountPost : subreddit.posts) {
                    if (Objects.equals(redditPost , accountPost)) {
                        posts.add(redditPost);
                    }
                }
            }
        }

        if (posts.isEmpty()) {
            System.out.println("There is no post for now </3\nJoin more subreddit to have more post in timeline .");
            return;
        }

        Collections.reverse(posts);

        Post post = Reddit.postScroller(posts , account);

        if (post != null) {
            post.viewPost();
            post.explorePost(account);
        }

    }


    public static int menuPage() {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("where you want to go ? : ");
        System.out.print("1) Timeline\n2) Create subreddit\n3) Create post \n4) Search\n5) Show my posts\n6) Log out\n: ");

        do {
            option = scanner.nextInt();

            if (option > 0 & option < 7) {
                break;
            } else {
                System.out.print("Please choose between 1 , 5 : ");
            }
        } while (true);

        return option;
    }

}

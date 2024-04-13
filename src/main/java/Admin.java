import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Admin extends Account{
    Subreddit subreddit;
    Admin(Account account , Subreddit subreddit) {
        super(account.name, account.username, account.password, account.emailAddress , account.accountID);
        this.subreddit = subreddit;

    }


    public void adminAction() {
        boolean page = true;
        boolean option;
        Scanner scanner = new Scanner(System.in);


        while (page) {
            System.out.println("Hey , you entered Add / Remove section .\nwant to continue ? (y/n)");
            page = scanner.nextLine() == "y";

            if (!page) {
                continue;
            }

            System.out.print("Please enter the username of this user : u/");
            String username = scanner.nextLine();

            ArrayList<Account> similarAccounts = Reddit.findSimilarUsernames(username);

            if (similarAccounts.isEmpty()) {
                System.out.println("Sorry we couldn't find any account with that username </3");
                continue;
            }

            System.out.println("Please choose one of the above similar usernames : ");
            int optionNumber = 1;
            int input;

            for (Account account : similarAccounts) {
                System.out.println(optionNumber + ") u/" + account.username);
                optionNumber++;
            }

            System.out.print(": ");

            do {
                input = scanner.nextInt();
                scanner.nextLine();

                if (input > 0 & input <= similarAccounts.size())  {
                    break;
                } else {
                    System.out.print("Please choose one of the above : ");
                }

            } while (true);

            Account account = similarAccounts.get(input - 1);

            if (subreddit.users.contains(account)) {
                Admin admin = subreddit.adminCheck(account);
                if (admin != null) {
                    if (Objects.equals(admin , subreddit.owner)) {
                        System.out.println("This account is owner of this subreddit , you can't do anything </3");
                        continue;
                    }

                    System.out.println("This user is already admin , would you want to remove it ? : 1) Yes 2) No");
                    option = scanner.nextInt() == 1;
                    scanner.nextLine();

                    if (option) {
                        subreddit.admins.remove(admin);
                        System.out.println("Successfully removed <3");

                    } else {
                        System.out.println("Ok <3");
                    }

                } else {
                    System.out.println("This user isn't in admins list , would you want to add it ? : 1) Yes 2) No");
                    option = scanner.nextInt() == 1;
                    scanner.nextLine();

                    if (option) {
                        admin = new Admin(account , subreddit);
                        subreddit.admins.add(admin);
                        System.out.println("User u/" + account.username + " has successfully added to admins <3");

                    } else {
                        System.out.println("Ok <3");
                    }
                }


            } else {
                System.out.println("Sorry but this user isn't joined to this subreddit already </3 .");
            }

        }
    }

    public void postInteraction(Post post) {
        int option;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hey Admin , Choose : \n1) Delete this post \n2) Ban the author and delete all the post of his");

        do {
            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1 | option == 2) {
                break;

            } else {
                System.out.print("Please choose one of the above : ");
            }
        } while (true);

        switch (option) {
            case 1:
                if (this.accountID == post.subreddit.owner.accountID) {
                    subreddit.posts.remove(post);
                    break;
                }

                if (post.subreddit.adminCheck(post.author) != null) {
                    System.out.println("Author of this post is admin you can't delete their post");
                    break;
                }

                subreddit.posts.remove(post);
                break;

            case 2:
                if (this.accountID == post.subreddit.owner.accountID) {
                    if (subreddit.adminCheck(post.author) != null) {
                        subreddit.admins.remove(subreddit.adminCheck(post.author));
                    }
                }

                if (post.subreddit.adminCheck(post.author) != null) {
                    System.out.println("Author of this post is admin you can't delete their post and ban them </3");
                    break;

                }

                subreddit.banedUsers.add(post.author);
                int numberOfPosts = 0;

                for (Post subredditPost : subreddit.posts) {
                    if (Objects.equals(subredditPost.author, post.author)) {
                        subreddit.posts.remove(subredditPost);
                        numberOfPosts++;
                    }
                }
                System.out.println("Successfully removed " + numberOfPosts + "posts from u/" + post.author);
                break;
        }
    }
}

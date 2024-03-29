import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Reddit {
    static ArrayList<Subreddit> subreddits = new ArrayList<>();
    static ArrayList<Post> posts = new ArrayList<>();
    static ArrayList<Account> accounts = new ArrayList<>();

    public static boolean emailValidator(String email) {
        String regEx = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regEx);
    }


    public static ArrayList<String> getAllUsernames() {
        ArrayList<String> usernames = new ArrayList<>();

        for (Account account : accounts) {
            usernames.add(account.username);
        }

        return usernames;
    }

    public static ArrayList<String> getAllSubredditnames() {
        ArrayList<String> names = new ArrayList<>();

        for (Subreddit subreddit : subreddits) {
            names.add(subreddit.name);
        }

        return names;
    }


    public static ArrayList<String> getAllEmails() {
        ArrayList<String> emailAddress = new ArrayList<>();

        for (Account account : accounts) {
            emailAddress.add(account.emailAddress);
        }

        return emailAddress;
    }

    public static Account signUp() {
        String name;
        String username;
        String password;
        String emailAddress;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hey , welcome to JReddit sign up page .");
        System.out.print("To continue please enter your email address : ");

        do {
            emailAddress = scanner.nextLine();

            if (emailValidator(emailAddress)) {
                break;

            } else if (getAllEmails().contains(emailAddress)) {
                System.out.println("Sorry but the email address you entered is already in use </3 . \n\tTry something else : ");

            } else {
                System.out.print("Sorry but the email address you enter is not valid </3 . \n\tTry something else: ");
            }

        } while (true);

        System.out.print("Good , now please enter your username : ");

        do {
           username = scanner.nextLine();

           if (getAllUsernames().contains(username)) {
               System.out.print("Sorry but the username you enter is already in use </3 . \n\tTry something else : ");
           } else {
               break;
           }

        } while(true);

        System.out.print("Oh it's going well , please enter your password : ");
        password = scanner.nextLine();

        System.out.print("Just one more step .\n\tEnter your name : ");
        name = scanner.nextLine();

        Account account = new Account(name , username , password , emailAddress);
        accounts.add(account);

        return account;
    }


    public static Account authenticate() {
        String password;
        int loginMethod;
        Scanner scanner = new Scanner(System.in);

        System.out.print("You want to log in with username or email address ? \n\t1) Username \t2) Email\n: ");

        do {
            loginMethod = scanner.nextInt();

            if (loginMethod == 1 | loginMethod == 2) {
                switch (loginMethod) {
                    case 1 :
                        System.out.print("So please enter your username : ");
                        String username = scanner.nextLine();

                        System.out.print("Now enter your password : ");
                        password = scanner.nextLine();

                        for (Account account : accounts) {
                            if (Objects.equals(account.username , username)) {
                                if (Objects.equals(account.password , DigestUtils.sha256Hex(password))) {
                                    return account;
                                }
                            }
                        }

                        return null;

                    case 2 :
                        System.out.print("So please enter your email address : ");
                        String emailAddress;

                        do {
                            emailAddress = scanner.nextLine();

                            if (emailValidator(emailAddress)) {
                                break;
                            } else {
                                System.out.print("Sorry but the entered email address is not valid . \nTry again : ");
                            }

                        } while (true);

                        System.out.print("Now enter your password : ");
                        password = scanner.nextLine();

                        for (Account account : accounts) {
                            if (Objects.equals(account.emailAddress , emailAddress)) {
                                if (Objects.equals(account.password , DigestUtils.sha256Hex(password))) {
                                    return account;
                                }
                            }
                        }

                        return null;
                }

            } else {
                System.out.print("Please choose between 1 or 2 : ");
            }

        } while (true);
    }


    public static void postScroller(ArrayList<Post> posts , Account account) {
        Scanner scanner = new Scanner(System.in);
        Post post = null;
        int postNumber = 0;
        String option;

        while (postNumber < posts.size()) {
            post = posts.get(postNumber);

            System.out.println("r/" + post.subreddit.name + " via u/" + post.author.username);
            System.out.println(post.title);
            System.out.println("\t" + post.content);
            System.out.println("\t\t" + post.vote + "^");

            System.out.println("Enter to show more , q to quite , w to go up , s to get down : ");
            option = scanner.nextLine();

            if (Objects.equals(option, "")) {
                break;

            } else if (Objects.equals(option, "w")) {
                if (postNumber != 0) {
                    postNumber--;
                }
                continue;

            } else if (Objects.equals(option , "s")) {
                postNumber++;
                continue;

            } else {
                post = null;
                break;
            }
        }
        
       if (post == null){
           System.out.println("There is no post anymore </3 .");
           return;

       } else {
           post.viewPost();
           System.out.print("Explore : \n1) This subreddit   2) Author profile    3) Comments     4) Vote     5) Quite");
           int exploreOption = scanner.nextInt();

           switch (exploreOption) {
               case 1 :
                   postScroller(post.subreddit.posts , account);
                   break;

               case 2 :
                   post.author.viewProfile();
                   return;

               case 3 :
                   commentScroller(post , account);
                   return;

               case 4 :
                   int vote;
                   System.out.print("1) Down / 2) Up : ");

                   do {
                       vote = scanner.nextInt();

                       if (vote == 1 | vote == 2) {
                           break;

                       } else {
                           System.out.println("Please choose between 1 or 2 : ");
                       }
                   } while (true);

                   boolean isUpVote = vote == 2;

                   account.vote(post , isUpVote);
                    post.author.updateKarma();

               default :
                   return;
           }
       }
        
    }


    public static void commentScroller(Post post , Account account) {
        Scanner scanner = new Scanner(System.in);
        String option;
        Comment comment = null;

        int commentNumber = 0;
        while (commentNumber < post.comments.size()){
            comment = post.comments.get(commentNumber);

            comment.viewComment();

            System.out.println("Enter to select , q to quite , w to go up , s to get down : ");
            option = scanner.nextLine();

            if (Objects.equals(option, "")) {
                break;

            } else if (Objects.equals(option, "w")) {
                if (commentNumber!= 0) {
                    commentNumber--;
                }
                continue;

            } else if (Objects.equals(option , "s")) {
                commentNumber++;
                continue;

            } else {
                comment = null;
                break;
            }
        }

        if (comment == null) {
            System.out.println("There is no comment anymore </3 .");
            return;

        } else {
            boolean choose;
            System.out.println("Would you want to vote 1) Yes / 2) No ");

            choose = scanner.nextInt() == 1;

            if (choose) {
                boolean isUpVote;
                System.out.println("1) Down / 2) Up");

                isUpVote = scanner.nextInt() == 2;

                account.vote(comment , isUpVote);
                comment.author.updateKarma();
            }
            return;
        }
    }



    public static boolean search(String name , Account searcherAccount) {
        Scanner scanner = new Scanner(System.in);
        String objectName = name.substring(2);
        ArrayList<String> usernames = getAllUsernames();
        ArrayList<String> subredditsNames = getAllSubredditnames();

        boolean isAccount = name.charAt(0) == 'u'; // if its account name or subreddits name

        if (isAccount) {
            ArrayList<Account> similarAccounts = findSimilarUsernames(objectName);

            if (similarAccounts.isEmpty()) {
                System.out.println("Sorry we couldn't find any username like this username </3");
                return false;

            } else {
                int number = 0;
                int choose;
                System.out.println("Please choose one of the above accounts : ");

                for (Account account : similarAccounts) {
                    System.out.println(number + ") u/" + account.username);
                    number++;
                }

                System.out.print(": ");

                do {
                   choose = scanner.nextInt();

                   if (choose > 0 | choose <= similarAccounts.size()) {
                        break;
                   } else {
                       System.out.print("Please choose between one of the above : ");
                   }
                } while (true);

                Account account = similarAccounts.get(choose - 1);
                account.viewProfile();
                return true;
            }

        } else {
            ArrayList<Subreddit> similarSubreddits = findSimilarSubreddits(objectName);

            if (similarSubreddits.isEmpty()) {
                System.out.println("Sorry but we couldn't find any subreddit with this name </3");
                return false;

            } else {
                int number = 0;
                int choose;
                System.out.println("Please choose one of the above subreddits : ");

                for (Subreddit subreddit : similarSubreddits) {
                    System.out.println(number + ") r/" + subreddit.name);
                    number++;
                }

                System.out.print(": ");

                do {
                    choose = scanner.nextInt();

                    if (choose > 0 | choose <= similarSubreddits.size()){
                        break;

                    } else {
                        System.out.print("Please choose between one of the above : ");
                    }
                } while (true);

                Subreddit subreddit = subreddits.get(subredditsNames.indexOf(objectName));


                System.out.println("r/" + subreddit.name);
                System.out.println("Number of posts : " + subreddit.posts.size());
                System.out.println("Number of users : " + subreddit.users.size());

                if (subreddit.posts.size() != 0) {
                    postScroller(subreddit.posts , searcherAccount);
                }

                return true;
            }
        }
    }


    public static ArrayList<Account> findSimilarUsernames(String name) {
        ArrayList<Account> similarAccounts = new ArrayList<>();

        for (Account account : accounts) {
            if (account.username.contains(name) | name.contains(account.username)) {
                similarAccounts.add(account);
            }
        }

        return similarAccounts;
    }


    public static ArrayList<Subreddit> findSimilarSubreddits(String name) {
        ArrayList<Subreddit> similarSubreddits = new ArrayList<>();

        for (Subreddit subreddit : subreddits) {
           if (subreddit.name.contains(name) | name.contains(subreddit.name)) {
               similarSubreddits.add(subreddit);
           }
        }

        return similarSubreddits;
    }

}

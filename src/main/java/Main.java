import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    }

    public static Account authPage() {
        int authMethod;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Hey welcome to JReddit , to continue choose one of the above options : \n\t1) Log in\t\t2) Sign up\n : ");

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


    public Post timelinePage(Account account) {
        Scanner scanner = new Scanner(System.in);
        String option;
        Post post;

        int postNumber = 0;
        while (postNumber < account.posts.size()){
            post = account.posts.get(postNumber);

            System.out.println("r/" + post.subreddit.name + " via u/" + post.author.username);
            System.out.println(post.title);
            System.out.println("\t" + post.content);
            System.out.println("\t\t" + post.vote + "^");

            System.out.println("Enter to show more , q to quite , w to go up , s to get down : ");
            option = scanner.nextLine();

            if (Objects.equals(option, "")) {
                return post;

            } else if (Objects.equals(option, "w")) {
                if (postNumber != 0) {
                    postNumber--;
                }
                continue;

            } else if (Objects.equals(option , "s")) {
                postNumber++;
                continue;

            } else {
                return null;
            }
        }

        System.out.println("There is no post anymore </3 .");
        return null;
    }



    public Comment commentPage(Post post) {
        Scanner scanner = new Scanner(System.in);
        String option;
        Comment comment;

        int commentNumber = 0;
        while (commentNumber < post.comments.size()){
            comment = post.comments.get(commentNumber);

            comment.viewComment();

            System.out.println("Enter to select , q to quite , w to go up , s to get down : ");
            option = scanner.nextLine();

            if (Objects.equals(option, "")) {
                return comment;

            } else if (Objects.equals(option, "w")) {
                if (commentNumber!= 0) {
                    commentNumber--;
                }
                continue;

            } else if (Objects.equals(option , "s")) {
                commentNumber++;
                continue;

            } else {
                return null;
            }
        }

        System.out.println("There is no comment anymore </3 .");
        return null;
    }

}

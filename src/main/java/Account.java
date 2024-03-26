import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;

public class Account {
    String name;
    String username;
    String password;
    String emailAddress;
    final UUID accountID;
    int karma;
    ArrayList<Subreddit> joinedSubreddits = new ArrayList<>();
    ArrayList<Comment> comments = new ArrayList<>();
    ArrayList<Post> posts = new ArrayList<>();

    Account (String name , String username , String password , String emailAddress) {
        this.name = name;
        this.username = username;
        this.password = DigestUtils.sha256Hex(password);
        this.emailAddress = emailAddress;
        this.accountID = UUID.randomUUID();
    }


    public void viewProfile() {
        System.out.println("Name = " + this.name) ;
        System.out.println("Username = " + this.username);
        System.out.println("Email = " + this.emailAddress);
        System.out.println("Karma = " + this.karma);
        System.out.println("Number of joined subreddits = " + this.joinedSubreddits.size());

        System.out.println("Karmas : ");
        System.out.println("\tPosts : ");

        for (Post post : posts) {
            System.out.println("\t\t" + post.title);
            System.out.println("\t\t\t" + post.vote + "^");
        }

        System.out.println("\tComments : ");

        for (Comment comment : comments) {
            System.out.println("\t\t" + comment.content);
            System.out.println("\t\t\t" + comment.vote + "^");
        }
    }

    public void viewSubreddits() {
        for (Subreddit subreddit : joinedSubreddits) {
            System.out.println("r/" + subreddit.name + ":");
            System.out.println("\tNumber of users = " + subreddit.users.size());
            System.out.println("\tNumber of posts = " + subreddit.posts.size());
        }
    }


    public void createPost(Subreddit subreddit) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("What title you want to set for your post : ");
        String title = scanner.nextLine();

        System.out.println("Please enter the body text for your post : ");
        String content = scanner.nextLine();

        Post post = new Post(this , title , content , subreddit);

        subreddit.posts.add(post);
        Reddit.posts.add(post);
        this.posts.add(post);
    }


    public void createSubreddit() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What name is good for this subreddit : r/");
        String name = scanner.nextLine();

        Subreddit subreddit = new Subreddit(this , name);

        Reddit.subreddits.add(subreddit);
    }
}

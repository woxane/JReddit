import java.util.ArrayList;
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
    }

    public void viewSubreddits() {
        for (Subreddit subreddit : joinedSubreddits) {
            System.out.println("r/" + subreddit.name + ":");
            System.out.println("\tNumber of users = " + subreddit.users.size());
            System.out.println("\tNumber of posts = " + subreddit.posts.size());
        }
    }
}

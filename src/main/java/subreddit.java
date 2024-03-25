import java.util.ArrayList;

public class Subreddit {
    Account owner;
    ArrayList<Account> admins = new ArrayList<>();
    ArrayList<Account> banedUsers = new ArrayList<>();
    ArrayList<Account> users = new ArrayList<>();
    ArrayList<Post> posts = new ArrayList<>();

    subreddit (Account owner) {
        this.owner = owner;
    }
}

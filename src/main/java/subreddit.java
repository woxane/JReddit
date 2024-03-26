import java.util.ArrayList;

public class Subreddit {
    String name;
    Account owner;
    ArrayList<Account> admins = new ArrayList<>();
    ArrayList<Account> banedUsers = new ArrayList<>();
    ArrayList<Account> users = new ArrayList<>();
    ArrayList<Post> posts = new ArrayList<>();

    Subreddit (Account owner , String name) {
        this.owner = owner;
        this.name = name;
    }
}

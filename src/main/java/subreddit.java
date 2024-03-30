import java.util.ArrayList;
import java.util.UUID;

public class Subreddit {
    String name;
    Account owner;
    final UUID subredditID;
    ArrayList<Account> admins = new ArrayList<>();
    ArrayList<Account> banedUsers = new ArrayList<>();
    ArrayList<Account> users = new ArrayList<>();
    ArrayList<Post> posts = new ArrayList<>();

    Subreddit (Account owner , String name) {
        this.owner = owner;
        this.name = name;
        this.subredditID = UUID.randomUUID();
        users.add(owner);
        admins.add(owner);
    }

    public void viewSubreddit() {
        System.out.println("u/" + this.name);
        System.out.println("Number of users = " + this.users.size());
        for (Post post : posts) {
            post.viewPost();
        }
    }
}

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Subreddit {
    String name;
    Account owner;
    final UUID subredditID;
    ArrayList<Admin> admins = new ArrayList<>();
    ArrayList<Account> banedUsers = new ArrayList<>();
    ArrayList<Account> users = new ArrayList<>();
    ArrayList<Post> posts = new ArrayList<>();

    Subreddit (Account owner , String name) {
        this.owner = owner;
        this.name = name;
        this.subredditID = UUID.randomUUID();
        users.add(owner);

        Admin admin = new Admin(owner , this);
        admins.add(admin);

    }

    public void viewSubreddit() {
        System.out.println("u/" + this.name);
        System.out.println("Number of users = " + this.users.size());
        for (Post post : posts) {
            post.viewPost();
        }
    }

    public Admin adminCheck(Account account) {
        for (Admin admin : admins) {
            if (admin.accountID == account.accountID) {
                return admin;
            }
        }
        return null;

    }

}

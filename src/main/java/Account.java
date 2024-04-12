import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
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

    Account(String name , String username , String password , String emailAddress , UUID accountID) {
        this.name = name;
        this.username = username;
        this.password = DigestUtils.sha256Hex(password);
        this.emailAddress = emailAddress;
        this.accountID = accountID;
    }


    public void viewProfile() {
        System.out.println("Name = " + this.name) ;
        System.out.println("Username = " + this.username);
        System.out.println("Email = " + this.emailAddress);
        System.out.println("Karma = " + this.karma);
        System.out.println("Number of joined subreddits = " + this.joinedSubreddits.size());
        this.viewSubreddits();

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
        if (subreddit.banedUsers.contains(this)) {
            System.out.println("Sorry you can't create post in this subreddit (Actually banned)");
        }


        Scanner scanner = new Scanner(System.in);

        System.out.print("What title you want to set for your post : ");
        String title = scanner.nextLine();

        System.out.print("Please enter the body text for your post : ");
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
        this.joinedSubreddits.add(subreddit);
    }


    public boolean vote(Post post , boolean isUpVote) {
        ArrayList<Account> accounts = new ArrayList<>();

        for (Vote vote : post.votes) {
            accounts.add(vote.voter);
        }

        if (accounts.contains(this)) {
            return false;
        }

        Vote vote = new Vote(this , isUpVote);
        post.votes.add(vote);

        this.updateKarma();
        return true;
    }


    public boolean vote(Comment comment , boolean isUpVote) {
        ArrayList<Account> accounts = new ArrayList<>();

        for (Vote vote : comment.votes) {
            accounts.add(vote.voter);
        }

        if (accounts.contains(this)) {
            return false;
        }

        Vote vote = new Vote(this , isUpVote);
        comment.votes.add(vote);

        this.updateKarma();
        return true;
    }


    public void retractVote(Post post) {
        for (Vote vote : post.votes) {
            if (Objects.equals(vote.voter , this)) {
                post.votes.remove(vote);
            }
        }
        this.updateKarma();
    }


    public void retractVote(Comment comment) {
        for (Vote vote : comment.votes) {
            if (Objects.equals(vote.voter , this)) {
                comment.votes.remove(vote);
            }
        }
        this.updateKarma();
    }


    public void updateKarma() {
        for (Post post : this.posts) {
            for (Vote vote : post.votes) {
                this.karma += (vote.isUpVote ? 1 : 0);
            }
        }

        for (Comment comment : this.comments) {
            for (Vote vote : comment.votes) {
                this.karma += (vote.isUpVote ? 1 : 0);
            }
        }
    }
}

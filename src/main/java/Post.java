import java.util.ArrayList;

public class Post {
    final Account author;
    final String title;
    final String content;
    final Subreddit subreddit;
    int vote;
    ArrayList<Comment> comments = new ArrayList<>();

    Post (Account account , String title, String content , Subreddit subreddit) {
        this.author = account;
        this.title = title;
        this.content = content;
        this.subreddit = subreddit;
        this.vote = 0;
    }

    public void viewPost() {
        System.out.println("r/" + this.subreddit.name + " via u/" + this.author.username);
        System.out.println(this.title);
        System.out.println("\n" + this.content);

        for (Comment comment : comments) {
            comment.viewComment();
        }
    }
}

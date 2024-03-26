import java.util.ArrayList;

public class Post {
    final Account author;
    final String title;
    final String content;
    final Subreddit subreddit;
    int vote;
    ArrayList<Comment> comments = new ArrayList<>();

    Post (Account username , String title , String content , Subreddit subreddit) {
        this.author = username;
        this.title = title;
        this.content = content;
        this.subreddit = subreddit;
        this.vote = 0;
    }
}

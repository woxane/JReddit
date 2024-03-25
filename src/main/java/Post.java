import java.util.ArrayList;

public class Post {
    final String Author;
    final String Content;
    final Subreddit subreddit;
    int vote;
    ArrayList<Comment> comments = new ArrayList<>();

    Post (String username , String content , Subreddit subreddit) {
        this.Author = username;
        this.Content = content;
        this.subreddit = subreddit;
        this.vote = 0;
    }
}

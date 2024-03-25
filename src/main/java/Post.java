import java.util.ArrayList;

public class Post {
    final String Author;
    final String Content;
    int vote;
    ArrayList<Comment> comments = new ArrayList<>();

    Post (String username , String content) {
        this.Author = username;
        this.Content = content;
        this.vote = 0;
    }
}

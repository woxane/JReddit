import java.util.ArrayList;

public class Post {
    final String postAuthor;
    final String postContent;
    int vote;
    ArrayList<Comment> comments = new ArrayList<>();

    Post (String username , String content) {
        this.postAuthor = username;
        this.postContent = content;
        this.vote = 0;
    }
}

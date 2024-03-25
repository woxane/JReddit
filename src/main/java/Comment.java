public class Comment {
    final String Author;
    final String Content;
    int vote;


    Comment (String username , String content) {
        this.Author = username;
        this.Content = content;
        this.vote = 0;
    }
}

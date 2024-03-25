public class Comment {
    final String commentAuthor;
    final String commentContent;
    int vote;


    Comment (String username , String content) {
        this.commentAuthor = username;
        this.commentContent = content;
        this.vote = 0;
    }
}

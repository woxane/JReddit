public class Comment {
    final Account Author;
    final Post post;
    final String Content;
    int vote;


    Comment (Account account , String content , Post post) {
        this.Author = account;
        this.Content = content;
        this.post = post;
        this.vote = 0;
    }
}

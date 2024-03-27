import java.util.ArrayList;

public class Comment {
    final Account author;
    final Post post;
    final String content;
    int vote;
    ArrayList<Vote> votes = new ArrayList<>();

    Comment (Account account , String content , Post post) {
        this.author = account;
        this.content = content;
        this.post = post;
        this.vote = 0;
    }


    public void viewComment() {
        System.out.println("\tu/" + author.username);
        System.out.println("\t\t" + this.content);
        System.out.println("\t" + this.vote + "^");
    }
}

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Comment {
    final Account author;
    final Post post;
    final String content;
    final UUID commentId;
    int vote;
    ArrayList<Vote> votes = new ArrayList<>();

    Comment (Account account , String content , Post post) {
        this.author = account;
        this.content = content;
        this.post = post;
        this.vote = 0;
        this.commentId = UUID.randomUUID();

        try {
            Database.insertComment(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void viewComment() {
        System.out.println("\tu/" + author.username);
        System.out.println("\t\t" + this.content);
        System.out.println("\t" + this.vote + "^");
    }


    public void voteComment(Account account) {
        boolean choose;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you want to vote 1) Yes / 2) No ");

        choose = scanner.nextInt() == 1;

        if (choose) {
            boolean isUpVote;
            System.out.println("1) Down / 2) Up");

            isUpVote = scanner.nextInt() == 2;

            account.vote(this, isUpVote);
            this.author.updateKarma();
        }
        return;
    }
}

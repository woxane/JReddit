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

    }


    public void viewComment() {
        System.out.println("\tu/" + author.username + " : ");
        System.out.println("\t\t" + this.content + " (vote : " + this.vote + ")");
    }


    public void voteComment(Account account) {
        boolean choose;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Want to vote ? : \n1) Yes / 2) No \n: ");

        choose = scanner.nextInt() == 1;
        scanner.nextLine();

        if (choose) {

            if (account.checkVote(this) != null) {
                System.out.println("You already voted\nWant to retract it ? (y/n)");
                boolean answer = scanner.nextLine() == "y";

                if (answer) {
                    this.vote -= account.checkVote(this).isUpVote ? 1 : -1;
                    account.retractVote(this);
                    this.author.updateKarma();
                    System.out.println("Done !");
                }

            }

            boolean isUpVote;
            System.out.println("1) Down / 2) Up");

            isUpVote = scanner.nextInt() == 2;
            scanner.nextLine();

            this.vote += isUpVote ? 1 : -1;
            account.vote(this, isUpVote);
            this.author.updateKarma();
        }
        return;
    }


    public void exploreComment(Account account) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Explore : \n1) Author profile \n Or : \n2) Vote   3) Quit \n: ");
        int exploreOption = scanner.nextInt();
        scanner.nextLine();

        switch (exploreOption) {
            case 1 :
                this.author.viewProfile();
                break;

            case 2 :
                this.voteComment(account);
                break;


            default :
                break;
        }


    }
}

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Post {
    final Account author;
    final String title;
    final String content;
    final Subreddit subreddit;
    final UUID postID;
    int vote;
    ArrayList<Comment> comments = new ArrayList<>();
    ArrayList<Vote> votes = new ArrayList<>();

    Post (Account account , String title, String content , Subreddit subreddit) {
        this.author = account;
        this.title = title;
        this.content = content;
        this.subreddit = subreddit;
        this.vote = 0;
        this.postID = UUID.randomUUID();

    }

    public void viewPost() {
        System.out.println("r/" + this.subreddit.name + " via u/" + this.author.username);
        System.out.println(this.title);
        System.out.println("\n" + this.content);
        System.out.println(this.vote + "^");

        for (Comment comment : comments) {
            comment.viewComment();
        }
    }


    public void explorePost(Account account) {
        Scanner scanner = new Scanner(System.in);
        Comment comment;

        System.out.print("Explore : \n1) This subreddit   2) Author profile    3) Comments\n Or :\n4) Vote    5) Leave a comment     6) Quite");
        int exploreOption = scanner.nextInt();

        switch (exploreOption) {
            case 1 :
                Reddit.postScroller(this.subreddit.posts , account);
                break;

            case 2 :
                this.author.viewProfile();
                return;

            case 3 :
                comment = Reddit.commentScroller(this , account);
                if (comment != null) {
                    comment.voteComment(account);
                }
                return;

            case 4 :
                int vote;

                if (account.checkVote(this)) {
                    System.out.println("You already voted\nWant to retract it ? (y/n)");
                    boolean answer = scanner.nextLine() == "y";

                    if (answer) {
                        account.retractVote(this);
                        this.author.updateKarma();
                        System.out.println("Done !");
                    }

                }

                System.out.print("Vote : \n1) Down / 2) Up / 3) Leave it : ");

                do {
                    vote = scanner.nextInt();

                    if (vote == 1 | vote == 2 | vote == 3) {
                        break;

                    } else {
                        System.out.println("Please choose between 1 or 2 or 3 : ");
                    }
                } while (true);

                if (vote == 3) {
                    return;
                }

                boolean isUpVote = vote == 2;

                boolean status = account.vote(this, isUpVote);
                if (!status) {
                    account.retractVote(this);
                }

                this.author.updateKarma();


            case 5 :
                System.out.print("Enter something : ");
                String content = scanner.nextLine();

                comment = new Comment(account , content , this);
                comments.add(comment);
                account.comments.add(comment);

            default :
                return;
        }

    }
}

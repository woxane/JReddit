import java.sql.SQLException;
import java.util.UUID;

public class Vote {
    Account voter;
    boolean isUpVote;
    final UUID voteID;

    Vote(Account voter , boolean isUpVote) {
        this.voter = voter;
        this.isUpVote = isUpVote;
        this.voteID = UUID.randomUUID();

        try {
            Database.insertVote(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

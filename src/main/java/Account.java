import java.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;

public class Account {
    String name;
    String username;
    String password;
    String emailAddress;
    final UUID accountID;
    int karma;

    Account (String name , String username , String password , String emailAddress) {
        this.name = name;
        this.username = username;
        this.password = DigestUtils.sha256Hex(password);
        this.emailAddress = emailAddress;
        this.accountID = UUID.randomUUID();
    }
}

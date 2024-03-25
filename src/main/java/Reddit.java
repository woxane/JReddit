import java.util.ArrayList;
public class Reddit {
    ArrayList<Subreddit> Subreddits = new ArrayList<>();
    ArrayList<Account> accounts = new ArrayList<>();

    public static boolean emailValidator(String email) {
        String regEx = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regEx);
    }
}

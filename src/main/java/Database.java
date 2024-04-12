import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class Database {
    static Connection connection;
    static Statement statement;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/Reddit.db");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getAllUsernames() throws SQLException {
        ArrayList<String> usernames = new ArrayList<>();
        String username;

        ResultSet resultSet = statement.executeQuery("SELECT username FROM Accounts");

        while (resultSet.next()) {
            username = resultSet.getString("username");
            usernames.add(username);
        }

        return usernames;
    }


    public static ArrayList<String> getAllSubredditNames() throws SQLException {
        ArrayList<String> names = new ArrayList<>();
        String name;

        ResultSet resultSet = statement.executeQuery("SELECT name FROM Subreddit");

        while(resultSet.next()) {
            name = resultSet.getString("name");
            names.add(name);
        }

        return names;

    }

    public static ArrayList<String> getAllEmails() throws SQLException{
        ArrayList<String> emailAddress = new ArrayList<>();
        String email ;

        ResultSet resultSet = statement.executeQuery("SELECT emailAddress FROM Accounts");

        while(resultSet.next()) {
            email = resultSet.getString("emailAddress");
            emailAddress.add(email);
        }

        return emailAddress;
    }

    public static void insertAccount(Account account) throws SQLException {
        String exequte = String.format("INSERT INTO Accounts (name , username , password , emailAddress , karma , accountID , subredditID , commentID , postID) " +
                        "VALUES ('%s' , '%s' , '%s' , '%s' , %d , '%s' , '%s' , '%s' , '%s')" , account.name , account.username , account.password , account.emailAddress , account.karma , account.accountID , "" , "" , "");
        statement.executeUpdate(exequte);
    }
}

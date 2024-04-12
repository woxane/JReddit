import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class Database {
    Connection connection;

    Database() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/Reddit.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAllUsernames() throws SQLException {
        ArrayList<String> usernames = new ArrayList<>();
        String username;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT username from Accounts");

        while (resultSet.next()) {
            username = resultSet.getString("username");
            usernames.add(username);
        }

        return usernames;
    }
}

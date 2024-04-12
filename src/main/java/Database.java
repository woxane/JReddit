import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class Database {
    Connection connection;

    Database() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:Reddit.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

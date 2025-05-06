package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static Connection connection = null;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/KNK_Gr1";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0000";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                System.out.println("Lidhja me databazën u realizua me sukses!");
            } catch (SQLException e) {
                System.out.println("Lidhja me databazën dështoi!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}

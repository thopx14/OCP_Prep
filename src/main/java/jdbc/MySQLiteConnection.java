package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLiteConnection {

    private static final String DB_FILE_NAME = "person.db";

    // Prevent instantiation
    private MySQLiteConnection() {

    }

    public static Connection getConnectionToDatabase() throws SQLException {
        return DriverManager.getConnection( "jdbc:sqlite:" + DB_FILE_NAME );
    }
}

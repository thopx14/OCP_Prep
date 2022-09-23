package jdbc;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLiteConnection {

    private static final String DATABASE = "user.db";
    private static final URL DB_FILE_NAME = MySQLiteConnection.class.getResource( DATABASE );

    // Prevent instantiation
    private MySQLiteConnection() {
    }

    public static Connection getConnectionToDatabase() throws SQLException {
        if ( DB_FILE_NAME == null ) {
            throw new IllegalStateException( "Cannot find " + DATABASE );
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection( "jdbc:sqlite:" + DB_FILE_NAME.toURI() );

        } catch ( URISyntaxException e ) {
            e.printStackTrace();
        }
        return connection;
    }
}

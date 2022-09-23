package jdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User> {

    @Override public List<User> getAll() {
        List<User> userList = null;
        try {
            userList = fetchAllUsers();

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override public Optional<User> get( long id ) {
        if ( id <= 0 ) {
            throw new IllegalArgumentException( "id must be > 0! => " + id );
        }
        User user = null;
        try ( Connection conn = MySQLiteConnection.getConnectionToDatabase();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery( "select * from user where id=" + id ) ) {

            rs.next();
            user = new User(
                    rs.getString( "prename" ),
                    rs.getString( "surname" ),
                    rs.getBytes( "password" ),
                    LocalDate.parse( rs.getString( "dayOfBirth" ) ),
                    rs.getString( "streetName" ),
                    rs.getInt( "houseNumber" ),
                    rs.getInt( "postalCode" ),
                    rs.getString( "city" )
            );
            user.setId( rs.getInt( "id" ) );

        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        return user == null ? Optional.empty() : Optional.of( user );
    }

    @Override public void delete( User user ) {
        String sql = "delete from user where" +
                " prename='" + user.getPrename() + "' AND " +
                " surname='" + user.getSurname() + "' AND " +
                " dayOfBirth='" + user.getDayOfBirth() + "' AND " +
                " streetName='" + user.getStreetName() + "' AND " +
                " houseNumber=" + user.getHouseNumber() + " AND " +
                " postalCode=" + user.getPostalCode() + " AND " +
                " city='" + user.getCity() + "'";
        try {
            updateDB( sql );

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    @Override public void insert( User user ) {
        try {
            if ( fetchUserID( user ) > 0 ) {
                throw new IllegalArgumentException( "User " + user + " already exists" );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        // 1. insert
        String prepSql = "insert into user (" +
                "prename," +
                "surname," +
                "password," +
                "dayOfBirth," +
                "streetName," +
                "houseNumber," +
                "postalCode," +
                "city) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";

        try ( Connection conn = MySQLiteConnection.getConnectionToDatabase();
                PreparedStatement stmt = conn.prepareStatement( prepSql ) ) {

            stmt.setString( 1, user.getPrename() );
            stmt.setString( 2, user.getSurname() );
            stmt.setBytes( 3, user.getPassword() );
            stmt.setString( 4, user.getDayOfBirth().toString() );
            stmt.setString( 5, user.getStreetName() );
            stmt.setInt( 6, user.getHouseNumber() );
            stmt.setInt( 7, user.getPostalCode() );
            stmt.setString( 8, user.getCity() );

            stmt.executeUpdate();
            // 2. get ID for user:
            user.setId( fetchUserID( user ) );

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    private int fetchUserID( User user ) throws SQLException {
        String sql = "select id from user where" +
                " prename='" + user.getPrename() + "' AND " +
                " surname='" + user.getSurname() + "' AND " +
                " dayOfBirth='" + user.getDayOfBirth() + "' AND " +
                " streetName='" + user.getStreetName() + "' AND " +
                " houseNumber=" + user.getHouseNumber() + " AND " +
                " postalCode=" + user.getPostalCode() + " AND " +
                " city='" + user.getCity() + "'";

        try ( Connection conn = MySQLiteConnection.getConnectionToDatabase();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery( sql ) ) {
            rs.next();
            int id = rs.getInt( "id" );

            return id == 0 ? -1 : id;
        }
    }

    private List<User> fetchAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "select * from user";
        try ( Connection conn = MySQLiteConnection.getConnectionToDatabase();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery( sql ) ) {

            while ( rs.next() ) {
                User user = new User(
                        rs.getString( "prename" ),
                        rs.getString( "surname" ),
                        rs.getBytes( "password" ),
                        LocalDate.parse( rs.getString( "dayOfBirth" ) ),
                        rs.getString( "streetName" ),
                        rs.getInt( "houseNumber" ),
                        rs.getInt( "postalCode" ),
                        rs.getString( "city" )
                );
                user.setId( rs.getInt( "id" ) );
                users.add( user );
            }
        }
        return users;
    }

    private void updateDB( String query ) throws SQLException {
        try ( Connection connection = MySQLiteConnection.getConnectionToDatabase();
                Statement stmt = connection.createStatement() ) {

            stmt.executeUpdate( query );
        }
    }
}

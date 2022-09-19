package jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQliteTest {

    public static void main( String[] args ) {

        try ( Connection connectionToDatabase = MySQLiteConnection.getConnectionToDatabase();
                Statement statement = connectionToDatabase.createStatement() ) {

            System.out.println( "Connection established!" );
            createPersonTable( statement );

            System.out.println( "Personen:" );
            System.out.println( "----------------------" );
            printPersonDB( queryPersonDB( statement, "select * from person" ) );
            List<String> names = getNamesFromPersonTable( queryPersonDB( statement, "select name from person" ) );
            updatePasswords( names, connectionToDatabase );
            System.out.println();
            System.out.println( "Personen (nach update):" );
            System.out.println( "----------------------" );
            printPersonDB( queryPersonDB( statement, "select * from person" ) );

            System.out.println( "clear text pws:" );
            System.out.println( "----------------------" );
            ResultSet rs = queryPersonDB( statement, "select * from person" );
            MessageDigest md = MessageDigest.getInstance( "SHA-256" );

            while ( rs.next() ) {
                byte[] hashedPW;
                hashedPW = rs.getBytes( "password" );
                String pw = "abcdefg";
                md.update( pw.getBytes( StandardCharsets.UTF_8 ) );
                byte[] hashComputed = md.digest();

                System.out.printf( "Password from %s matches %s => %s%n",
                        rs.getString( "name" ),
                        pw,
                        Arrays.equals( hashedPW, hashComputed ) );
            }

        } catch ( SQLException | NoSuchAlgorithmException e ) {
            throw new RuntimeException( e );
        }
    }

    static void printPersonDB( ResultSet rs ) throws SQLException {
        while ( rs.next() ) {
            System.out.printf( "id = %d, name = %s, password = %s%n",
                    rs.getInt( "id" ),
                    rs.getString( "name" ),
                    rs.getString( "password" ) );
        }
    }

    static List<String> getNamesFromPersonTable( ResultSet rs ) throws SQLException {
        List<String> names = new ArrayList<>();
        while ( rs.next() ) {
            names.add( rs.getString( "name" ) );
        }

        return names;
    }

    static void createPersonTable( Statement statement ) throws SQLException {
        try ( BufferedReader reader = Files.newBufferedReader( Path.of( "person.sql" ) ) ) {
            String line;
            while ( ( line = reader.readLine() ) != null ) {
                statement.addBatch( line );
            }
            statement.executeBatch();
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }

    static void updatePasswords( List<String> names, Connection connection ) throws SQLException {
        try ( PreparedStatement preparedStatement =
                connection.prepareStatement( "update person set password = ? where name = ?" ) ) {

            MessageDigest md = MessageDigest.getInstance( "SHA-256" );
            md.update( "abcdefg".getBytes( StandardCharsets.UTF_8 ) );
            byte[] hashedPW = md.digest();

            for ( String name : names ) {
                preparedStatement.setBytes( 1, hashedPW );
                preparedStatement.setString( 2, name );
                preparedStatement.executeUpdate();
            }

        } catch ( NoSuchAlgorithmException e ) {
            throw new RuntimeException( e );
        }
    }

    static ResultSet queryPersonDB( Statement statement, String query ) throws SQLException {
        return statement.executeQuery( query );
    }
}

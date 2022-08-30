package io.aufgaben;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SaveLoadArray {
    public static void main( String[] args ) {
        int len = new java.util.Random().nextInt( 100 ) + 1;
        int[] arr1 = createArray( len, -20, 20 );
        System.out.println( "Array der Länge: " + len + ": " + Arrays.toString( arr1 ) );
        saveArray( arr1, "array.txt" );
        int[] arr2 = loadArray( "array.txt" );
        System.out.println( Arrays.toString( arr2 ) );
    }

    public static int[] createArray( int length, int min, int max ) {
        int[] arr = new int[length];
        for ( int i = 0; i < arr.length; i++ ) {
            arr[i] = new Random().nextInt( min, max );
        }
        return arr;
    }

    public static void saveArray( int[] array, String fileName ) {
        try ( BufferedWriter writer = Files.newBufferedWriter( Path.of( fileName ), StandardCharsets.UTF_8 ) ) {

            for ( int i = 0; i < array.length; i++ ) {
                writer.write( String.valueOf( array[i] ) );
                if ( i < array.length - 1 )
                    writer.newLine();
            }

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public static int[] loadArray( String fileName ) {
        int[] arr;
        try {
            List<String> strings = Files.readAllLines( Path.of( fileName ) );
            arr = new int[strings.size()];

            for ( int i = 0; i < strings.size(); i++ ) {
                arr[i] = Integer.parseInt( strings.get( i ) );
            }

        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }

        return arr;
    }
}

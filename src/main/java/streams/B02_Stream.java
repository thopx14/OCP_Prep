package streams;

import java.util.Random;
import java.util.stream.Stream;

public class B02_Stream {

    public static void main( String[] args ) {

        String montag = "Montag ist ein guter Tag";
        System.out.print( montag + " shuffled: " );
        // Verändere den String "Montag" mit zufälligen Buchstaben.
        montag.chars()                              // IntStream
                .mapToObj( i -> ( char ) i )        // Stream<Character>
                .map( B02_Stream::shuffle )         // Stream<Character>
                .forEach( System.out::print );

        System.out.print( "\nRandom text: " );
        Stream.generate( B02_Stream::getRandomASCIICharacter )         // Stream<Character>
                .limit( 30 )
                .forEach( System.out::print );
    }

    static char getRandomASCIICharacter() {
        char randomNr;
        if ( new Random().nextBoolean() ) {
            // Großbuchstaben
            randomNr = ( char ) ( 65 + Math.round( Math.random() * ( 90 - 65 ) ) );
        } else if ( new Random().nextBoolean() ) {
            // Kleinbuchstaben
            randomNr = ( char ) ( 97 + Math.round( Math.random() * ( 122 - 97 ) ) );

        } else {
            // Sonderzeichen
            randomNr = ( char ) ( 64 + Math.round( Math.random() * ( 64 - 35 ) ) );
        }

        return randomNr;
    }

    static char shuffle( char c ) {
        return getRandomASCIICharacter();
    }
}

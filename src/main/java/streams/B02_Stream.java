package streams;

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
        Stream.generate( RandomCharGenerator::getRandomASCIICharacter )         // Stream<Character>
                .limit( 30 )
                .forEach( System.out::print );
    }

    static char shuffle( char c ) {
        return RandomCharGenerator.getRandomASCIICharacter();
    }
}

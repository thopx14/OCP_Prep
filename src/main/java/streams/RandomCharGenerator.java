package streams;

import java.util.Random;

public class RandomCharGenerator {
    
    public static char getRandomASCIICharacter() {
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
}

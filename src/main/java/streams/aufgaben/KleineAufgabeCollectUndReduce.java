package streams.aufgaben;

import streams.RandomCharGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class KleineAufgabeCollectUndReduce {
    public static void main( String[] args ) {

        /*
        Erzeugen einer List<Character> mit Zufallswerten.
         */
        long start = System.currentTimeMillis();
        List<Character> randomChars = Stream.generate( RandomCharGenerator::getRandomASCIICharacter )
//                .parallel()
                .limit( 10_000_000 )
                .collect( ArrayList::new, List::add, List::addAll );
        long end = System.currentTimeMillis();

        System.out.println( "duration (ms): " + ( end - start ) );
    }
}

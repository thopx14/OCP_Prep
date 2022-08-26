package streams.aufgaben;

import java.util.stream.Stream;

public class StreamReduceSimple {
    public static void main( String[] args ) {
        String[] items = { "aa", "bbb", "cccc", "ddddd" };

        /*
        A1
         */
        Integer length = Stream.of( items )
                .map( String::length )
                .reduce( 0, Integer::sum );

        System.out.println( length ); // 14

        /*
        A2
         */
        length = Stream.of( items )         // Stream<String>
                .reduce( 0, ( val, str ) -> val + str.length(), Integer::sum );

        System.out.println( length ); // 14

        /*
        A3
         */
        Integer[] array = { 0, 0, 1, 0, 1, 0 };

        // hier identity, accumulator und combiner definieren
        String s = Stream.of( array )   // Stream<Integer>
                .reduce( "", ( str, val ) -> str + val, String::concat );

        System.out.println( s ); // 001010
    }
}

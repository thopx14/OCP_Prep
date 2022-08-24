package streams.aufgaben;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamIntermediateOperations {

    static class Tier {
        private final String name;

        public Tier( String name ) {
            this.name = name;
        }

        public String toString() {
            return "Tier " + name;
        }

        @Override
        public int hashCode() {
            return Objects.hash( name );
        }

        @Override
        public boolean equals( Object obj ) {
            if ( this == obj )
                return true;
            if ( obj == null )
                return false;
            if ( getClass() != obj.getClass() )
                return false;
            Tier other = ( Tier ) obj;
            return Objects.equals( name, other.name );
        }
    }

    public static void main( String[] args ) {

        /*
         * ###### A1.
         */
//        List<Integer> list = Arrays.asList(13, 15, 17, 19, 21);
//
        System.out.println( "*** A1" );
//        // A
//        for (Integer x : list) {
//            if (x == 15 || x == 19) {
//                System.out.println("Treffer: " + x);
//            }
//        }

        Stream.of( 13, 15, 17, 19, 21 )
                .filter( x -> x == 15 || x == 19 )
                .forEach( x -> System.out.println( "Treffer: " + x ) );
        // B

        /*
         * ###### A2.
         */
        System.out.println( "*** A2" );
//        Integer[] array = { 1, 4, 7, 3, -8 };

        //A
//        for (Integer x : array) {
//            System.out.println( x%2==0 ? "gerade" : "ungerade" );
//        }

        Stream.of( 1, 4, 7, 3, -8 )
                .map( i -> i % 2 == 0 ? "gerade" : "ungerade" )
                .forEach( System.out::println );
        //B

        /*
         * ###### A3.
         */
        System.out.println( "*** A3" );

//        List<String> list = Arrays.asList("Tom", "Jerry", "Rex");
//        
//        //A
//        for(String name : list) {
//            Tier t = new Tier(name);
//            System.out.println(t);
//        }

        Stream.of( "Tom", "Jerry", "Rex" )
                .map( Tier::new )
                .forEach( System.out::println );
        //B    

        /*
         * ###### A4.
         */
        System.out.println( "*** A4" );
        /*
         * Erstellen Sie bitte eine Pipeline,
         *   - die 30 Zufallswerte im Bereich [-20 ... 20] generiert (s. Stream.generate),
         *   - dann die negativen Werte aus dem Bereich [-15 ... -10] ausfiltert (weg wirft),
         *   - dann die durchgekommenen Werte in Double umwandelt
         *   - dann die Double-Werte ausgibt
         *
         *   Falls noch nicht realisiert, ändern Sie bitte die Pipeline so, dass sie immer 30 Ausgaben erzeugt.
         */

        Random random = new Random();

        Supplier<Integer> intSupplier = () -> random.nextInt( -20, 21 );

        AtomicInteger counter = new AtomicInteger();

        Stream.generate( intSupplier ) // Stream<Integer>
                .filter( x -> x < -15 || x > -10 )
                .limit( 30 )
                .map( x -> ( double ) x )
                .forEach( x -> {
                    int nummer = counter.incrementAndGet();
                    System.out.println( nummer + ". " + x );
                } );

//        Stream.iterate( -20, i -> ++i )                   // Stream<Integer>
//                .limit( 41 )                              // Stream<Integer>
//                .filter( x -> !( x >= -15 && x <= -10 ) )   // Stream<Integer>
//                .map( Double::valueOf )                   // Stream<Double>
//                .forEach( System.out::println );

        /*
         * ###### A5.
         */
        System.out.println( "*** A5" );
        // Tier Klasse, siehe oben!
        Tier[] array = {
                new Tier( "Rex" ),
                new Tier( "Tom" ),
                new Tier( "Jerry" ),
                new Tier( "Tom" ),
                new Tier( "Jerry" ),
        };

        Stream.of( array )
                .distinct()
                .forEach( System.out::println );

        /*
         * ###### A6.
         */
        System.out.println( "*** A6" );

        List<String> mailsErsthelfer = Arrays.asList( "tom@mycompany.com", "jerry@mycompany.com" );
        List<String> mailsIT = Arrays.asList( "tom@mycompany.com", "mary@mycompany.com" );
        List<String> mailsQM = Arrays.asList( "peter@mycompany.com", "jerry@mycompany.com", "Hansi" );


        Stream.of( mailsErsthelfer, mailsIT, mailsQM )    // Stream<List<String>>
                .flatMap( List::stream )                  // Stream<String>
                .filter( x -> x.contains( "@" ) )
                .map( s -> s.split( "@" )[0] )              // Stream<String>
                .distinct()
                .forEach( System.out::println );

        /*
        Ausgabe:
        tom
        jerry
        mary
        peter

        "Hansi" taucht nicht auf!
         */

    } // end of main

}// end of StreamIntermediateOperations 

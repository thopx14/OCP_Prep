package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class B04_StreamTerminalOperations {
    public static void main( String[] args ) {

        /*
        Liefert die Summe der Zahlen bis limit(zahl) zurück.
         */
        Optional<Integer> reduce = Stream.iterate( 0, i -> ++i )
                .limit( 55 )
                // Optional<T> reduce(BinaryOperator<T> accumulator);
                //      T apply(T t, T u);
                .reduce( ( a, b ) -> a + b );

        reduce.ifPresent( System.out::println );

        String s1 = "Hallo";
        String s2 = "Welt";

        /*
        Konkateniert zwei Strings
         */
        Stream.of( s1, s2 )                 //Stream<String>
                .reduce( String::concat )   // Optional<String>
                .ifPresent( System.out::println );

        /*
        allMatch:
            Gibt true zurück, sobald alle Werte dem Predicate entsprechen.
         */

        List<Integer> integerList = new ArrayList<>( List.of( 1, 6, 55, 77, 22, 100, 55 ) );

        boolean b = integerList.stream()
//                .peek( System.out::println )
                .allMatch( x -> x > 0 );

        System.out.println( b ); // true

        /*
        anyMatch:
            Gibt true zurück, sobald EIN Element dem Predicate entspricht.
         */

        b = integerList.stream()
//                .peek( System.out::println )
                .anyMatch( x -> x > 0 );

        System.out.println( b ); // true

    }
}

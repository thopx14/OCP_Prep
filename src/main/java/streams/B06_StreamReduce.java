package streams;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class B06_StreamReduce {
    public static void main( String[] args ) {

        String[] arr = { "a", "b", "c", "d", "e", "f", "g" };
        /*
           Optional<T> reduce(BinaryOperator<T> accumulator);
         */

        Optional<String> reduce = Stream.of( arr )
                .reduce( ( s1, s2 ) -> s1 + s2 );

        System.out.print( "reduce1: " );
        reduce.ifPresent( System.out::println );

        /*
            T reduce(T identity, BinaryOperator<T> accumulator);
         */
        String reduce2 = Stream.of( arr )
                .reduce( "!", String::concat );

        System.out.print( "reduce3: " );
        System.out.println( reduce2 ); // !abcdefg

        /*
            <U> U reduce(U identity,
                 BiFunction<U, ? super T, U> accumulator,
                 BinaryOperator<U> combiner);
         */

        String identity = "";
        // accumulator "addiert" String und Integer und gibt sie als String zurück.
        BiFunction<String, Integer, String> accumulator = ( str, value ) -> str + value;
        // combiner "kombiniert" die Elemente aus einer parallel() Operation.
        BinaryOperator<String> combiner = ( s1, s2 ) -> s1 + s2;

        String reduce3 = Stream.of( 1, 2, 3, 4, 5, 6, 7, 8, 99 ) //Stream<Integer>
                .parallel()
                .reduce( identity, accumulator, combiner );

        System.out.print( "reduce4: " );
        System.out.println( reduce3 ); // 1234567899

    }
}

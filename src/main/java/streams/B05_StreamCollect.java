package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class B05_StreamCollect {
    public static void main( String[] args ) {

        /*
        aus java.util.stream.Stream:
             <R> R collect(Supplier<R> supplier,
                      BiConsumer<R, ? super T> accumulator,
                      BiConsumer<R, R> combiner);
         */
        Supplier<List<Integer>> supplier = () -> new ArrayList<>();
        BiConsumer<List<Integer>, Integer> consumer = ( list, value ) -> list.add( value );
        BiConsumer<List<Integer>, List<Integer>> combiner = ( listA, listB ) -> {
            // empty
        };

        List<Integer> collect = Stream.iterate( 0, i -> ++i )
                .limit( 100 )
                .collect( supplier, consumer, combiner );

        System.out.println( collect ); // ArrayList mit Integer Werten von [0 .. 99]

        combiner = ( listA, listB ) -> listA.addAll( listB );
        List<Integer> collectParallel = Stream.iterate( 0, i -> ++i )
                .limit( 100 )
                .collect( supplier, consumer, combiner );

        System.out.println( collectParallel ); // ArrayList mit Integer Werten von [0 .. 99]

        // Kurzform:
        List<Integer> collectShort = Stream.iterate( 0, i -> ++i )
                .limit( 100 )
                .collect( ArrayList::new, ArrayList::add, ArrayList::addAll );

        System.out.println( collectShort ); // ArrayList mit Integer Werten von [0 .. 99]
    }
}

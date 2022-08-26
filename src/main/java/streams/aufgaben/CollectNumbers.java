package streams.aufgaben;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectNumbers {

    public static List<Integer> a1( String[] arr ) {
        return Arrays.stream( arr )
                .flatMap( s -> Arrays.stream( s.split( "," ) ) )
                .map( Integer::valueOf )
                .collect( Collectors.toList() );
    }

    public static List<Integer> a2( String[] arr ) {
        return Arrays.stream( arr )
                .flatMap( s -> Arrays.stream( s.split( "," ) ) )
                .map( Integer::valueOf )
                .filter( x -> x % 2 == 0 )
                .collect( Collectors.toList() );
    }

    public static List<Integer> a3( String[] arr ) {
        /*
        <R> R collect(Supplier<R> supplier,
                  BiConsumer<R, ? super T> accumulator,
                  BiConsumer<R, R> combiner);
         */
//        Supplier<List<Integer>> supp = ArrayList::new;
//        BiConsumer<List<Integer>, Integer> accu = List::add;
//        BiConsumer<List<Integer>, List<Integer>> cmb = List::addAll;

        return Arrays.stream( arr )
                .flatMap( s -> Arrays.stream( s.split( "," ) ) )
                .map( Integer::valueOf )
                .filter( x -> x % 2 == 0 )
                .collect( ArrayList::new, List::add, List::addAll );
    }
}

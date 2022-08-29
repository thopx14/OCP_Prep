package streams.aufgaben;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GroupingByCollector {
    public static void main( String[] args ) {

        String[] array = {
                "aaa", "bb", "aaa", "ccc", "dd", "eeeee", "ffff", "ggggg", "hhhh"
        };

        /*
         * Grouping by Variante 1:
         */

        // classifier
        Function<String, Integer> classifier = String::length;
        // collector
        Collector<String, ?, Map<Integer, List<String>>> collector =
                Collectors.groupingBy( classifier );


        Map<Integer, List<String>> groups = Arrays.stream( array )
                .collect( collector );

        /*
         * BiConsumer<? super K, ? super V> action
         */
        groups.forEach( ( k, v ) -> System.out.println( k + ": " + groups.get( k ) ) );
        /*
         * Ausgabe:
         *  2: [bb, dd]
            3: [aaa, aaa, ccc]
            4: [ffff, hhhh]
            5: [eeeee, ggggg]
         */
        System.out.println();


        /*
         * Grouping by Variante 2:
         */
        
        /*
         * public static <T, K, A, D>
            Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier,
                                          Collector<? super T, A, D> downstream) {
         */

        Function<String, Integer> classifier2 = String::length;
        Collector<String, ?, Set<String>> downstream = Collectors.toSet();

        Collector<String, ?, Map<Integer, Set<String>>> collector2 =
                Collectors.groupingBy( classifier2, downstream );

        Map<Integer, Set<String>> groups2 = Arrays.stream( array )
                .collect( collector2 );

        groups2.forEach( ( k, v ) -> System.out.println( k + ": " + groups2.get( k ) ) );
        /*
         * Ausgabe:
         *  2: [bb, dd]
            3: [aaa, ccc]
            4: [hhhh, ffff]
            5: [eeeee, ggggg]
         * 
         */
        System.out.println();

        /*
         * Kurzform:
         */
        Map<Integer, Set<String>> groups3 = Arrays.stream( array )
                .collect(
                        Collectors.groupingBy(
                                String::length,
                                Collectors.toSet() ) );

        groups3.forEach( ( k, v ) -> System.out.println( k + ": " + groups3.get( k ) ) );
        System.out.println();
        
        /*
         * Grouping by Variante 3:
         */
        
        /*
         * static <T, K, D, A, M extends Map<K, D>>
                Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier,
                    Supplier<M> mapFactory,
                        Collector<? super T, A, D> downstream)

         */

        Supplier<Map<Integer, Set<String>>> supplier = TreeMap::new;
        Function<String, Integer> classifier3 = String::length;
        Collector<String, ?, Set<String>> downstream2 = Collectors.toSet();

        Collector<String, ?, Map<Integer, Set<String>>> coll3 =
                Collectors.groupingBy( classifier3, supplier, downstream2 );

        Map<Integer, Set<String>> groups4 = Arrays.stream( array )
                .collect( coll3 );

        groups3.forEach( ( k, v ) -> System.out.println( k + ": " + groups3.get( k ) ) );
        System.out.println();
    }
}

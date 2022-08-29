package streams;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class B07_GroupingBy {
    public static void main( String[] args ) {

        List<Integer> integerList = List.of( 1, 2, 3, 5, 1, 0, 0, 3, 4, -99, -199, -1 );

        /*
        public static <T, K> Collector<T, ?, Map<K, List<T>>>
            groupingBy(Function<? super T, ? extends K> classifier)
         */
        Function<Integer, String> classifier = i -> {
            if ( i > 0 ) {
                return "positiv";

            } else if ( i == 0 ) {
                return "neutral";
            }

            return "negativ";
        };

        Collector<Integer, ?, Map<String, List<Integer>>> collector
                = Collectors.groupingBy( classifier );

//        Map<String, List<Integer>> gruppen = integerList.stream().collect( Collectors.toCollection( collector ) );
    }
}

package functional.aufgaben;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;

public class CollectionsFunctional {

    public static void main( String[] args ) {

        Collection<String> coll = new ArrayList<>();
        coll.addAll( List.of( "Klaus", "Maus", "Hans", "Haus" ) );

        coll.forEach( System.out::println );
        /*
            Klaus
            Maus
            Hans
            Haus
         */

        List<Integer> integers1 = new ArrayList<>( List.of( 1, 2, 3, 4, 5, 6, 7, 8 ) );
        integers1.removeIf( i -> i % 2 != 0 );
        System.out.println( integers1 ); // [2, 4, 6, 8]

        List<Integer> integers2 = new ArrayList<>( List.of( 1, 2, 3, 4, 5, 6, 7, 8 ) );
        UnaryOperator<Integer> op1 = i -> {
            if ( i % 2 != 0 ) {
                return 0;
            }
            return i;
        };
        integers2.replaceAll( op1 );
        System.out.println( integers2 ); // [0, 2, 0, 4, 0, 6, 0, 8]

        List<Integer> integers3 = new ArrayList<>( List.of( 1, 2, 3, 4, 5, 6, 7, 8 ) );
        Comparator<Integer> cmp1 = new Comparator<Integer>() {
            @Override
            public int compare( Integer o1, Integer o2 ) {
                return o2 - o1;
            }
        };

        integers3.sort( cmp1 );
        System.out.println( integers3 ); // [8, 7, 6, 5, 4, 3, 2, 1]

        integers3 = new ArrayList<>( List.of( 1, 2, 3, 4, 5, 6, 7, 8 ) );
        integers3.sort( ( x, y ) -> y - x );
        System.out.println( integers3 ); // [8, 7, 6, 5, 4, 3, 2, 1]

        integers3 = new ArrayList<>( List.of( 1, 2, 3, 4, 5, 6, 7, 8 ) );
        integers3.sort( Comparator.reverseOrder() );
        System.out.println( integers3 ); // [8, 7, 6, 5, 4, 3, 2, 1]

        integers3 = new ArrayList<>( List.of( 1, 2, 3, 4, 5, 6, 7, 8 ) );
        AtomicInteger ai = new AtomicInteger();

        integers3.forEach( i -> ai.addAndGet( i ) );

        System.out.println( ai.get() ); // 36

        int sum = integers3.stream().mapToInt( i -> i ).sum();
        System.out.println( sum ); // 36

    }
}

package collectionapi.aufgaben;

import java.util.*;

public class KleineAufgabePriorityQueues {
    public static void main( String[] args ) {

        /*
         A1
         */
        Queue<Integer> q = new PriorityQueue<>();
        q.add( 12 );
        q.add( - 5 );
        q.add( 22 );
        q.add( - 2 );
        q.add( 0 );
        q.add( 77 );
        q.add( 12 );

        /*
         A2
         */
        StringJoiner sj = new StringJoiner( ", " );
        while ( ! q.isEmpty() ) {
            sj.add( q.poll() + "" );
        }

        System.out.println( sj );
        System.out.println( "q.size() = " + q.size() ); // 0

        Queue<Integer> q2 = new LinkedList<>( Arrays.asList( 1, 5, 8, 22, 101, 2, 33, 55 ) );
        System.out.println( q2 ); // [1, 5, 8, 22, 101, 2, 33, 55]

        sj = new StringJoiner( ", " );
        while ( ! q2.isEmpty() ) {
            sj.add( q2.remove() + "" );
        }

        System.out.println( sj );
        System.out.println( q2 ); // []
    }
}

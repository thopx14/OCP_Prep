package collectionapi.aufgaben;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

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
        while ( ! q.isEmpty() ) {
            System.out.printf( "%s ", q.poll() ); // -5 -2 0 12 12 22 77
        }

        System.out.println( "\nq.size() = " + q.size() ); // 0

        Queue<Integer> q2 = new LinkedList<>( Arrays.asList( 1, 5, 8, 22, 101, 2, 33, 55 ) );
        System.out.println( q2 ); // [1, 5, 8, 22, 101, 2, 33, 55]

        while ( ! q2.isEmpty() ) {
            System.out.printf( "%d ", q2.remove() ); // 1 5 8 22 101 2 33 55
        }
        System.out.println( "\n" + q2 ); // []
    }
}

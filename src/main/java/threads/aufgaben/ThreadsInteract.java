package threads.aufgaben;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadsInteract {

    // A1
    private static List<List<Integer>> calculateSum( int bound ) throws InterruptedException {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        Runnable r1 = () -> {
            for ( int i = 0; i < 20; i++ ) {
                list1.add( new Random().nextInt( bound ) );
            }
        };

        Runnable r2 = () -> {
            for ( int i = 0; i < 30; i++ ) {
                list2.add( new Random().nextInt( bound ) );
            }
        };

        Thread t1 = new Thread( r1 );
        Thread t2 = new Thread( r2 );

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return new ArrayList<>( List.of( list1, list2 ) );

    }

    private static int count = 0;

    public static void main( String[] args ) throws InterruptedException {

        // ---------------------A1------------------------------
        List<List<Integer>> sums = calculateSum( 100 );

        int sum = sums.stream() // Stream<List<Integer>>
                .flatMap( List::stream ) // Stream<Integer>
                .mapToInt( Integer::intValue )
                .sum();


        System.out.println( "Summe: " + sum );
        // -----------------------------------------------------

        // ---------------------A2------------------------------
        Thread t = new Thread(
                () -> {
                    for ( int i = 0; i < 1_000_000; i++ ) {
                        count++;
                    }
                }
        );
        t.start();
        t.join();
        System.out.println( count ); // 1000000 (ohne t.join() -> 0!)
        // -----------------------------------------------------

        // ---------------------A3------------------------------
        Runnable runnable = () -> {
            while ( !Thread.currentThread().isInterrupted() ) {
                System.out.println( "\uD83D\uDC4B aus " + Thread.currentThread().getName() );

                try {

                    TimeUnit.SECONDS.sleep( 1 );

                } catch ( InterruptedException e ) {
                    System.out.println( "\uD83D\uDE31 ... i'm going to die!" );
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t2 = new Thread( runnable );
        t2.start();

        TimeUnit.SECONDS.sleep( 5 );

        t2.interrupt();
        // -----------------------------------------------------
    }
}

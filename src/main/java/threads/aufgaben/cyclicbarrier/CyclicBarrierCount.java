package threads.aufgaben.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierCount {

    private static int COUNT = 0;

    public static void main( String[] args ) {

        Runnable barrierAction = () -> {
            System.out.println( "Thread " + Thread.currentThread().getName() + " has broken the barrier!" );
            System.out.println( "count = " + COUNT );
        };
        CyclicBarrier cyclicBarrier = new CyclicBarrier( 2, barrierAction );

        Runnable barrierTarget = () -> {
            for ( int i = 0; i < 1_000_000; i++ ) {
                synchronized ( CyclicBarrier.class ) {
                    COUNT++;
                }
            }
            try {
                System.out.println( "Thread " + Thread.currentThread().getName() + " is waiting in front of the barrier" );
                int result = cyclicBarrier.await();
                if ( result == 0 ) {
                    System.out.println( "Thread " + Thread.currentThread().getName() + " arrived as last Thread" );
                }

            } catch ( InterruptedException | BrokenBarrierException e ) {
                throw new RuntimeException( e );
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute( barrierTarget );
        executorService.execute( barrierTarget );
//        executorService.execute( barrierTarget );

        executorService.shutdown();


    }
}

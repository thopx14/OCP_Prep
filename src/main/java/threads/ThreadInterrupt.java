package threads;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt {


    public static void main( String[] args ) {
        Runnable r1 = () -> {
            while ( !Thread.currentThread().isInterrupted() ) {
                System.out.println( Thread.currentThread() ); // Thread[Hallo Thread,5,main]
                try {
                    TimeUnit.SECONDS.sleep( 1 );

                } catch ( InterruptedException interruptedException ) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        Thread t1 = new Thread( r1 );
        t1.setName( "Hallo Thread" );
        t1.start();
        System.out.println( Thread.currentThread() ); // Thread[main,5,main]
        try {
            TimeUnit.SECONDS.sleep( 2 );

        } catch ( InterruptedException e ) {
            throw new RuntimeException( e );
        }
        t1.interrupt();
    }
}

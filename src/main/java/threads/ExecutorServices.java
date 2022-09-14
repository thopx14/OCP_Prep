package threads;

import java.util.concurrent.*;

public class ExecutorServices {
    public static void main( String[] args ) {

        /*
        ScheduledExecutorService
         */
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool( 1 );
        Runnable printer = () -> {
            System.out.println( "Hallo Welt" );
            try {
                Thread.sleep( 200 );

            } catch ( InterruptedException e ) {
                System.out.println( "Interrupted!! Going to die now!" );
            }
        };
        ScheduledFuture<?> future =
                scheduler.scheduleAtFixedRate( printer, 0, 1, TimeUnit.SECONDS );

        /*
        Beendet aber nicht den kompletten ScheduledExecutorService:
         */
//        Runnable cancelTask = () -> future.cancel( false );
//        scheduler.schedule( cancelTask, 10, TimeUnit.SECONDS );

        scheduler.scheduleAtFixedRate( () -> Thread.currentThread().interrupt(), 0, 10, TimeUnit.MILLISECONDS );
        /*
        Hier wird der ScheduledExecutorService beendet (komplett).
         */
        try {
            if ( !scheduler.awaitTermination( 10, TimeUnit.SECONDS ) ) {
                scheduler.shutdownNow();
            }

        } catch ( InterruptedException e ) {
            scheduler.shutdownNow();
        }

        /*
        ExecutorService mit Callable<?>
         */
        ExecutorService scheduler2 = Executors.newScheduledThreadPool( 3 );
        Callable<String> c1 = () -> "Huhu";
        Future<String> f1 = scheduler2.submit( c1 );

        try {
            String whatToGet = f1.get();
            System.out.println( whatToGet );

        } catch ( InterruptedException | ExecutionException e ) {
            e.printStackTrace();
            /*
            java.util.concurrent.ExecutionException: java.lang.ArithmeticException: / by zero
                at java.base/java.util.concurrent.FutureTask.report(FutureTask.java:122)
                at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:191)
                at threads.ExecutorServices.main(ExecutorServices.java:44)
             */
        }

        scheduler2.shutdown();

    }
}

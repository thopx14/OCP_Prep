package threads;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutors {
    public static void main( String[] args ) {
        int cpus = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool( cpus );

        Runnable r1 = () -> {
            System.out.println( "Fixed rate: " + LocalTime.now() + ": " + Thread.currentThread().getName() );
            ThreadUtils.pauseSeconds( 2 );
        };
        /*
        Task wird alle x TimeUnits ausgeführt, egal ob der Task beendet wurde, oder nicht.
         */
        executor.scheduleAtFixedRate( r1, 0, 1, TimeUnit.SECONDS );
        /*
       Fixed rate: 12:41:06.875746200: pool-1-thread-1 --> 2 sec.
        Fixed rate: 12:41:08.894122900: pool-1-thread-1 --> 2 sec.
        Fixed rate: 12:41:10.907823: pool-1-thread-2 --> 2 sec.
        Fixed rate: 12:41:12.908831700: pool-1-thread-1 --> 2 sec.
        Fixed rate: 12:41:14.923219400: pool-1-thread-3 --> 2 sec.
         */

        try {
            executor.awaitTermination( 10, TimeUnit.SECONDS );
            executor.shutdown();

        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        Runnable r2 = () -> {
            System.out.println( "Fixed delay: " + LocalTime.now() + ": " + Thread.currentThread().getName() );
            ThreadUtils.pauseSeconds( 2 );
        };
        executor = Executors.newScheduledThreadPool( cpus );
        /*
        Task wird ausgeführt und es wird am Ende des Tasks X TimeUnits gewartet,
        bis der nächste Task gestartet wird!
         */
        executor.scheduleWithFixedDelay( r2, 0, 1, TimeUnit.SECONDS );
        /*
        Fixed delay: 12:41:16.867816600: pool-2-thread-1 --> > 2 sec.
        Fixed delay: 12:41:19.877128900: pool-2-thread-1 --> > 2 sec.
        Fixed delay: 12:41:22.879976500: pool-2-thread-2 --> > 2 sec.
        Fixed delay: 12:41:25.897396400: pool-2-thread-1 --> > 2 sec.
         */
        try {
            executor.awaitTermination( 10, TimeUnit.SECONDS );
            executor.shutdown();

        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
}

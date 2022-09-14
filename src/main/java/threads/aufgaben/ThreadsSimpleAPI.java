package threads.aufgaben;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ThreadsSimpleAPI {
    public static void main( String[] args ) {
        // A3
        generate37Threads();
        // A4
        generate26Threads();
        // A5
        Thread tom = new Thread( ThreadsSimpleAPI::bigJob, "Tom" );
        tom.setPriority( Thread.MIN_PRIORITY );
        Thread jerry = new Thread( ThreadsSimpleAPI::bigJob, "Jerry" );
        jerry.setPriority( Thread.MAX_PRIORITY );

        tom.start();
        jerry.start();
        // Ausgaben:
        // Thread: Jerry, Zeit: 5.624 Sek. / 0
        // Thread: Tom, Zeit: 5.689 Sek. / 0
    }

    static void generate37Threads() {
        /*
        Generate 37 threads in state 'NEW'.
         */
        Runnable target = () -> System.out.println( Thread.currentThread().getId() );

        List<Thread> threadList = Stream
                .generate( () -> target )
                .limit( 37 )
                .map( Thread::new )
                .toList();

        //  Setting state to 'RUNNABLE'
        threadList.forEach( Thread::start );
    }

    static void generate26Threads() {
        final char c = 'A';
        for ( int i = c; i <= 25 + c; i++ ) {
            final char ch = ( char ) i;
            new Thread( () -> System.out.println( ch ) ).start();
        }
    }

    public static void bigJob() {
        Thread th = Thread.currentThread();
        System.out.println( "Starte " + th.getName()
                + ". Priorität: " + th.getPriority() );

        long start = System.currentTimeMillis();

        int exists = 0;
        for ( int i = 0; i < 100_000; i++ ) {
            Path path = Paths.get( "./" + i );
            if ( Files.exists( path ) ) {
                exists++;
            }
        }

        long ende = System.currentTimeMillis();

        System.out.println( "Thread: " + th.getName()
                + ", Zeit: " + ( ende - start ) / 1000. + " Sek. / " + exists );
    }
}

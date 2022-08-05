package classdesign.designpattern;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTest {

    public static void main( String[] args ) {

        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            Singleton s = Singleton.getInstance();
            System.out.println( Singleton.getInstanceCount() ); // 1

        };

        executor.execute( r1 );
        executor.execute( r1 );
        executor.execute( r1 );

        executor.shutdown();
    }

}

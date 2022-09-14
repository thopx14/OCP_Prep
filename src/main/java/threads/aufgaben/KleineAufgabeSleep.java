package threads.aufgaben;

import java.util.concurrent.TimeUnit;

public class KleineAufgabeSleep {
    public static void main( String[] args ) {

        new Thread( () ->
        {
            for ( int i = 1; i <= 20; i++ ) {
                System.out.println( i );

                try {
                    TimeUnit.SECONDS.sleep( 1 );

                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }
        ).start();

    }
}

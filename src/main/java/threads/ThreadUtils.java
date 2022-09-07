package threads;

import java.util.concurrent.TimeUnit;

public class ThreadUtils {

    public static void pauseSeconds( int seconds ) {
        try {
            TimeUnit.SECONDS.sleep( seconds );

        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    public static void pauseMillis( int millis ) {
        try {
            Thread.sleep( millis );

        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
}

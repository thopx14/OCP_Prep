package threads.aufgaben.wetterstation;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class WheatherStation {

    private final BlockingDeque<Integer> data;
    private int lastVal;
    private final int grenzWertWarm;
    private final int grenzWertKalt;

    public WheatherStation( int runFor, TimeUnit unit ) {
        this.data = new LinkedBlockingDeque<>();
        this.lastVal = 10;
        this.grenzWertWarm = 30;
        this.grenzWertKalt = 4;

        long cpus = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool( ( int ) cpus );
        executor.scheduleAtFixedRate( this::readWetterDaten, 0, 100, TimeUnit.MILLISECONDS );
        executor.scheduleAtFixedRate( this::analyzeWetterDaten, 0, 1, TimeUnit.SECONDS );
        executor.scheduleAtFixedRate( this::checkGrenzWerte, 0, 2, TimeUnit.SECONDS );

        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/ExecutorService.html
        try {
            if ( !executor.awaitTermination( runFor, unit ) ) {
                executor.shutdownNow();
                if ( !executor.awaitTermination( runFor, unit ) ) {
                    System.err.println( "Pool " + executor + " cannot terminate!" );
                }
            }
        } catch ( InterruptedException e ) {
            executor.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    private void readWetterDaten() {
        Random r = new Random();
        int newVal = r.nextBoolean() ? 5 : -5;
        int randomTemp;
        int bound = lastVal + newVal;
        if ( bound <= lastVal ) {
            randomTemp = new Random().nextInt( bound, lastVal );
        } else {
            randomTemp = new Random().nextInt( lastVal, bound );
        }

        System.out.println( "Neue Temperatur gelesen: " + randomTemp );
        try {
            data.put( randomTemp );

        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        lastVal = randomTemp;
    }

    private void analyzeWetterDaten() {
        int[] last3Temps = new int[3];
        for ( int i = 0; i < 3; i++ ) {
            try {
                last3Temps[i] = data.take();

            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }
        boolean tempFall = last3Temps[0] < last3Temps[1] && last3Temps[1] < last3Temps[2];
        if ( tempFall ) {
            System.out.println( "Temperaturabfall!!!! " + Arrays.toString( last3Temps ) );
        }
    }

    private void checkGrenzWerte() {
        try {
            int currTemp = data.take();
            if ( currTemp >= grenzWertWarm || currTemp <= grenzWertKalt ) {
                System.out.println( "Grenzwert erreicht : " + currTemp );
            }
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
}

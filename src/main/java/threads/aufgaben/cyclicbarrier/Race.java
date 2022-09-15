package threads.aufgaben.cyclicbarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Race {
    private static final int HOW_MANY_CARS = 6;

    public static void main( String[] args ) {

        final List<RaceCar> finishList = new CopyOnWriteArrayList<>();
        final List<Callable<Object>> carsToRace = new ArrayList<>();


        Runnable barrierAction = () -> {
            System.out.println( "Finish \uD83C\uDFC1 \uD83C\uDFC1 \uD83C\uDFC1" );
            for ( int i = 0; i < finishList.size(); i++ ) {
                System.out.printf( "%2d. Platz: %5s%n", i + 1, finishList.get( i ) );
            }
        };

        CyclicBarrier barrier = new CyclicBarrier( HOW_MANY_CARS, barrierAction );

        for ( int i = 1; i < HOW_MANY_CARS + 1; i++ ) {
            RaceCar rc = new RaceCar( ( "Race Car " + i ), finishList, barrier );
            carsToRace.add( Executors.callable( rc ) );
        }

        ExecutorService executorService = Executors.newFixedThreadPool( HOW_MANY_CARS );
        try {
            executorService.invokeAll( carsToRace );

        } catch ( InterruptedException e ) {
            throw new RuntimeException( e );
        }


        executorService.shutdown();


    }
}

package threads.aufgaben;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class ExecutorServiceSimple {
    public static void main( String[] args ) throws InterruptedException, ExecutionException {
        // A1.
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute( ExecutorServiceSimple::printRandomDoubles );

        //A2.
        Future<Double> result = service.submit( ExecutorServiceSimple::getRandomDoubles );
        System.out.println( "A2: sum: " + result.get() );

        //A3
        Collection<Callable<Integer>> callables = new ArrayList<>();
        for ( int i = 0; i < 100; i++ ) {
            callables.add( () -> new Random().nextInt( 100 ) );
        }
        List<Future<Integer>> futureList = service.invokeAll( callables, 10, TimeUnit.SECONDS );
        int sumOfFutures = getSumOfFutureList( futureList );
        System.out.println( "Sum of futures: " + sumOfFutures );

        service.shutdown();
    }

    static int getSumOfFutureList( List<Future<Integer>> futureList ) throws ExecutionException, InterruptedException {
        int sum = 0;
        for ( Future<Integer> future : futureList ) {
            sum += future.get();
        }
        return sum;
    }

    static void printRandomDoubles() {
        double randomSeedVal = new Random().nextDouble() * new Random().nextInt( 100 );
        Stream.iterate( randomSeedVal, s -> new Random().nextDouble() * randomSeedVal )
                .limit( 10 )
                .forEach( System.out::println );
    }

    static double getRandomDoubles() {
        double randomSeedVal = new Random().nextDouble() * new Random().nextInt( 100 );
        return Stream.iterate( randomSeedVal, s -> new Random().nextDouble() * randomSeedVal )
                .limit( 10 )
                .mapToDouble( d -> d )
                .sum();
    }
}

package threads.aufgaben;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

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
            callables.add( () -> ThreadLocalRandom.current().nextInt( 100 ) );
        }
        List<Future<Integer>> futureList = service.invokeAll( callables, 10, TimeUnit.SECONDS );
        System.out.println( "Sum of futures: " + getSumOfFutureList( futureList ) );

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
        ThreadLocalRandom.current().doubles()
                .limit( 10 )
                .forEach( System.out::println );
    }

    static double getRandomDoubles() {
        return ThreadLocalRandom.current().doubles()
                .limit( 10 )
                .sum();
    }
}

package threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class ThreadsMitCollections {

    //    private static List<Integer> list = Collections.synchronizedList( new ArrayList<>() );
    private static final List<Integer> list = new ArrayList<>();

    public static void main( String[] args ) {

        System.out.println( "old size: " + list.size() ); // 0
        List<Integer> tempList = Stream.iterate( 0, i -> ++i )
                .limit( 1_000_000 )
                .toList();

        ExecutorService service = Executors.newFixedThreadPool( 3 );
        service.execute( () -> addElementsTo( list, Arrays.asList( 1, 2, 3, 4 ) ) );
        service.execute( () -> addElementsTo( list, tempList ) );
        Future<Integer> f2 = service.submit( () -> addElementsTo( list, tempList ) );

        try {
            int newSize = f2.get();
            System.out.println( "new size: " + newSize );

        } catch ( InterruptedException | ExecutionException e ) {
            e.printStackTrace();
        }
        service.shutdown();
    }

    static synchronized int addElementsTo( Collection<? super Integer> c, List<Integer> elements ) {
        for ( Integer element : elements ) {
            c.add( element );
        }
        return c.size();
    }
}

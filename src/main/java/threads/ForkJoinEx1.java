package threads;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinEx1 {

    static class CountStringLenTask extends RecursiveTask<Long> {

        private static final int THRESHOLD = 2;
        private final List<String> list;
        private final int indexFrom, indexTo;

        public CountStringLenTask( List<String> list, int indexFrom, int indexTo ) {
            this.list = list;
            this.indexFrom = indexFrom;
            this.indexTo = indexTo;
        }

        @Override protected Long compute() {

            System.out.println( "compute() -> " + Thread.currentThread().getName() );
            if ( ( indexTo - indexFrom ) < THRESHOLD ) {
                List<String> sublist = list.subList( indexFrom, indexTo );
                return sublist.stream().flatMapToInt( String::chars ).count();

            } else {
                int mid = ( indexFrom + indexTo ) / 2;
                CountStringLenTask taskA = new CountStringLenTask( list, indexFrom, mid );
                CountStringLenTask taskB = new CountStringLenTask( list, mid, indexTo );
                taskB.fork();
                Long resultA = taskA.compute();
                Long resultB = taskB.join();

                return resultA + resultB;
            }
        }
    }

    public static void main( String[] args ) {

        List<String> stringList = List.of( "Hallo", "Welt", "Welt", "Bbla", "Blubb" );
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long cnt = forkJoinPool.invoke( new CountStringLenTask( stringList, 0, stringList.size() ) );
        System.out.print( "Anzahl Buchstaben in: " + stringList + ": " + cnt );

    }
}

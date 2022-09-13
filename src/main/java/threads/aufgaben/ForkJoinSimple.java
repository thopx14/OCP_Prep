package threads.aufgaben;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinSimple {

    static class SplitterAction extends RecursiveAction {
        private static final int THRESHOLD = 3;
        private final int[] numbers;
        private final int start;
        private final int stop;

        public SplitterAction( int start, int stop ) {
            numbers = new int[stop + 1];
            for ( int i = start; i <= stop; i++ ) {
                numbers[i] = i;
            }
            this.start = start;
            this.stop = stop;
        }

        private SplitterAction( int[] numbers, int start, int stop ) {
            this.numbers = numbers;
            this.start = start;
            this.stop = stop;
        }

        @Override protected void compute() {
            if ( ( stop - start ) < THRESHOLD ) {
                System.out.printf( "Unterbereich %d ... %d%n", numbers[start], numbers[stop] );

            } else {
                int mid = ( start + stop ) / 2;
                RecursiveAction left = new SplitterAction( numbers, start, mid );
                RecursiveAction right = new SplitterAction( numbers, mid, stop );
                invokeAll( left, right );
            }

        }
    }

    public static void main( String[] args ) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke( new SplitterAction( 1, 9 ) );
        /*
        Unterbereich 1 ... 3
        Unterbereich 7 ... 9
        Unterbereich 3 ... 5
        Unterbereich 5 ... 7
         */
    }
}

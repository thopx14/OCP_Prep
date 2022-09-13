package threads.aufgaben;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinMitArray {
    private static final long THRESHOLD = Runtime.getRuntime().availableProcessors();

    static class EliminateNegativeAction extends RecursiveAction {
        private final int[] numbers;
        private final int start;
        private final int stop;

        public EliminateNegativeAction( int[] numbers, int start, int stop ) {
            this.numbers = numbers;
            this.start = start;
            this.stop = stop;
        }

        @Override protected void compute() {
            if ( ( stop - start ) < THRESHOLD ) {
                for ( int i = start; i < stop; i++ ) {
                    if ( numbers[i] < 0 ) {
                        numbers[i] = 0;
                    }
                }
            } else {
                int mid = ( start + stop ) / 2;
                RecursiveAction left = new EliminateNegativeAction( numbers, start, mid );
                RecursiveAction right = new EliminateNegativeAction( numbers, mid, stop );

                invokeAll( left, right );
            }
        }
    }

    static class CountNegativValues extends RecursiveTask<Integer> {
        private final int[] numbers;
        private final int start;
        private final int stop;

        public CountNegativValues( int[] numbers, int start, int stop ) {
            this.numbers = numbers;
            this.start = start;
            this.stop = stop;
        }

        @Override protected Integer compute() {
            if ( ( stop - start ) < THRESHOLD ) {
                int cnt = 0;
                for ( int i = start; i < stop; i++ ) {
                    if ( numbers[i] < 0 ) {
                        cnt++;
                    }
                }
                return cnt;

            } else {
                int mid = ( start + stop ) / 2;
                CountNegativValues taskA = new CountNegativValues( numbers, start, mid );
                CountNegativValues taskB = new CountNegativValues( numbers, mid, stop );

                taskB.fork();
                Integer resultA = taskA.compute();
                Integer resultB = taskB.join();

                return resultA + resultB;
            }

        }
    }

    public static void main( String[] args ) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] arr = generateRandomArray( -50, 50, 10 );
        System.out.println( Arrays.toString( arr ) );

        //A1.
        RecursiveAction eliminateNegativeAction = new EliminateNegativeAction( arr, 0, arr.length );
        forkJoinPool.invoke( eliminateNegativeAction );
        System.out.println( Arrays.toString( arr ) );

        //A2.
        arr = generateRandomArray( -50, 50, 10 );
        System.out.println( Arrays.toString( arr ) );
        int howManyNegatives = forkJoinPool.invoke( new CountNegativValues( arr, 0, arr.length ) );
        System.out.println( "Negativ values in array: " + howManyNegatives );

    }

    static int[] generateRandomArray( int from, int to, int newSize ) {
        if ( to <= from ) {
            throw new IllegalArgumentException( to + "<=" + from );
        }
        int[] arr = new int[newSize];
        for ( int i = 0; i < newSize; i++ ) {
            arr[i] = new Random().nextInt( from, to );
        }
        return arr;
    }
}

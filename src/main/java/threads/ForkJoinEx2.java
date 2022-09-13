package threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinEx2 {

    static class ReplaceAction extends RecursiveAction {
        private final List<String> list;
        private final String replaceWith;
        private final String toReplace;
        private final int indexFrom;
        private final int indexTo;
        private static final int TRESHOLD = 2;


        public ReplaceAction( List<String> list, String replaceWith, String toReplace, int indexFrom, int indexTo ) {
            this.list = list;
            this.replaceWith = replaceWith;
            this.toReplace = toReplace;
            this.indexFrom = indexFrom;
            this.indexTo = indexTo;
        }

        @Override protected void compute() {
            System.out.println( "compute() -> " + Thread.currentThread().getName() );
            if ( ( indexTo - indexFrom ) < TRESHOLD ) {
                for ( int i = indexFrom; i < indexTo; i++ ) {
                    String replaced = list.get( i ).replaceAll( toReplace, replaceWith );
                    list.set( i, replaced );
                }

            } else {
                int mid = ( indexFrom + indexTo ) / 2;
                RecursiveAction left = new ReplaceAction( list, replaceWith, toReplace, indexFrom, mid );
                RecursiveAction right = new ReplaceAction( list, replaceWith, toReplace, mid, indexTo );

                invokeAll( left, right );
            }
        }
    }

    public static void main( String[] args ) {

        List<String> list = Arrays.asList( "Hallo", "Daniel", "Amaranth", "Klaus", "Bärbel" );
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke( new ReplaceAction( list, "o", "a", 0, list.size() ) );

        System.out.println( list );
    }
}

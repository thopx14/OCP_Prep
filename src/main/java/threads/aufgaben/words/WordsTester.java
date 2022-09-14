package threads.aufgaben.words;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class WordsTester<T> {

    private final List<String> words;
    private ExecutorService executorService;
    private final ForkJoinPool pool;
    private @Getter T result;
    private @Getter List<T> resultMultiThreaded;

    private static class GreatestStringTask extends RecursiveTask<String> {
        private final static int THRESHOLD = 500;
        private final List<String> list;

        public GreatestStringTask( List<String> list ) {
            this.list = list;
        }

        @Override protected String compute() {
            if ( list.size() < THRESHOLD ) {
                return list.stream().max( Comparator.naturalOrder() ).get();

            } else {
                int mid = ( list.size() ) / 2;
                GreatestStringTask taskA = new GreatestStringTask( list.subList( 0, mid ) );
                GreatestStringTask taskB = new GreatestStringTask( list.subList( mid, list.size() ) );

                taskB.fork();
                String resultA = taskA.compute();
                String resultB = taskB.join();

                return resultA.compareTo( resultB ) < 0 ? resultB : resultA;
            }
        }
    }

    private static class ToUpperCaseTask extends RecursiveAction {
        private final static int THRESHOLD = 500;
        private final @Getter List<String> resultList;

        public ToUpperCaseTask( List<String> list ) {
            this.resultList = list;
        }

        @Override protected void compute() {
            if ( resultList.size() < THRESHOLD ) {
                resultList.replaceAll( String::toUpperCase );

            } else {
                int mid = ( resultList.size() ) / 2;
                invokeAll( new ToUpperCaseTask( resultList.subList( 0, mid ) ),
                        new ToUpperCaseTask( resultList.subList( mid, resultList.size() ) ) );
            }
        }
    }

    public WordsTester( ExecutorService executorService ) {
        this.words = null;
        this.pool = null;
        this.executorService = executorService;
        resultMultiThreaded = new ArrayList<>();
    }

    public WordsTester( List<String> words ) {
        this.words = words;
        this.pool = new ForkJoinPool();
    }

    public void checkWordsWithCondition( Callable<T> callable ) {
        try {
            result = executorService.submit( callable ).get();

        } catch ( InterruptedException | ExecutionException e ) {
            throw new RuntimeException( e );
        }
    }

    public void checkWordsWithConditionMultiThreaded( Collection<Callable<T>> callable ) {
        try {
            List<Future<T>> futures = executorService.invokeAll( callable );
            resultMultiThreaded.clear();
            for ( Future<T> future : futures ) {
                resultMultiThreaded.add( future.get() );
            }

        } catch ( InterruptedException | ExecutionException e ) {
            throw new RuntimeException( e );
        }
    }

    public String getGreatestString() {
        return pool.invoke( new GreatestStringTask( words ) );
    }

    public List<String> toUpperCaseList() {
        ToUpperCaseTask toUpperCase = new ToUpperCaseTask( words );
        pool.invoke( toUpperCase );
        return toUpperCase.getResultList();
    }
}

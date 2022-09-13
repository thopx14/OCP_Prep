package threads.aufgaben.words;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class WordsTester<T> {

    private ExecutorService executorService;
    private @Getter T result;
    private @Getter List<T> resultMultiThreaded;

    public WordsTester( ExecutorService executorService ) {
        this.executorService = executorService;
        resultMultiThreaded = new ArrayList<>();
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
}

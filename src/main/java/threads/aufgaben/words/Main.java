package threads.aufgaben.words;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main( String[] args ) {

        List<String> englishWords = Words.englishWords();
        System.out.println( "Words.size() = " + englishWords.size() ); // 58110

        ExecutorService executorService = Executors.newCachedThreadPool();
        WordsTester<Long> wordsTester = new WordsTester<>( executorService );

        Callable<Long> callableWordsContainingT = () ->
                englishWords.stream()
                        .filter( w -> w.contains( "t" ) )
                        .count();


        Callable<Long> callableContainsOO = () -> englishWords.stream()
                .filter( w -> w.contains( "oo" ) )
                .count();

        Callable<Long> callableLenFive = () -> englishWords.stream()
                .filter( w -> w.length() == 5 )
                .count();

        wordsTester.checkWordsWithCondition( callableWordsContainingT );
        System.out.println( "Words containing 't': " + wordsTester.getResult() );
        wordsTester.checkWordsWithCondition( callableContainsOO );
        System.out.println( "Words containing 'oo': " + wordsTester.getResult() );
        wordsTester.checkWordsWithCondition( callableLenFive );
        System.out.println( "Words with 5 letters: " + wordsTester.getResult() );

        List<Callable<Long>> callableList = new ArrayList<>();
        int howMany = 50;
        int delta = englishWords.size() / howMany;
        for ( int indexFrom = 0; indexFrom < englishWords.size(); indexFrom += delta ) {
            int indexTo = indexFrom + delta;
            if ( indexTo > englishWords.size() ) {
                indexTo = englishWords.size();
            }
            List<String> sublist = englishWords.subList( indexFrom, indexTo );
            callableList.add( () ->
                    sublist.stream()
                            .filter( s -> s.length() == 5 )
                            .count()
            );
        }

        wordsTester.checkWordsWithConditionMultiThreaded( callableList );
        List<Long> resultMultiThreaded = wordsTester.getResultMultiThreaded();
        long sum = resultMultiThreaded.stream().mapToLong( l -> l ).sum();
        System.out.println( "Words with 5 letters (multithreaded): " + sum );


        executorService.shutdown();
    }
}

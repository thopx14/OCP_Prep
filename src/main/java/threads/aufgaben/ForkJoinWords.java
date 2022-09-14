package threads.aufgaben;

import io.aufgaben.FileUtils;
import threads.aufgaben.words.Words;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinWords {
    private static final long THRESHOLD = Runtime.getRuntime().availableProcessors();

    static class ToUpperAction extends RecursiveAction {
        private final List<String> words;
        private final int start;
        private final int stop;

        public ToUpperAction( List<String> words, int start, int stop ) {
            this.words = words;
            this.start = start;
            this.stop = stop;
        }

        @Override protected void compute() {
            if ( ( stop - start ) < THRESHOLD ) {
                List<String> sublist = words.subList( start, stop );
                sublist.replaceAll( String::toUpperCase );

            } else {
                int mid = ( start + stop ) / 2;
                RecursiveAction left = new ToUpperAction( words, start, mid );
                RecursiveAction right = new ToUpperAction( words, mid, stop );

                invokeAll( left, right );
            }
        }
    }

    static class GreatesStringTask extends RecursiveTask<String> {
        private final List<String> words;
        private final int start;
        private final int stop;

        public GreatesStringTask( List<String> words, int start, int stop ) {
            this.words = words;
            this.start = start;
            this.stop = stop;
        }

        @Override protected String compute() {
            if ( ( stop - start ) < THRESHOLD ) {

                List<String> sublist = words.subList( start, stop );
                String max = "";
                for ( String s : sublist ) {
                    max = max.compareTo( s ) < 0 ? s : max;
                }
                return max;

            } else {
                int mid = ( start + stop ) / 2;
                GreatesStringTask taskA = new GreatesStringTask( words, start, mid );
                GreatesStringTask taskB = new GreatesStringTask( words, mid, stop );

                taskB.fork();
                String resultA = taskA.compute();
                String resultB = taskB.join();

                return resultA.compareTo( resultB ) < 0 ? resultB : resultA;
            }
        }
    }

    public static void main( String[] args ) {

        List<String> words = Words.englishWords();
        FileUtils.safeToFile( words, Path.of( "words_orig.txt" ), false );

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        RecursiveAction upperCaseAction = new ToUpperAction( words, 0, words.size() );
        forkJoinPool.invoke( upperCaseAction );

        FileUtils.safeToFile( words, Path.of( "words_upper.txt" ), false );

        words = Words.englishWords();
        String greatestString = getResult( forkJoinPool, new GreatesStringTask( words, 0, words.size() ) );
        System.out.println( "Lexikographisch größter String: " + greatestString ); //zulus
    }

    static <T> T getResult( ForkJoinPool pool, ForkJoinTask<T> task ) {
        return pool.invoke( task );
    }

}

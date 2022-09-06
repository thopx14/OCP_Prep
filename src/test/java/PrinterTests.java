import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import threads.aufgaben.Printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrinterTests {

    /*
   https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    */
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;


    @BeforeEach
    void setUpStreams() {
        System.setOut( new PrintStream( outContent ) );
        System.setErr( new PrintStream( errContent ) );
    }

    @Test
    public void testPrinterSingleThreadPrintLines() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> future = executorService.submit(
                new Printer( 'a', 10, 5 ).getRunnable() );
        try {
            future.get(); // Blockiert solange bis Runnable beendet wurde!

        } catch ( InterruptedException | ExecutionException e ) {
            executorService.shutdown(); // Beende den ExecutorService (oder warte 60 sec.)
            Assertions.fail( e.getMessage() );
        }
        executorService.shutdown(); // Beende den ExecutorService (oder warte 60 sec.)

        StringBuilder expected =
                new StringBuilder( "aaaaaaaaaa" )
                        .append( System.lineSeparator() )
                        .append( "aaaaaaaaaa" )
                        .append( System.lineSeparator() )
                        .append( "aaaaaaaaaa" )
                        .append( System.lineSeparator() )
                        .append( "aaaaaaaaaa" )
                        .append( System.lineSeparator() )
                        .append( "aaaaaaaaaa" )
                        .append( System.lineSeparator() );

        Assertions.assertEquals( expected.toString(), outContent.toString() );

    }

    @AfterAll
    static void restoreStreams() {
        System.setOut( originalOut );
        System.setErr( originalErr );
    }
}

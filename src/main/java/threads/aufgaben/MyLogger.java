package threads.aufgaben;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class MyLogger {

    private final StringBuilder sb = new StringBuilder();
//    private final StringBuffer sb = new StringBuffer();

    /*
    Mit 'synchronized' kein Problem!

    Wenn man StringBuffer einsetzt funktioniert die Synchronisation trotzdem nicht.
    StringBuffer's append Methode ist zwar synchronisiert, aber der eine Thread ruft
    append(caller) auf während der zweite Thread append(caller) aufruft und dann der erste
    wieder append(message) usw... Die umschließende Methode sollte synchronisiert werden,
    ansonsten wird's nicht funktionieren!!!
     */
    public synchronized void addMessage( String caller, String message ) {
        sb.append( caller )
                .append( " - " )
                .append( message )
                .append( "\n" );
    }

    public String getLog() {
        return sb.toString();
    }

    public static void main( String[] args ) throws InterruptedException {
        MyLogger logger = new MyLogger();

        Runnable r1 = () -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.MEDIUM );
            String localDateTime = LocalDateTime.now().format( formatter );
            for ( int i = 0; i < localDateTime.length(); i++ ) {
                logger.addMessage( Thread.currentThread().getName(), localDateTime );
            }
        };

        Thread t1 = new Thread( r1 );
        Thread t2 = new Thread( r1 );

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        /*
        Kauderwelsch:
        -------------
            Thread-0 - 06.09.2022, 16:30:45
            Thread-1 - Thread-022, 16:30:45         -  06.09.2022, 16:30:45
            Thread-1 - 06.09.2022, 16:30:45         - 06.09.2022, 16:30:45Thread-1  - 06.09.2022, 16:30:45Thread-0  - 06.09.2022, 16:30:45Thread-1  - Thread-022, 16:30:45
                    - 06.09.2022, 16:30:45
            Thread-1 - 06.09.2022, 16:30:45
         */
        System.out.println( logger.getLog() );

    }
}

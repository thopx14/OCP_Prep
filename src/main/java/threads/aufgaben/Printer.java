package threads.aufgaben;

import lombok.Getter;

public class Printer {

    private final char ch;
    private final int howManyChs;
    private final int howLongPrintingCh;
    private final @Getter Runnable runnable;

    public Printer( char ch, int howManyChs, int howLongPrintingCh ) {
        this.ch = ch;
        this.howManyChs = howManyChs;
        this.howLongPrintingCh = howLongPrintingCh;
        this.runnable = this::printLines;
    }

    private void printLines() {
        for ( int i = 0; i < howLongPrintingCh; i++ ) {
            synchronized ( Printer.class ) {
                for ( int j = 0; j < howManyChs; j++ ) {
                    System.out.print( ch );
                }
            }
            System.out.println();
        }
    }

    public static void main( String[] args ) {
        new Thread( new Printer( 'a', 30, 100 ).getRunnable() ).start();
        new Thread( new Printer( 'B', 5, 60 ).getRunnable() ).start();
        new Thread( new Printer( 'X', 50, 60 ).getRunnable() ).start();

    }
}

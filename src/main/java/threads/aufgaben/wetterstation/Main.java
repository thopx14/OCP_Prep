package threads.aufgaben.wetterstation;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main( String[] args ) {
        new WheatherStation( 5, TimeUnit.SECONDS );
    }
}

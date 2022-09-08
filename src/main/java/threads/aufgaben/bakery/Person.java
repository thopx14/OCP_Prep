package threads.aufgaben.bakery;

import threads.ThreadUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class Person {
    private final String name;
    private final Bakery preferedBakery;
    private final Deque<Roll> actuallRolls;

    public Person( String name, Bakery preferedBakery ) {
        this.name = name;
        this.preferedBakery = preferedBakery;
        this.actuallRolls = new ArrayDeque<>();

        new Thread( () -> {
            while ( true ) {
                buyRoll();
                consumeRoll();
                ThreadUtils.pauseSeconds( 3 );
            }
        } ).start();
    }

    public void buyRoll() {
        int howMany = new Random().nextInt( 3, 15 );
        actuallRolls.addAll( preferedBakery.buyRolls( howMany ) );
        System.out.println( name + ": bought " + howMany + " \uD83E\uDD56!" );
    }

    public void consumeRoll() {
        int howMany = new Random().nextInt( 3, 15 );
        for ( int i = 0; i < howMany; i++ ) {
            if ( actuallRolls.pollFirst() == null ) {
                break;
            }
        }
        System.out.println( name + ": Consumed " + howMany + " rolls \uD83D\uDE0B" );
    }
}

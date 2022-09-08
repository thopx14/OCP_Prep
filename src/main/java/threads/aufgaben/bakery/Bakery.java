package threads.aufgaben.bakery;

import threads.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bakery {
    private final String name;
    private final BlockingDeque<Roll> warehouse;
    private final Lock lock;

    public Bakery( String name ) {
        this.name = name;
        this.warehouse = new LinkedBlockingDeque<>( 100 );
        this.lock = new ReentrantLock();

        new Thread( this::produceRolls ).start();
    }

    private void produceRolls() {
        while ( true ) {
            for ( int i = 0; i < 10; i++ ) {
                if ( !warehouse.offerLast( new Roll() ) ) {
                    break;
                }
            }
            System.out.println( name + ": Another 10 rolls produced (actual stock: " + warehouse.size() + " )" );
            ThreadUtils.pauseMillis( 500 );
        }
    }

    public List<Roll> buyRolls( int howMany ) {
        List<Roll> boughtRolls = new ArrayList<>();
        try {
            lock.lock();
            for ( int i = 0; i < howMany; i++ ) {
                try {
                    Roll roll = warehouse.take();
                    boughtRolls.add( roll );

                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }

            }
            return boughtRolls;

        } finally {
            lock.unlock();
        }
    }
}

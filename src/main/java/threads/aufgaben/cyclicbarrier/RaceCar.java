package threads.aufgaben.cyclicbarrier;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RaceCar implements Runnable {
    private final String name;
    private final List<RaceCar> finish;
    private final CyclicBarrier barrier;

    public RaceCar( String name, List<RaceCar> finish, CyclicBarrier barrier ) {
        this.name = name;
        this.finish = finish;
        this.barrier = barrier;
    }

    public void run() {
        try {
            Thread.sleep( 1000 );
            finish.add( this );
            this.barrier.await();

        } catch ( InterruptedException | BrokenBarrierException e ) {
            throw new RuntimeException( e );
        }
    }

    public String toString() {
        return name;
    }
}

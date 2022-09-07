package threads.aufgaben.philisophenproblem;

import threads.ThreadUtils;

import java.util.List;

public class PhilosopherSimulator {

    private final List<Philosopher> listOfPhilosophers;
    private final ForkFactory forkFactory;

    public PhilosopherSimulator( int howManyPhilosophersShouldBeGenerated ) {
        listOfPhilosophers = PhilosopherFactory.generatePhilosophers( howManyPhilosophersShouldBeGenerated );
        forkFactory = new ForkFactory( howManyPhilosophersShouldBeGenerated );
    }

    public void simulatePhilosophersDay() {
        for ( Philosopher p : listOfPhilosophers ) {
            Runnable runnable = () -> {
                while ( true ) {
                    PhilosopherAction[] values = PhilosopherAction.values();
                    for ( PhilosopherAction action : values ) {
                        p.setCurrentAction( action );
                        System.out.println( p );
                        switch ( action ) {
                            case TAKING_LEFT_FORK, TAKING_RIGHT_FORK ->
//                                    forkFactory.aquireForkNoDeadlock();
                                    forkFactory.aquireFork();
                            case RELEASE_LEFT_FORK, RELEASE_RIGHT_FORK ->
//                                    forkFactory.releaseForkNoDeadlock();
                                    forkFactory.releaseFork();
                        }
                        ThreadUtils.pauseMillis( 500 );
                    }
                }
            };
            new Thread( runnable ).start();
        }
    }
}

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
                        if ( switchState( p, action ) ) {
                            p.setCurrentAction( action );
                            if ( p.getCurrentAction() == PhilosopherAction.EAT
                                    || p.getCurrentAction() == PhilosopherAction.THINK ) {
                                ThreadUtils.pauseSeconds( 1 );
                            }
                            System.out.println( p );
                        }
                        ThreadUtils.pauseSeconds( 1 );
                    }
                }
            };
            new Thread( runnable ).start();
        }
    }

    private boolean switchState( Philosopher philosopher, PhilosopherAction action ) {
        boolean switchStateAllowed = false;
        switch ( action ) {
            case THINK, HUNGRY -> {
                switchStateAllowed = true;
            }
            case TAKING_LEFT_FORK -> {
                if ( !philosopher.isLeftFork() ) {
                    philosopher.setLeftFork( forkFactory.aquireForkNoDeadlock() );
                    switchStateAllowed = true;
                }
            }
            case TAKING_RIGHT_FORK -> {
                if ( philosopher.isLeftFork() && !philosopher.isRightFork() ) {
                    philosopher.setRightFork( forkFactory.aquireForkNoDeadlock() );
                    switchStateAllowed = true;
                }
            }
            case EAT -> {
                if ( philosopher.isLeftFork() && philosopher.isRightFork() ) {
                    switchStateAllowed = true;
                }
            }
            case RELEASE_LEFT_FORK -> {
                if ( philosopher.isLeftFork() && forkFactory.releaseForkNoDeadlock() ) {
                    philosopher.setLeftFork( false );
                    switchStateAllowed = true;
                }
            }
            case RELEASE_RIGHT_FORK -> {
                if ( philosopher.isRightFork() && forkFactory.releaseForkNoDeadlock() ) {
                    philosopher.setRightFork( false );
                    switchStateAllowed = true;
                }
            }
        }

        return switchStateAllowed;
    }
}

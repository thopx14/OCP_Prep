package threads.aufgaben.philisophenproblem;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForkFactory {

    private final List<Fork> forkList;
    private final Lock lock;

    public ForkFactory( int howManyForks ) {
        forkList = Stream.generate( () -> new Fork( false ) )
                .limit( howManyForks )
                .collect( Collectors.toList() );
        lock = new ReentrantLock();
    }

    public void aquireFork() {
        boolean allForksInUse = true;
        for ( Fork fork : forkList ) {
            synchronized ( forkList ) {
                if ( !fork.isInUse() ) {
                    fork.setInUse( true );
                    allForksInUse = false;
                    break;
                }
            }
        }
        if ( allForksInUse ) {
            synchronized ( forkList ) {
                try {
                    forkList.wait();

                } catch ( InterruptedException e ) {
                    throw new RuntimeException( e );
                }
            }
        }
    }

    public boolean aquireForkNoDeadlock() {
        boolean tryToLock = lock.tryLock();
        boolean aquireOkay = false;
        if ( tryToLock ) {
            try {
                for ( Fork fork : forkList ) {
                    if ( !fork.isInUse() ) {
                        fork.setInUse( true );
                        aquireOkay = true;
                        break;
                    }
                }
            } finally {
                lock.unlock();
            }
        }

        return aquireOkay;
    }

    public void releaseFork() {
        for ( Fork fork : forkList ) {
            synchronized ( forkList ) {
                if ( fork.isInUse() ) {
                    fork.setInUse( false );
                    forkList.notifyAll();
                    break;
                }
            }
        }
    }

    public boolean releaseForkNoDeadlock() {
        boolean tryToLock = lock.tryLock();
        if ( tryToLock ) {
            try {
                for ( Fork fork : forkList ) {
                    if ( fork.isInUse() ) {
                        fork.setInUse( false );
                        break;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
        return tryToLock;
    }
}

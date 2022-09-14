package threads.aufgaben.words;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class PasswordTester {
    private final String passwordToTest;
    private final List<String> passwords;

    public PasswordTester( String passwordToTest, List<String> passwords ) {
        this.passwordToTest = passwordToTest;
        this.passwords = passwords;
    }

    private static final class PasswordTesterTask extends RecursiveTask<Boolean> {
        private static final int THRESHOLD = 500;
        private final List<String> passwords;
        private final String passwordToTest;

        public PasswordTesterTask( List<String> passwords, String passwordToTest ) {
            this.passwords = passwords;
            this.passwordToTest = passwordToTest;
        }

        @Override protected Boolean compute() {
            if ( passwords.size() < THRESHOLD ) {
                return passwords.stream().anyMatch( p -> p.equals( passwordToTest ) );

            } else {
                int mid = passwords.size() / 2;
                PasswordTesterTask taskA = new PasswordTesterTask( passwords.subList( 0, mid ), passwordToTest );
                PasswordTesterTask taskB = new PasswordTesterTask( passwords.subList( mid, passwords.size() ), passwordToTest );

                taskA.fork();
                Boolean resultB = taskB.compute();
                Boolean resultA = taskA.join();

                return resultA || resultB;
            }
        }
    }

    public boolean doesPasswordMatch() {
        return new ForkJoinPool().invoke( new PasswordTesterTask( passwords, passwordToTest ) );
    }
}

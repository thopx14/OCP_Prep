package threads.aufgaben.philisophenproblem;

import java.util.ArrayList;
import java.util.List;

public class PhilosopherFactory {

    private static final String[] namesOfPhilosophs = {
            "Sokrates",
            "Homer",
            "Schopenhauer",
            "Kant",
            "Platon",
            "Pythagoras"
    };

    public static List<Philosopher> generatePhilosophers( int count ) {
        List<Philosopher> philosopherList = new ArrayList<>();
        if ( count < namesOfPhilosophs.length ) {
            for ( int i = 0; i < count; i++ ) {
                philosopherList.add( new Philosopher( namesOfPhilosophs[i] ) );
            }
        } else {
            throw new IllegalArgumentException( "Can only create + " + ( namesOfPhilosophs.length - 1 ) + " philosophers!" );

        }
        return philosopherList;
    }
}

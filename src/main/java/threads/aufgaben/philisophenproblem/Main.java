package threads.aufgaben.philisophenproblem;

public class Main {
    public static void main( String[] args ) {
        PhilosopherSimulator simulator = new PhilosopherSimulator( 5 );
        simulator.simulatePhilosophersDay();
    }
}

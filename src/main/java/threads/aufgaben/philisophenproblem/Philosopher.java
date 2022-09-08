package threads.aufgaben.philisophenproblem;

import lombok.Getter;
import lombok.Setter;

public class Philosopher {
    private final @Getter String name;
    private @Getter @Setter PhilosopherAction currentAction;
    private @Getter @Setter boolean leftFork;
    private @Getter @Setter boolean rightFork;

    public Philosopher( String name ) {
        this.name = name;
        this.currentAction = PhilosopherAction.THINK;
        this.leftFork = false;
        this.rightFork = false;
    }

    @Override public String toString() {
        return name + " " + currentAction.toString();
    }

}

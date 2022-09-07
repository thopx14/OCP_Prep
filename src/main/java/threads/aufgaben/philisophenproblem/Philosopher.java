package threads.aufgaben.philisophenproblem;

import lombok.Getter;
import lombok.Setter;

public class Philosopher {
    private final @Getter String name;
    private @Getter @Setter PhilosopherAction currentAction;

    public Philosopher( String name ) {
        this.name = name;
        this.currentAction = PhilosopherAction.THINK;
    }

    @Override public String toString() {
        return name + " " + currentAction.toString();
    }

}

package threads.aufgaben.philisophenproblem;

import lombok.Getter;

public enum PhilosopherAction {
    THINK( "denkt nach ..." ),
    HUNGRY( "hat Hunger ..." ),
    TAKING_LEFT_FORK( "nimmt die linke Gabel" ),
    TAKING_RIGHT_FORK( "nimmt die rechte Gabel" ),
    EAT( "isst ..." ),
    RELEASE_RIGHT_FORK( "legt die rechte Gabel ab" ),
    RELEASE_LEFT_FORK( "legt die linke Gabel ab" );

    private final @Getter String action;

    private PhilosopherAction( String action ) {
        this.action = action;
    }

    @Override public String toString() {
        return action;
    }
}

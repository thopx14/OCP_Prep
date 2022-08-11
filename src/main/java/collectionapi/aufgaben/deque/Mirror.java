package collectionapi.aufgaben.deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class Mirror {

    private final Deque<Character> deque;

    public Mirror() {
        deque = new ArrayDeque<>();
        deque.addLast( '|' );
    }

    public void add( char c ) {
        deque.addFirst( c );
        deque.addLast( c );
    }

    public boolean isEmpty() {
        return deque.size() == 1;
    }

    public void remove() throws IllegalStateException {
        if ( isEmpty() ) {
            throw new IllegalStateException( "Mirror list is empty!" );
        }
        deque.pollFirst();
        deque.pollLast();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for ( Character character : deque ) {
            sb.append( character );
        }
        return sb.toString();
    }
}

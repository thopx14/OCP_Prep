package collectionapi.aufgaben;

import java.util.ArrayDeque;
import java.util.Deque;

public class Mirror {

    private final Deque<Character> deque;

    public Mirror() {
        deque = new ArrayDeque<>();
    }

    public void add( char c ) {
        deque.addFirst( c );
        deque.addLast( c );
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public void remove() {
        deque.pollFirst();
        deque.pollLast();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int breakDelimiter = deque.size() / 2;
        int cnt = 0;

        for ( Character character : deque ) {
            if ( cnt++ == breakDelimiter )
                sb.append( "|" );
            sb.append( character );
        }
        return sb.toString();
    }
}

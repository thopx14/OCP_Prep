package collectionapi.aufgaben.deque;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Stream;

public class PalindromeChecker {

    private static final Deque<Character> deque = new ArrayDeque<>();

    public static boolean isPalindrome( String palindrom ) {
        Stream<Character> characterStream = palindrom.toLowerCase().chars().mapToObj( s -> ( char ) s );
        Character[] characters = characterStream.toArray( Character[]::new );

        return isPalindrome( characters );
    }

    private static boolean isPalindrome( Character[] arr ) {
        deque.clear(); // First clear to avoid pollution.
        deque.addAll( Arrays.asList( arr ) );

        boolean isPalindrom = false;

        while ( !deque.isEmpty() ) {
            Character head = deque.pollFirst();
            Character tail = deque.pollLast();

            if ( head != null && tail != null ) {
                isPalindrom = head.charValue() == tail.charValue();
            }

            // No chance to have a palindrom here, so we break and return immediately!
            if ( !isPalindrom )
                break;
        }

        return isPalindrom;
    }
}

import collectionapi.aufgaben.deque.PalindromeChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromeTests {

    private static final Deque<Character> deque = new ArrayDeque<>();

    @BeforeEach
    void init() {
        deque.clear();
    }

    @ParameterizedTest
    @ValueSource(strings = { "anna", "Rotor", "Hannah", "oTTo", "neben", "Regallager", "Reittier", "Rentner", "aBBa" })
    void testForPalindrome( String p ) {
        assertTrue( PalindromeChecker.isPalindrome( p ) );
    }

    @ParameterizedTest
    @ValueSource(strings = { "motor", "Klaus", "Maus", "Haus" })
    void testForNonePalindromes( String p ) {
        assertFalse( PalindromeChecker.isPalindrome( p ) );
    }
}

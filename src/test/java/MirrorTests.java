import collectionapi.aufgaben.Mirror;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MirrorTests {
    private final Mirror mirror;

    public MirrorTests() {
        mirror = new Mirror();
    }

    @BeforeEach
    void fillMirror() {
        for ( char ch = 'a'; ch < 'g'; ch++ ) {
            mirror.add( ch );
        }
    }

    @Test
    void testAddMethodWithOutputPatternAtoG() {
        Assertions.assertEquals( "fedcba|abcdef", mirror.toString() );
    }


    @Test
    void testRemoveMethodEmptyOutput() {
        while ( ! mirror.isEmpty() ) {
            mirror.remove();
        }
        Assertions.assertEquals( "", mirror.toString() );
    }


    @Test
    void testRemoveMethodNonEmptyOutput() {
        mirror.remove();
        mirror.remove();
        Assertions.assertEquals( "dcba|abcd", mirror.toString() );
    }
}

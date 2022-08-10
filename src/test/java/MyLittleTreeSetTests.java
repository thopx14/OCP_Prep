import collectionapi.aufgaben.MyLittleTreeSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Comparator;
import java.util.SortedSet;

import static org.junit.jupiter.api.Assertions.*;

public class MyLittleTreeSetTests {

    static SortedSet<Integer> myRandomSet;

    @BeforeAll
    static void init() {
        myRandomSet = MyLittleTreeSet.randomTreeSet( 0, 1001, 100, Comparator.reverseOrder() );
    }

    @Test
    void generateRandomTreeSetWith100Values() {
        for ( Integer integer : myRandomSet ) {
            assertTrue( integer >= 0 && integer <= 1000, "Integer value = " + integer );
        }
    }

    @ParameterizedTest
    @ValueSource(ints = { 100, 500 })
    void testIfSizeIsExactlyAsGiven( int size ) {
        myRandomSet = MyLittleTreeSet.randomTreeSet( 0, 1001, size, Comparator.reverseOrder() );
        assertEquals( size, myRandomSet.size() );
    }

    @Test
    void testGetSubset() {
        SortedSet<Integer> subSet = myRandomSet.subSet( 900, 799 ); // 799 excl. 900 incl. -> reversedOrder() !

        for ( Integer integer : subSet ) {
            assertTrue( integer >= 800 && integer <= 900, "Integer value = " + integer ); // [ 800 .. 900 ]
        }
    }

    @Test
    void testDescendingOrder() {
        Object[] objects = myRandomSet.toArray();

        for ( int i = 0; i < objects.length; i++ ) {
            int j = i + 1;
            if ( j < objects.length ) {
                assertFalse( ( int ) objects[i] <= ( int ) objects[j], objects[i] + " <= " + objects[j] );
            }
        }

    }

}

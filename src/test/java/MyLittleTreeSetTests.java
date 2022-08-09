import collectionapi.aufgaben.MyLittleTreeSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.SortedSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyLittleTreeSetTests {

    @Test
    void generateRandomTreeSetWith100Values() {
        int from = 0;
        int to = 901;
        int howMayTimes = 100;
        SortedSet<Integer> myRandomSet = MyLittleTreeSet.randomTreeSet( to, from, howMayTimes );

        for ( Integer integer : myRandomSet ) {
            assertTrue( integer >= from && integer <= to, "Integer value = " + integer );
        }
    }

    @ParameterizedTest
    @ValueSource(ints = { 100, 500 })
    void testIfSizeIsExactlyAsGiven( int size ) {
        SortedSet<Integer> myRandomSet = MyLittleTreeSet.randomTreeSet( 0, 1000, size );

        assertEquals( size, myRandomSet.size() );
    }

    @Test
    void testGetSubset() {
        SortedSet<Integer> myRandomSet = MyLittleTreeSet.randomTreeSet( 0, 1000, 100 );
        SortedSet<Integer> subSet = myRandomSet.subSet( 800, 901 ); // 800 incl. 901 excl. -> [ 800 .. 900 ]

        for ( Integer integer : subSet ) {
            assertTrue( integer >= 800 && integer <= 900, "Integer value = " + integer ); // [ 800 .. 900 ]
        }
    }

}

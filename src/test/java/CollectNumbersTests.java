import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import streams.aufgaben.CollectNumbers;

import java.util.ArrayList;
import java.util.List;

public class CollectNumbersTests {

    private static final String[] arr = {
            "1,2,3,4,5",
            "7,6,5,4,3",
            "123,456",
    };

    @Test
    void testExtractElementsOfArrToListOfInteger() {
        List<Integer> expected = new ArrayList<>();
        for ( String s : arr ) {
            String[] stringNumbers = s.split( "," );

            for ( String sNum : stringNumbers ) {
                Integer num = Integer.valueOf( sNum );
                expected.add( num );
            }
        }

        List<Integer> result = CollectNumbers.a1( arr );

        Assertions.assertIterableEquals( expected, result );
    }

    @Test
    void testExtractElementsOfArrToListOfIntegerOnlyEvenNumbers() {
        List<Integer> expected = new ArrayList<>();
        for ( String s : arr ) {
            String[] stringNumbers = s.split( "," );

            for ( String sNum : stringNumbers ) {
                Integer num = Integer.valueOf( sNum );
                if ( num % 2 == 0 )
                    expected.add( num );
            }
        }

        List<Integer> result = CollectNumbers.a2( arr );

        Assertions.assertIterableEquals( expected, result );
    }

    @Test
    void testExtractElementsOfArrToListOfIntegerOnlyEvenNumbersWithDiffCollectMethod() {
        List<Integer> expected = new ArrayList<>();
        for ( String s : arr ) {
            String[] stringNumbers = s.split( "," );

            for ( String sNum : stringNumbers ) {
                Integer num = Integer.valueOf( sNum );
                if ( num % 2 == 0 )
                    expected.add( num );
            }
        }

        List<Integer> result = CollectNumbers.a3( arr );

        Assertions.assertIterableEquals( expected, result );
    }
}

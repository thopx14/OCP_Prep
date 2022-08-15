import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import collectionapi.aufgaben.maps.TextStatistics;

public class TextStatisticsTests {

    private static final TextStatistics st1 = TextStatistics.of( "Heute ist Montag" );

    @Test
    void testGetUniqueCharsSt1Object() {

        Collection<Character> uniqueChars = st1.getUniqueChars();
        Collection<Character> origChars = st1.getText()
                .chars()
                .mapToObj( s -> (char) s )
                .collect( Collectors.toCollection( HashSet<Character>::new ) );

        boolean contains = true;
        
        for ( Character character : uniqueChars ) {
            if( !origChars.contains( character ) ) {
                contains = false;
                break;
            }
        }
        
        assertTrue(contains);
    }

}

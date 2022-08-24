import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import streams.aufgaben.StreamCountMinMax;

import java.util.*;

public class StreamCountMinMaxTests {

    private static Locale[] locales = null;

    @BeforeEach
    void initLocales() {
        locales = Locale.getAvailableLocales();
    }

    @Test
    public void a1() {
        Optional<String> result = StreamCountMinMax.a1();

        List<Locale> tempLocalList = new ArrayList<>( List.of( locales ) );
        tempLocalList.sort( ( l1, l2 ) -> l1.getDisplayCountry().compareTo( l2.getDisplayCountry() ) );

        String expected = tempLocalList.get( tempLocalList.size() - 1 ).getDisplayCountry();

        Assertions.assertEquals( expected, result.get() );
    }

    @Test
    public void a2() {
        long result = StreamCountMinMax.a2();
        long expected = 0;

        for ( Locale locale : locales ) {
            if ( locale.getLanguage().startsWith( "de" ) ) {
                expected++;
            }
        }

        Assertions.assertEquals( expected, result );
    }

    @Test
    public void a3() {
        Optional<Locale> result = StreamCountMinMax.a3();
        Locale expected = null;

        List<Locale> filtered = new ArrayList<>();
        for ( Locale locale : locales ) {
            if ( locale.getDisplayCountry().contains( "t" ) ) {
                filtered.add( locale );
            }
        }

        Comparator<Locale> cmp = ( loc1, loc2 ) ->
                loc1.getDisplayLanguage().compareTo( loc2.getDisplayLanguage() );

        filtered.sort( cmp );

        if ( filtered.size() > 0 ) {
            expected = filtered.get( 0 );
        }

        Assertions.assertEquals( expected, result.get() );
    }
}

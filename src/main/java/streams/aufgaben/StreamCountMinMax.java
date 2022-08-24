package streams.aufgaben;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * see StreamCountMinMaxTests.java for unit tests!
 */
public class StreamCountMinMax {

    private static final Locale[] locales = Locale.getAvailableLocales();

    public static Optional<String> a1() {
        return Arrays.stream( locales )
                .map( Locale::getDisplayCountry )
                .max( Comparator.naturalOrder() );
    }

    public static long a2() {
        return Arrays.stream( locales )
                .map( Locale::getLanguage )
                .filter( s -> s.startsWith( "de" ) )
                .count();
    }

    public static Optional<Locale> a3() {
        Comparator<Locale> cmp = ( loc1, loc2 ) ->
                loc1.getDisplayLanguage().compareTo( loc2.getDisplayLanguage() );

        return Stream.of( Locale.getAvailableLocales() )
                .filter( l -> l.getDisplayCountry().contains( "t" ) )
                .min( cmp );
    }
}

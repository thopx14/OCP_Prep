import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import streams.aufgaben.TextStatistics;
import streams.aufgaben.TextStatisticsCombined;
import streams.aufgaben.TextStatisticsImpl;

import java.util.*;
import java.util.function.BiFunction;

public class TextStatisticsImplTests {

    private TextStatisticsImpl textStatistics = null;
    private static final String[] input = {
            "String 1",
            "Heute ist Montag",
            "Java ist toll!",
            "BBBBBBBBB",
            "Huhu",
            "Rhabarber"
    };

    @ParameterizedTest
    @ValueSource(strings = { "Heute ist ein guter Tag!" })
    public void testGetCountChars( String s ) {
        textStatistics = new TextStatisticsImpl( s );

        int expected = s.length();
        int result = textStatistics.getCountChars();

        Assertions.assertEquals( expected, result );
    }

    @ParameterizedTest
    @ValueSource(strings = { "Heute, ist ein guter Tag im Intern@t!!" })
    public void testGetCountSpecialChars( String s ) {
        textStatistics = new TextStatisticsImpl( s );
        long result = textStatistics.getCountSpecialChars();

        Assertions.assertEquals( 10, result );
    }

    @ParameterizedTest
    @ValueSource(strings = { "Rhabarber schmeckt bitter, aber gesund isser" })
    public void testGetLongestWord( String s ) {
        textStatistics = new TextStatisticsImpl( s );

        Optional<String> result = textStatistics.getLongestWord();

        Assertions.assertNotEquals( Optional.empty(), result );
        Assertions.assertEquals( "Rhabarber", result.get() );
    }

    @ParameterizedTest
    @ValueSource(strings = { "" })
    public void testGetLongestWordWithEmptyString( String s ) {
        textStatistics = new TextStatisticsImpl( s );

        Optional<String> result = textStatistics.getLongestWord();

        Assertions.assertEquals( Optional.empty(), result );
    }

    @ParameterizedTest
    @ValueSource(strings = { "AAAAA BBBBB" })
    public void testGetLongestWordWithTwoSameLen( String s ) {
        textStatistics = new TextStatisticsImpl( s );

        Optional<String> result = textStatistics.getLongestWord();

        Assertions.assertEquals( "AAAAA", result.get() );
    }

    @Test
    public void testGetCombinedTextstatisticsCountChars() {
        TextStatistics idt = new TextStatisticsImpl( "" );
        BiFunction<TextStatistics, String, TextStatistics> acc = ( stats, strVal )
                -> new TextStatisticsCombined( stats, new TextStatisticsImpl( strVal ) );

        TextStatistics stats = Arrays.stream( input ).reduce( idt, acc, TextStatisticsCombined::new );
        int result = stats.getCountChars();

        int expected = 0;
        for ( String s : input ) {
            expected += s.length();
        }

        Assertions.assertEquals( expected, result );
    }

    @Test
    public void testGetCombinedTextstatisticsCountSpecialChars() {
        TextStatistics idt = new TextStatisticsImpl( "" );
        BiFunction<TextStatistics, String, TextStatistics> acc = ( stats, strVal )
                -> new TextStatisticsCombined( stats, new TextStatisticsImpl( strVal ) );

        TextStatistics stats = Arrays.stream( input ).reduce( idt, acc, TextStatisticsCombined::new );
        long result = stats.getCountSpecialChars();

        long expected = 0;
        for ( String s : input ) {
            char[] chars = s.toCharArray();
            for ( char aChar : chars ) {
                if ( !Character.isLetterOrDigit( aChar ) )
                    expected++;
            }
        }

        Assertions.assertEquals( expected, result );
    }

    @Test
    public void testGetCombinedTextstatisticsCountLetters() {
        TextStatistics idt = new TextStatisticsImpl( "" );
        BiFunction<TextStatistics, String, TextStatistics> acc = ( stats, strVal )
                -> new TextStatisticsCombined( stats, new TextStatisticsImpl( strVal ) );

        TextStatistics stats = Arrays.stream( input ).reduce( idt, acc, TextStatisticsCombined::new );
        long result = stats.getCountLetters();

        long expected = 0;
        for ( String s : input ) {
            char[] chars = s.toCharArray();
            for ( char aChar : chars ) {
                if ( Character.isLetter( aChar ) )
                    expected++;
            }
        }

        Assertions.assertEquals( expected, result );
    }

    @Test
    public void testGetCombinedTextstatisticsLongestWord() {
        TextStatistics idt = new TextStatisticsImpl( "" );
        BiFunction<TextStatistics, String, TextStatistics> acc = ( stats, strVal )
                -> new TextStatisticsCombined( stats, new TextStatisticsImpl( strVal ) );

        TextStatistics stats = Arrays.stream( input ).reduce( idt, acc, TextStatisticsCombined::new );
        Optional<String> result = stats.getLongestWord();

        String expected;
        List<String> stringList = new ArrayList<>();

        for ( String s : input ) {
            String[] words = s.split( " " );
            Collections.addAll( stringList, words );
        }

        stringList.sort( Comparator.comparing( String::length ) );

        expected = stringList.get( stringList.size() - 1 );
        System.out.println( expected );

        Assertions.assertEquals( expected, result.get() );
    }
}

package streams.aufgaben;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class TextStatisticsImpl implements TextStatistics {

    private final String text;

    public TextStatisticsImpl( String s ) {
        text = s;
    }

    @Override
    public int getCountChars() {
        return Stream.of( text )
                .reduce( 0, ( val, str ) -> val + str.length(), Integer::sum );
    }

    @Override
    public long getCountSpecialChars() {
        return text.chars()
                .filter( c -> !Character.isLetterOrDigit( c ) )
                .count();
    }

    @Override
    public long getCountLetters() {
        return text.chars()
                .filter( Character::isLetter )
                .count();
    }

    @Override
    public Optional<String> getLongestWord() {
        if ( text.equals( "" ) ) {
            return Optional.empty();
        }

        Function<String, String> func = s -> {
            StringBuilder sb = new StringBuilder();
            for ( int i = 0; i < s.length(); i++ ) {
                char curChar = s.charAt( i );
                if ( Character.isLetterOrDigit( curChar ) ) {
                    sb.append( curChar );
                }
            }
            return sb.toString();
        };

        return Stream.of( text.split( " " ) )
                .map( func )
//                .peek( System.out::println )
                .max( Comparator.comparing( String::length ) );
    }
}

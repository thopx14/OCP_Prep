package collectionapi.aufgaben.maps;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class TextStatistics {

    private final String text;

    private TextStatistics( String text ) {
        this.text = text;
    }

    public static TextStatistics of( String text ) {
        return new TextStatistics( text );
    }
    
    public String getText() {
        return text;
    }

    public Collection<Character> getUniqueChars() {
        return text.chars().mapToObj( s -> (char) s )
                .collect( Collectors.toCollection( HashSet<Character>::new ) );
    }

    public Map<Character, Integer> getCharCounts() {
        /*
         * Override HashMap class to get a better toString() output.
         */
        @SuppressWarnings("serial")
        Map<Character, Integer> charCountMap = new HashMap<>() {
            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();

                sb.append( "{" );

                BiConsumer<Character, Integer> bc = ( k, v ) -> {
                    sb.append( "'" )
                    .append( k )
                    .append( "'" )
                    .append( "=" )
                    .append( get( k ) )
                    .append( "; " );
                };

                this.forEach( bc );

                sb.append( "}" );

                return sb.toString();
            }
        };

        // Count the chars
        for ( int i = 0; i < text.length(); i++ ) {
            
            Integer cnt = charCountMap.get( text.charAt( i ) );            
            if ( cnt == null ) {
                cnt = 0;
            }
            charCountMap.put( text.charAt( i ), ++cnt );
        }
        return charCountMap;
    }

    public static void main( String[] args ) {
        
        TextStatistics stat = TextStatistics.of( "Heute ist Montag!" );
        Collection<Character> chars = stat.getUniqueChars();
        System.out.println( chars ); // [ , a, !, e, g, H, i, M, n, o, s, t, u]

        System.out.println( stat.getCharCounts() );
        // {' '=2; 'a'=1; '!'=1; 'e'=2; 'g'=1; 'H'=1; 'i'=1; 'M'=1; 'n'=1; 'o'=1; 's'=1; 't'=3; 'u'=1; }
    }

}

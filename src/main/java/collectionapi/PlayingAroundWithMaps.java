package collectionapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PlayingAroundWithMaps {

    public static void main( String[] args ) {

        Map<String, String> options = new HashMap<>();

        for ( int i = 0; i < args.length; i++ ) {
            if ( args[i].trim().startsWith( "-" ) || args[i].trim().startsWith( "--" ) ) {
                int j = i + 1;
                if ( j < args.length ) {
                    options.put( args[i].replaceAll( "-", "" ), args[j] );
                }
            }
        }

        System.out.println( options );

        options.forEach( ( s1, s2 ) -> System.out.println( s1 + " " + s2 ) );

        Set<Entry<String, String>> mapSet = options.entrySet();

        System.out.println();

        for ( Entry<String, String> entry : mapSet ) {
            System.out.println( entry.getKey() + " = " + entry.getValue() );
        }

        System.out.println();
        for ( String key : options.keySet() ) {
            System.out.println( "key: " + key + " = " + options.get( key ) );
        }

    }

}

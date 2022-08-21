package functional.aufgaben;

import java.util.HashMap;
import java.util.Map;

public class MapsFunctional {

    static Map<Integer, String> createMap() {
        Map<Integer, String> newMap = new HashMap<>();
        newMap.put( 1, "Mo" );
        newMap.put( 2, "Di" );
        newMap.put( 3, "Mi" );

        return newMap;
    }

    public static void main( String[] args ) {
        Map<Integer, String> myMap = createMap();

        /*
        A1
         */
        myMap.forEach( ( k, v ) -> System.out.println( k + ": " + v ) );
        /*
        1: Mo
        2: Di
        3: Mi
         */

        /*
        A2
         */
        myMap.compute( 2, ( key, oldValue ) -> oldValue != null ? "Dienstag" : null );
        System.out.println();
        myMap.forEach( ( k, v ) -> System.out.println( k + ": " + v ) );
        /*
        1: Mo
        2: Dienstag
        3: Mi
         */

        /*
        A3
         */
        myMap = createMap();
        myMap.computeIfAbsent( 2, k -> "Dienstag" );
        System.out.println();
        myMap.forEach( ( k, v ) -> System.out.println( k + ": " + v ) );
        /*
        1: Mo
        2: Di
        3: Mi
         */

        /*
        A4
         */
        myMap = createMap();
        myMap.computeIfPresent( 2, ( k, v ) -> "Dienstag" );
        System.out.println();
        myMap.forEach( ( k, v ) -> System.out.println( k + ": " + v ) );
        /*
        1: Mo
        2: Dienstag
        3: Mi
         */

        myMap = createMap();

        System.out.println();
        myMap.merge( 2, "Dienstag", ( existingValue, additionalValue ) -> {
            System.out.println( "existing: " + existingValue );
            System.out.println( "additional: " + additionalValue );
            return existingValue + " (" + additionalValue + ")";
        } );

        myMap.merge( 4, "Do", ( existingValue, additionalValue ) -> {
            System.out.println( "existing: " + existingValue );
            System.out.println( "additional: " + additionalValue );
            return existingValue + " (" + additionalValue + ")";
        } );

        System.out.println();
        myMap.forEach( ( i, v ) -> System.out.println( i + " = " + v ) );
        /*
        existing: Die
        additional: Dienstag

        Das hier wird nie ausgegeben, weil die methode merge(..) so definiert ist:
        ----------------------------------------------------------
         V newValue = (oldValue == null) ? value :
                   remappingFunction.apply(oldValue, value);
        ----------------------------------------------------------
        (existing: null )
        ( additional: Do )

        Finale Ausgabe:
        ------------------
        1: Mo
        2: Di (Dienstag)
        3: Mi
        4: Do
         */

    }
}

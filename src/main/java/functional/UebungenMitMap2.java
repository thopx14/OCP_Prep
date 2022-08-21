package functional;

import java.util.Map;
import java.util.function.BiFunction;

public class UebungenMitMap2 {
    public static void main( String[] args ) {

        Map<Integer, String> myMap = MapUtils.createMap();

        // BiConsumer<T, U> : void accept(T t, U u);
        myMap.forEach( ( k, v ) -> System.out.println( k + ": " + v ) );

        /*
            Ausgabe:
            1: mo
            2: die
            3: do
            4: fr
            5: sa
            6: so
         */

        // BiFunction<? super K, ? super V, ? extends V> function: V accept(K k, V v);
        myMap.replaceAll( ( k, v ) -> v.toUpperCase() );
        System.out.println();
        myMap.forEach( ( k, v ) -> System.out.println( k + ": " + v ) );

        myMap.computeIfAbsent( 7, ( k ) -> "??" );
        System.out.println();
        myMap.forEach( ( k, v ) -> System.out.println( k + ": " + v ) ); // Neuer key hinzugefügt: 7: ??

        myMap.computeIfPresent( 1, ( k, v ) -> "Montag" );
        System.out.println();
        myMap.forEach( ( k, v ) -> System.out.println( k + ": " + v ) ); // 1: Montag

        BiFunction<Integer, String, String> remappingFunction = ( k, v ) -> {
            if ( v == null ) {
                // No value exists for current key!
                return "UNBEKANNT";
            }
            return new StringBuilder( "UNBEKANNT" ).reverse().toString();
        };

        myMap.compute( 7, remappingFunction );

        System.out.println();
        myMap.forEach( ( k, v ) -> System.out.println( k + ": " + v ) ); // 7: TNNAKEBNU

        myMap.compute( 8, remappingFunction ); // 8: UNBEKANNT

        System.out.println();
        myMap.forEach( ( k, v ) -> System.out.println( k + ": " + v ) );

        //        myMap.merge( 7, " HUHU", ( oldValue, newValue ) -> oldValue.concat( newValue ) ); // 7: TNNAKEBNU HUHU
        // Kurzform
        myMap.merge( 7, " HUHU", String::concat ); // 7: TNNAKEBNU HUHU

        System.out.println();
        myMap.forEach( ( k, v ) -> System.out.println( k + ": " + v ) );

        // 10 existierte noch nicht vorher. Wird also angelegt mit "HUHU" (newValue)
        myMap.merge( 10, " HUHU", String::concat ); // 10:  HUHU

        System.out.println();
        myMap.forEach( ( k, v ) -> System.out.println( k + ": " + v ) );
    }
}

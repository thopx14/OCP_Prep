package collectionapi.aufgaben.maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapBesitzerFahrzeuge {
    public static void main( String[] args ) {
        Fahrzeug f1 = new Fahrzeug( "VW", "Golf" );
        Fahrzeug f2 = new Fahrzeug( "VW", "Polo" );
        Fahrzeug f3 = new Fahrzeug( "Tesla", "Model3" );
        Fahrzeug f4 = new Fahrzeug( "BMW", "M3" );

        Person p1 = new Person( "Max", "Mustermann" );
        Person p2 = new Person( "Erika", "Musterfrau" );
        Person p3 = new Person( "Hans", "Dampf" );

        Map<Fahrzeug, Person> fahrzeugPersonMap = new HashMap<>();

        fahrzeugPersonMap.put( f1, p1 );
        fahrzeugPersonMap.put( f2, p1 );
        fahrzeugPersonMap.put( f3, p2 );
        fahrzeugPersonMap.put( f4, p3 );

        System.out.println( "Person von Fahrzeug: " + f1 );
        System.out.println( fahrzeugPersonMap.get( f1 ) );
        System.out.println( "Person von Fahrzeug: " + f2 );
        System.out.println( fahrzeugPersonMap.get( f2 ) );

        Map<Person, List<Fahrzeug>> fahrzeugListe = new HashMap<>();

        fahrzeugListe.put( p1, List.of( f1, f2, f3 ) );
        fahrzeugListe.put( p2, List.of( f1 ) );
        fahrzeugListe.put( p3, List.of( f3, f4 ) );

        System.out.println( "Fahrzeuge von: " + p1 );
        System.out.println( fahrzeugListe.get( p1 ) );
        System.out.println( "Fahrzeuge von: " + p2 );
        System.out.println( fahrzeugListe.get( p2 ) );
        System.out.println( "Fahrzeuge von: " + p3 );
        System.out.println( fahrzeugListe.get( p3 ) );

        System.out.println();
        System.out.println( "*** printMap(fahrzeugPersonMap):" );
        printMap( fahrzeugPersonMap );
        System.out.println();
        System.out.println( "*** printMap(fahrzeugListe):" );
        printMap( fahrzeugListe );
    }

    static <K, V> void printMap( Map<K, V> map ) {
        map.forEach( ( k, v ) -> System.out.printf( "%s -> %s%n", k, map.get( k ) ) );
    }
}

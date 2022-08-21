package functional;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class UebungenMitMap {

    public static void main( String[] args ) {
        Map<City, List<Person>> cityListMap = new HashMap();

        Person p1 = new Person( "Franz", "Zimmer", LocalDate.of( 1977, Month.JANUARY, 1 ) );
        Person p2 = new Person( "Klaus", "Kleber", LocalDate.of( 1956, Month.APRIL, 18 ) );
        Person p3 = new Person( "Alisa", "Müller", LocalDate.of( 1999, Month.MARCH, 22 ) );
        Person p4 = new Person( "Ulrike", "Weber", LocalDate.of( 1937, Month.AUGUST, 6 ) );
        Person p5 = new Person( "Max", "Mustermann", LocalDate.of( 1937, Month.AUGUST, 6 ) );
        Person p6 = new Person( "Erika", "Musterfrau", LocalDate.of( 1937, Month.AUGUST, 6 ) );

        City c1 = new City( "Ulm", 48.41316116292635, 9.976115184488048 );
        City c2 = new City( "Münster", 51.96803285204616, 7.61627695950934 );
        City c3 = new City( "Köln", 50.941424966118745, 6.957423713712692 );

        cityListMap.put( c1, List.of( p1, p2 ) );
        cityListMap.put( c2, List.of( p3, p4 ) );
        cityListMap.put( c3, List.of( p5, p6 ) );

        cityListMap.forEach( ( k, v ) -> System.out.println( k + ": \n" + v ) );

        BiFunction<City, List<Person>, List<Person>> replacePersons = new BiFunction<City, List<Person>, List<Person>>() {
            @Override
            public List<Person> apply( City city, List<Person> people ) {
                Person p1 = new Person( "Max", "Musterknabe", LocalDate.of( 1977, Month.APRIL, 1 ) );
                Person p2 = new Person( "Erika", "Superwoman", LocalDate.of( 1955, Month.MARCH, 18 ) );
                List<Person> newPersonList = List.of( p1, p2 );

                return newPersonList;
            }
        };

        System.out.println();
        cityListMap.replaceAll( replacePersons );
        cityListMap.forEach( ( k, v ) -> System.out.println( k + ": \n" + v ) );

    } // end of main

} // end of UebungenMitMap

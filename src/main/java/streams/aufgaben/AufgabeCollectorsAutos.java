package streams.aufgaben;

import java.util.*;
import java.util.stream.Collectors;

public class AufgabeCollectorsAutos {

    static class Auto {
        private String hersteller, modell;

        public Auto( String hersteller, String modell ) {
            this.hersteller = hersteller;
            this.modell = modell;
        }

        public String getHersteller() {
            return hersteller;
        }

        public String getModell() {
            return modell;
        }

        public String toString() {
            return hersteller + "/" + modell;
        }
    }

    public static void main( String[] args ) {
        List<Auto> autos = Arrays.asList(
                new Auto( "VW", "Golf" ),
                new Auto( "VW", "Polo" ),
                new Auto( "Opel", "Corsa" ),
                new Auto( "Opel", "Astra" )
        );

        /*
         * ###### A1.
         */
        Set<String> set = autos.stream().collect(
                Collectors.mapping( Auto::getHersteller, Collectors.toSet() )
        );

        System.out.println( "*** A1:" );
        System.out.println( set ); // mögliche Ausgabe: [VW, Opel]

        /*
         * ###### A2.
         */
        Map<String, List<Auto>> map1 = autos.stream().collect(
                Collectors.groupingBy( Auto::getHersteller ) ); // wird automatisch eine Liste zurückgeben!

        System.out.println( "*** A2:" );
        System.out.println( map1 );
        // mögliche Ausgabe: {VW=[VW/Golf, VW/Polo], Opel=[Opel/Corsa, Opel/Astra]}

        /*
         * ###### A3.
         */
        Map<String, List<String>> map2 = autos.stream()
                .collect(
                        Collectors.groupingBy(
                                Auto::getHersteller,
                                Collectors.mapping(
                                        Auto::getModell, Collectors.toList()
                                )
                        )
                );
        System.out.println( "*** A3:" );
        System.out.println( map2 );
        // mögliche Ausgabe: {VW=[Golf, Polo], Opel=[Corsa, Astra]}

        /*
         * ###### A4.
         */
        Map<String, List<Auto>> map3 = autos.stream()
                .collect(
                        Collectors.groupingBy(
                                Auto::getHersteller,
                                TreeMap::new,
                                Collectors.toList()
                        )
                );
        System.out.println( "*** A4:" );
        System.out.println( map3 );
        // Ausgabe: {Opel=[Opel/Corsa, Opel/Astra], VW=[VW/Golf, VW/Polo]}

        /*
         * ###### A5.
         */
        Map<Boolean, List<Auto>> map4 = autos.stream()
                .collect(
                        Collectors.partitioningBy( a -> a.getModell().contains( "o" ) )
                );
        System.out.println( "*** A5:" );
        System.out.println( map4 );
    }
}

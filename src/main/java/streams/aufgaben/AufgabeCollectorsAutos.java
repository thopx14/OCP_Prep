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

//        Function<Auto, String> mapper = auto -> auto.getHersteller();        
//        Collector<String, ?, Set<String>> downstream = Collectors.toSet();
//        Collector<Auto, ?, Set<String>> collector
//                = Collectors.mapping( Auto::getHersteller, Collectors.toSet() );

        Set<String> set = autos.stream().collect(
                Collectors.mapping( Auto::getHersteller, Collectors.toSet() )
        );
        System.out.println( "*** A1:" );
        System.out.println( set ); // mögliche Ausgabe: [VW, Opel]

        /*
         * ###### A2.
         */
//        Function<Auto, String> classifier = Auto::getHersteller; // Key der neuen Map!
//        Collector<Auto, ?, List<Auto>> downstream = Collectors.toList();
//        
//        Collector<Auto, ?, Map<String, List<Auto>>> collector2 =
//                Collectors.groupingBy(classifier, downstream);

        Map<String, List<Auto>> map1 = autos.stream().collect(
                Collectors.groupingBy(
                        Auto::getHersteller,
                        Collectors.toList() ) );
        System.out.println( "*** A2:" );
        System.out.println( map1 );
        // mögliche Ausgabe: {VW=[VW/Golf, VW/Polo], Opel=[Opel/Corsa, Opel/Astra]}

        /*
         * ###### A3.
         */
        System.out.println( "*** A3:" );
//        Collector<Auto, ?, List<String>> downstream =
//                Collectors.mapping( Auto::getModell, Collectors.toList() );
//
//        Collector<Auto, ?, Map<String, List<String>>> collector2 =
//                Collectors.groupingBy( Auto::getHersteller, downstream );

        Map<String, List<String>> map2 = autos.stream()
                .collect(
                        Collectors.groupingBy(
                                Auto::getHersteller,
                                Collectors.mapping(
                                        Auto::getModell, Collectors.toList()
                                )
                        )
                );
        System.out.println( map2 );
        // mögliche Ausgabe: {VW=[Golf, Polo], Opel=[Corsa, Astra]}

        /*
         * ###### A4.
         */
        System.out.println( "*** A4:" );
//
//        Function<Auto, String> func = Auto::getHersteller;
//        Collector<Auto, ?, List<Auto>> downstream = Collectors.toList();
//        Supplier<Map<String, List<Auto>>> supp = TreeMap::new;
//
//        Collector<Auto, ?, Map<String, List<Auto>>> collector2 =
//                Collectors.groupingBy( func, supp, downstream );

        Map<String, List<Auto>> map3 = autos.stream()
                .collect(
                        Collectors.groupingBy(
                                Auto::getHersteller,
                                TreeMap::new,
                                Collectors.toList()
                        )
                );
        System.out.println( map3 );
        // Ausgabe: {Opel=[Opel/Corsa, Opel/Astra], VW=[VW/Golf, VW/Polo]}

        /*
         * ###### A5.
         */
        Map<Boolean, List<Auto>> map4 = autos.stream()
                .collect(
                        Collectors.partitioningBy(
                                a -> a.getModell().contains( "o" ),
                                Collectors.toList()
                        )
                );
        System.out.println( "*** A5:" );
        System.out.println( map4 );

    }
}

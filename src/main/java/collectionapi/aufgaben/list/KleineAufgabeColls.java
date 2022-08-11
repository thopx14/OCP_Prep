package collectionapi.aufgaben.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KleineAufgabeColls {

    public static void main( String[] args ) {

        /*
        A1
         */
        List<String> stringList = new ArrayList<>();
        stringList.add( "hhh" );
        stringList.add( "aa" );
        stringList.add( "m" );
        stringList.add( "aa" );
        stringList.add( "cccc" );
        stringList.add( "aa" );

        /*
        A2
         */
        System.out.println( stringList ); // [hhh, aa, m, aa, cccc, aa]

        /*
        A3
         */
        Collections.sort( stringList, String::compareToIgnoreCase );
        System.out.println( stringList ); // [aa, aa, aa, cccc, hhh, m]

        /*
            stringList.sort( null ); <-- sortiert die Liste nach der natürlichen Ordnung!
         */

    }
}

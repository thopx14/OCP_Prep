package collectionapi.aufgaben.maps;

import java.util.HashMap;
import java.util.Map;

public class KleineAufgabeMap {
    public static void main( String[] args ) {

        Map<Integer, String> map = new HashMap<>();

        map.put( 1, "Januar" );
        map.put( 2, "Februar" );
        map.put( 3, "März" );

        for ( Integer key : map.keySet() ) {
            System.out.println( map.get( key ) );
        }
    }
}

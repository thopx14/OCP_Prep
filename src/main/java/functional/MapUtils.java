package functional;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    static Map<Integer, String> createMap() {
        Map<Integer, String> newMap = new HashMap<>();
        newMap.put( 1, "mo" );
        newMap.put( 2, "die" );
        newMap.put( 3, "do" );
        newMap.put( 4, "fr" );
        newMap.put( 5, "sa" );
        newMap.put( 6, "so" );

        return newMap;
    }

} // end of MapUtils

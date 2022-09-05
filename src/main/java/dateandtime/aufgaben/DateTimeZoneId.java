package dateandtime.aufgaben;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DateTimeZoneId {
    public static void main( String[] args ) {

        Map<String, List<String>> groupedZoneIds = extractZoneIdsByCountryName();
        // A1 + A2
        groupedZoneIds.forEach( ( k, v ) -> System.out.println( k + ": " + v ) );
        //A3
        saveGroupedIdsToFile( groupedZoneIds, Path.of( "./timezones.txt" ) );

    }

    public static Map<String, List<String>> extractZoneIdsByCountryName() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        return availableZoneIds.stream() // Stream<String>
                .collect( Collectors.groupingBy(
                        str -> {
                            if ( str.contains( "/" ) ) {
                                return str.split( "/" )[0];
                            }
                            return "Special";
                        }
                ) );
    }

    public static void saveGroupedIdsToFile( Map<String, List<String>> map, Path p ) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable( SerializationFeature.INDENT_OUTPUT );

        try {
            Writer writer = Files.newBufferedWriter( p );
            mapper.writeValue( writer, map );

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}

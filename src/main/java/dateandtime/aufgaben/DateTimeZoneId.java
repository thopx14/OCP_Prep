package dateandtime.aufgaben;

import java.io.BufferedWriter;
import java.io.IOException;
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
        saveGroupedIdsToFile( groupedZoneIds, Path.of( "." ) );

    }

    private static Map<String, List<String>> extractZoneIdsByCountryName() {
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

    private static void saveGroupedIdsToFile( Map<String, List<String>> map, Path p ) {
        for ( String k : map.keySet() ) {
            Path pathToNewFile = p.resolve( Path.of( k + ".txt" ) );
            List<String> zones = map.get( k );

            try ( BufferedWriter writer = Files.newBufferedWriter( pathToNewFile ) ) {

                writer.write( k + ":" );
                writer.newLine();
                for ( String zone : zones ) {
                    writer.write( "**" + zone );
                    writer.newLine();
                }

            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }
}

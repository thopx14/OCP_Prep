import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

class DateTimeZoneIdTests {

    private static final String TIMEZONES_TXT = "./timezones.txt";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    @BeforeEach
    void checkExistenceOfTimezonesFile() {
        Assertions.assertTrue( Files.exists( Path.of( TIMEZONES_TXT ) ) );
    }

    @Test
    void testValidJsonFileOfTimeZonesWritten() {
        try ( Reader reader = Files.newBufferedReader( Path.of( TIMEZONES_TXT ) ) ) {

            Assertions.assertInstanceOf( Map.class, mapper.readValue( reader, Map.class ) );

        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }

    }
}

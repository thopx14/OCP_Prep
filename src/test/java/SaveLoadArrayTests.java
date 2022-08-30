import io.aufgaben.SaveLoadArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveLoadArrayTests {

    private static final int LEN = new java.util.Random().nextInt( 100 ) + 1;
    private static final int MIN = -20;
    private static final int MAX = 20;
    private static int[] array;

    @BeforeEach
    void init() {
        array = SaveLoadArray.createArray( LEN, MIN, MAX );
    }

    @Test
    void testLenOfCreatedArray() {
        Assertions.assertEquals( LEN, array.length );
    }

    @ParameterizedTest
    @ValueSource(strings = { "array.txt" })
    void testIfFileForSaveArrayIsCreated( String dest ) {
        SaveLoadArray.saveArray( array, dest );

        assertTrue( Files.exists( Path.of( dest ) ) );
    }

    @ParameterizedTest
    @ValueSource(strings = { "array.txt" })
    void testIfContentsOfSavedArrayAreTheSame( String dest ) {
        SaveLoadArray.saveArray( array, dest );
        int[] loadedArray = SaveLoadArray.loadArray( dest );

        assertArrayEquals( array, loadedArray );
    }
}

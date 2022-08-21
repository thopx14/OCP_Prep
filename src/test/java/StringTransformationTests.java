import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import functional.aufgaben.StringTransform;

public class StringTransformationTests {

    private static StringTransform transformator;

    @BeforeAll
    public static void init() {
        transformator = new StringTransform();
        transformator
                .addTransformation(s -> s.toUpperCase())
                .addTransformation(s -> s + "!");
    }

    @ParameterizedTest
    @ValueSource(strings= {"Hallo", "Java ist toll"})
    public void testTransformation(String s) {
        String result = transformator.process(s);      
        String expected = s.toUpperCase() + "!";
        
        assertEquals(expected, result);
    }

}

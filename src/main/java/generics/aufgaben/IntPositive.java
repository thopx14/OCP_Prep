package generics.aufgaben;

import java.io.IOException;
import java.util.function.Supplier;

/*
 * Aufgabe 'Generics - Generische Methoden' -> A2
 */
public class IntPositive {

    private static <T extends Exception> void checkAndThrow(boolean check, Supplier<T> s) throws T {
	if (!check) {
	    throw s.get();
	}
    }

    void setValue(int value) {
	checkAndThrow(value > 0, IllegalArgumentException::new);
    }

    void writeValue(int value) throws IOException {
	checkAndThrow(value > 0, java.io.IOException::new);
    }

} // end of IntPositive

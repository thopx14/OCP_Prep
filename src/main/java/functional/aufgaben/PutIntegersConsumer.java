package functional.aufgaben;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PutIntegersConsumer {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        putIntegers(i -> sb.append(i).append(" "));
        System.out.println(sb); // Zeile A: 1 2 3 4

        List<Integer> list = new ArrayList<>();
        putIntegers(i -> list.add(i));
        System.out.println(list); // Zeile B: [1, 2, 3, 4]

        putIntegers(System.out::print); // Zeile C, optionale Aufgabe: 1234
        // die Zeile C kann entfernt werden
    }

    // void accept(T t);
    static void putIntegers(Consumer<Integer> c) {
        int[] integers = { 1, 2, 3, 4 };
        for (int integer : integers) {
            c.accept(integer);
        }
    }
}

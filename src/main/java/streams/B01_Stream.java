package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class B01_Stream {

    public static void main(String[] args) {

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);

        /*
         * mapToInt()
         */
        int sum = integerList.stream()
                    .mapToInt(i -> i)
                    .sum();

        System.out.println(sum); // 21

        List<String> strings = new ArrayList<>(List.of("Hallo", "Welt", "!!!"));

        /*
         * map()
         */
        strings.stream()
            .map(s -> s + "X")
            .forEach(System.out::println);

        /*
         * iterate()
         */
        String seed = "X";
        Stream.iterate(seed, s -> s + "X")
            .limit(5)
            .forEach(System.out::println);
        /*
         * Ausgabe: 
         * X 
         * XX 
         * XXX 
         * XXXX 
         * XXXXX
         */
        

    } // end of main
    
}// end of B01_Stream

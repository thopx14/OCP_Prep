package streams.aufgaben;

import java.util.stream.Stream;

public class KleineAufgabeMap {

    public static void main(String[] args) {
        
        Stream.of("Tom", "Jerry", "Paul", "John")   // Stream<String>
                .map(String::length)                // Stream<Integer>
                .forEach(System.out::println);      // Ausgabe
        
        long count = "Irend ein Text!!11 Bla Bla Bla".chars()   //IntStream
                                                     .count();  //long
        
        System.out.println(count); // 30
    }
}

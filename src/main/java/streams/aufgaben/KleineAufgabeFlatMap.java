package streams.aufgaben;

import java.util.Arrays;
import java.util.stream.Stream;

public class KleineAufgabeFlatMap {

    public static class ArrayBuilder {
        private char first;
        private int count;
        
        public ArrayBuilder(char first, int count) {
            this.first = first;
            this.count = count;
        }

        public Character[] build() {
            Character[] array = new Character[count];
            
            for (char ch = first; ch<count+first; ch++) {
                array[ch-first] = ch;
            }
            
            return array;
        }
    }
    
    public static void main(String[] args) {
        /*
         * Bitte 
         * - eine Pipeline erzeugen, die als Datenquelle das Array 'array' hat
         * - Die Pipeline soll mit den ArrayBuiders Character-Arrays erzeugen
         * - Character-Arrays sollten 'konkateniert' werden (flatMap)
         * - Bitte alle Zeichen aller Character-Arrays ausgeben mit forEach
         */
        
        ArrayBuilder[] array = {
                new ArrayBuilder('a', 5),   
                new ArrayBuilder('B', 3),   
                new ArrayBuilder('m', 7),   
            };
        
        Arrays.stream(array)                              // Stream<ArrayBuilder>
                .map(ArrayBuilder::build)                 // Stream<Character[]>
                .flatMap(Stream::of)                      // Stream<Character>
                .forEach(System.out::print);              // Ausgabe: abcdeBCDmnopqrs

    } // end of main

} // end of KleineAufgabeFlatMap

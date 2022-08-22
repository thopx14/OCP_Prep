package streams.aufgaben;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamBilden {
    
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(55, 77);

        // A
        List<List<Integer>> list3 = Arrays.asList(list1, list2);
//        for ( List<Integer> e : list3 ) {
//            System.out.println( "size = " + e.size() + ". elements = " + e );
//        }
        /*
         * ###### A1.
         */
        System.out.println("*** A1:");
        list3.stream()
            .forEach( l -> System.out.println("size= " + l.size() + ". elements = " + l) );
        // B

        /*
         * ###### A2.
         */
//        for ( int i = 1; i < 100; i++ ) {
//            System.out.println( new Random().nextInt() );
//        }

        System.out.println("*** A2:");
        Stream.generate( () -> new Random().nextInt() ).limit(100)
            .forEach(System.out::println);

        /*
         * ###### A3.
         */
//        for ( int i = 100; i >= 1; i-- ) {
//            System.out.println( i );
//        }
        System.out.println("*** A3:");
        Stream.iterate(100, i -> --i )
            .limit(100)
            .forEach(System.out::println);

        /*
         * ###### A4.
         */
        String[] a1 = { "a", "b" };
        String[] a2 = { "c", "d" };
        
        // A
//        String[][] a3 = { a1, a2 };
//        for (String[] arr : a3) {
//            for (String s : arr) {
//                System.out.println(s);
//            }
//        }
        
        System.out.println("*** A4:");
        Stream<String> s1 = Arrays.stream(a1);        
        Stream<String> s2 = Arrays.stream(a2);
        
        Stream.concat(s1, s2)
            .forEach(System.out::println);        
        // B
        
        /*
         * ###### A5.
         */
        //A
//        Collection<String> coll = new ArrayList<>();
//        coll.add("/a");
//        coll.add("/a/b");
//        coll.add("/a/b/c");
//        coll.add("/a/b/c/d");
//        for(String s : coll) {
//            System.out.println(s);
//        }
        
        AtomicInteger ai = new AtomicInteger(96); // Ein Zeichen vor: 'a'
        StringBuilder sb = new StringBuilder();
        
        Supplier<String> supplier = () -> {
            sb.append("/" + (char) ai.incrementAndGet());
            return sb.toString();
        };
                            
        System.out.println("*** A5:");
        Stream.generate(supplier)
            .limit(4)
            .forEach(System.out::println);              
        // B

    } // end of main

} // end of StreamBilden

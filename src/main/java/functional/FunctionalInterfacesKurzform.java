package functional;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

/**
 * @see FunctionalInterfacesLangform
 */
public class FunctionalInterfacesKurzform {

    public static void main( String[] args ) {

        Function<Integer, String> f1 = i -> new StringBuilder().append( i ).reverse().toString();
        System.out.println( f1.apply( 120 ) ); // 021

        UnaryOperator<Integer> f2 = i -> i * i;
        System.out.println( f2.apply( 10 ) ); // 100

        BiFunction<String, String, Integer> f3 = ( s1, s2 ) -> s1.length() + s2.length();
        System.out.println( f3.apply( "Hans", "Dampf" ) ); // 9

        BinaryOperator<String> f4 = ( s1, s2 ) -> s1.concat( s2 );
        System.out.println( f4.apply( "Du", "blöde Sau" ) ); // Dublöde Sau

        Consumer<String> f5 = s -> System.out.println( s );
        f5.accept( "Blubb" ); // Blubb

        BiConsumer<Integer, Point> f6 = ( x, y ) -> System.out.println( x + y.toString() );
        f6.accept( 100, new Point( 20, 20 ) ); // 100java.awt.Point[x=20,y=20]

        Supplier<List<Integer>> f7 = () -> {
            List<Integer> l = new ArrayList<>();
            for ( int j = 0; j < 5; j++ ) {
                l.add( new Random().nextInt( j + 1 ) );
            }

            return l;
        };

        List<Integer> intList = f7.get();
        System.out.println( intList ); // [0, 0, 2, 1, 2]

        Predicate<String> f8 = x -> x.length() > 5;
        System.out.println( f8.test( "Hallo11!!" ) ); // true

        BiPredicate<String, Integer> f9 = ( x, y ) -> x.length() > 10 && y == 8;
        System.out.println( f9.test( "Haaaallooooooohooooo", 8 ) ); // true

    } // end of main

} // end of FunctionalInterfacesKurzform

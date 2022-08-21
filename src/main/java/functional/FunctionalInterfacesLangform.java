package functional;

import java.util.function.*;

public class FunctionalInterfacesLangform {

    public static void main( String[] args ) {

        /*
         * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/package-summary.html
         */

        /*
         * Functions
         */
        Function<Integer, String> f1 = new Function<Integer, String>() {
            @Override
            public String apply( Integer t ) {
                return "number: " + t;
            }
        };

        System.out.println( f1.apply( 12 ) ); // 'number: 12'

        // UnaryOperator extends Function
        UnaryOperator<Integer> f2 = new UnaryOperator<Integer>() {
            @Override
            public Integer apply( Integer t ) {
                return t * t;
            }
        };

        System.out.println( f2.apply( 100 ) ); // 10000

        BiFunction<String, String, Integer> f3 = new BiFunction<String, String, Integer>() {
            @Override
            public Integer apply( String t, String u ) {
                return t.length() + u.length();
            }
        };

        System.out.println( f3.apply( "Hallo", "Welt" ) ); // 9


        // BinaryOperator extends BiFunction
        BinaryOperator<String> f4 = new BinaryOperator<String>() {
            @Override
            public String apply( String t, String u ) {
                return t.concat( u );
            }
        };

        System.out.println( f4.apply( "Hallo", "Welt" ) ); // HalloWelt

        /*
         * Consumer
         */

        Consumer<String> c1 = new Consumer<String>() {
            @Override
            public void accept( String t ) {
                System.out.println( t );
            }
        };

        c1.accept( "Was hängt am Baum und grüßt? Ein Huhu!!!" ); // Was hängt am Baum und grüßt? Ein Huhu!!!

        BiConsumer<String, String> c2 = new BiConsumer<String, String>() {
            @Override
            public void accept( String t, String u ) {
                System.out.println( new StringBuilder( t ).append( u ).reverse().toString() );
            }
        };

        c2.accept( "Huhu", "Hahn" ); // nhaHuhuH

        /*
         * Supplier
         */
        Supplier<String> s1 = new Supplier<String>() {
            @Override
            public String get() {
                return "Ich bin ein Supplier!";
            }
        };

        System.out.println( s1.get() ); //Ich bin ein Supplier!

        /*
         * Predicate
         */
        Predicate<Integer> p1 = new Predicate<Integer>() {
            @Override
            public boolean test( Integer i ) {
                return i > 100;
            }
        };

        System.out.println( p1.test( 100 ) ); // false

        BiPredicate<Integer, String> p2 = new BiPredicate<Integer, String>() {
            @Override
            public boolean test( Integer t, String u ) {
                return t > 10 && u.length() == 2;
            }
        };

        System.out.println( p2.test( 19, "AB" ) ); // true


    } // end of main

} // end of FunctionalInterfaces 

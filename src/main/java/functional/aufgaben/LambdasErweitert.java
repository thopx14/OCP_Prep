package functional.aufgaben;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LambdasErweitert {

    public static void main(String[] args) {
        
        /*
         * ###### A1.
         */
        class C1 implements Consumer<Integer> {
            @Override
            public void accept(Integer t) {
                System.out.println(t);
            }
        }
        
        /*
         * Alle drei Consumer-Realisierungen sollen dieselbe Ausgabe erzeugen.
            - Initialisieren Sie c2 mithilfe einer anonymen Klasse.
            - Verwenden Sie eine Lambda-Funktion um c3 ausführlich zu initialisieren.
            - Verwenden Sie eine Lambda-Funktion um c4 kompakt zu initialisieren.
         */
    
        Consumer<Integer> c1 = new C1();
        c1.accept(12);
        
        Consumer<Integer> c2 = new Consumer<Integer>() {

            @Override
            public void accept(Integer t) {
                System.out.println(t);                
            }
        };
        c2.accept(12);  
        
        Consumer<Integer> c3 = (Integer i) -> System.out.println(i);
        c3.accept(12);

        Consumer<Integer> c4 = System.out::println;
        c4.accept(12);
        
        // end of ###### A1.
        
        /*
         * ###### A2.
         */
        
        class S1 implements Supplier<String> {
            @Override
            public String get() {
                return "Montag";
            }
        }
        
        /*
         * - Initialisieren Sie s2 mithilfe einer anonymen Klasse.
           - Verwenden Sie eine Lambda-Funktion um s3 ausführlich zu initialisieren.
           - Verwenden Sie eine Lambda-Funktion um s4 kompakt zu initialisieren.
         */
        
        Supplier<String> s1 = new S1();
        System.out.println("Heute ist " + s1.get());
        
        Supplier<String> s2 = new Supplier<String>() {        
            @Override
            public String get() {
                return "Montag";
            }            
        };
        
        System.out.println("Heute ist " + s2.get());
        
        Supplier<String> s3 = () -> { return "Montag"; };
        System.out.println("Heute ist " + s3.get());
        
        Supplier<String> s4 = () -> "Montag";
        System.out.println("Heute ist " + s4.get());
        // end of ###### A2. 
        
        /*
         * ###### A3.
         */
        
        
        class F1 implements BiFunction<String, String, Integer> {
            @Override
            public Integer apply(String t1, String t2) {
                return t1.length() + t2.length();
            }
        }

        /*
         * - Initialisieren Sie f2 mithilfe einer anonymen Klasse.
           - Verwenden Sie eine Lambda-Funktion um f3 ausführlich zu initialisieren.
           - Verwenden Sie eine Lambda-Funktion um f4 kompakt zu initialisieren.
         */
        BiFunction<String, String, Integer> f1 = new F1();
        System.out.println( f1.apply("ab", "cde") );
        
        BiFunction<String, String, Integer> f2 = new BiFunction<String, String, Integer>() {

            @Override
            public Integer apply(String t1, String t2) {
                return t1.length() + t2.length();
            }
        };
        
        System.out.println( f2.apply("ab", "cde") );
        
        BiFunction<String, String, Integer> f3 = 
                (String t1, String t2) -> { return t1.length() + t2.length(); };
                
        System.out.println( f3.apply("ab", "cde") );

        BiFunction<String, String, Integer> f4 = (t1, t2) -> t1.length() + t2.length();
        System.out.println( f4.apply("ab", "cde") );
        // end of ###### A3.
        
        /*
         * ###### A4.
         */
        
//        /*Typ hier*/ ref = x -> y -> x;
        
        // end of ###### A4.        
    } // end of main

} // end of LambdasErweitert

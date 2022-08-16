package generics.aufgaben;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;
import java.util.function.Supplier;

public class GenerischeMethoden {

    /*
     * A1
     */
    static <T> T getRandom(T p1, T p2) {
        if (new Random().nextBoolean()) {
            return p1;
        }

        return p2;
    }

    /*
     * A3
     */
    public static <T extends Number> Double sum(Collection<T> zahlen) {
        Double sum = 0.0;

        for (T t : zahlen) {
            sum += t.doubleValue();
        }

        return sum;
    }

    /*
     * A4
     */
    @SafeVarargs
    public static <B, T extends Collection<B>> T build(Supplier<T> s, B... values) {

        T c = s.get();
        for (B b : values) {
            c.add(b);
        }
        return c;
    }

    // main
    public static void main(String[] args) {

        /*
         * A1:
         */

        // --------------------------------------------------------------------------------------
        String s = getRandom("abc", "def"); // getRandom liefert zufällig entweder "abc" oder "def"
        System.out.println(s);

        Integer i = getRandom(14, 12);
        System.out.println(i); // entweder 14 oder 12

        Date d = getRandom(new Date(), new Date(0)); // java.util.Date
        System.out.println(d);

        /*
         * Gemeinsame Schnittstelle hier ist: java.io.Serializable
         */
        Object obj = getRandom("hallo", 22);
        System.out.println(obj); // entweder hallo oder 22

//	String erg = getRandom("hallo", 22); // hier soll ein Compilerfehler entstehen
        // --------------------------------------------------------------------------------------

        /*
         * A3
         */
        // --------------------------------------------------------------------------------------
        Collection<Integer> iColl = new ArrayList<>(Arrays.asList(1, 2, 3));
        double sum = sum(iColl);
        System.out.println(sum);

        Collection<Double> dColl = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0));

        sum = sum(dColl);
        System.out.println(sum);

        Collection<Double> dColl2 = new ArrayList<>(Arrays.asList(1.0, 2.5, 3.0));

        sum = sum(dColl2);
        System.out.println(sum);

        Collection<Number> nColl = new ArrayList<>(Arrays.asList(1.0, 2.5, 3.0f));
        sum = sum(nColl);
        System.out.println(sum);
        // --------------------------------------------------------------------------------------

        /*
         * A4
         */
        // --------------------------------------------------------------------------------------
        Collection<Integer> c1 = build(ArrayList::new, 1, 2, 3, 4, 4);
        System.out.println(c1); // [1, 2, 3, 4, 4]

        Collection<String> c2 = build(ArrayList::new, "Hallo", "Welt", "Bla", "Blubb");
        System.out.println(c2); // [Hallo, Welt, Bla, Blubb]

        Collection<String> c3 = build(HashSet::new, "Hallo", "Welt", "Bla", "Blubb", "Bla");
        System.out.println(c3); // [Hallo, Bla, Welt, Blubb]

        Collection<String> c4 = build(TreeSet::new, "Hallo", "Welt", "Bla", "Blubb", "Bla");
        System.out.println(c4); // [Bla, Blubb, Hallo, Welt]
        // --------------------------------------------------------------------------------------

    } // end of main

}// end of GenerischeMethoden

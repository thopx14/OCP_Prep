package classdesign.aufgaben;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


interface Transformable<T> {
    T transform( String[] arr ); // T wird als Rückgabewerte definiert.
}

public class Interfaces_String_Transform {

    public static void main( String[] args ) {

        /*
         * A1:
         */
        String[] arr = { "mo", "di", "mi" };

        ArrayList<String> list = transform( arr );

        System.out.println( list ); // [MO, DI, MI]

        /*
         * A2:
         */
        list = null;

        Transformable<ArrayList<String>> t = strings -> {
            ArrayList<String> al = new ArrayList<>();
            for ( String string : strings ) {
                al.add( string.toUpperCase() );
            }
            return al;
        };

        list = transform( arr, t );
        System.out.println( list ); // [MO, DI, MI]

        list = null;

        Transformable<ArrayList<String>> t2 = strings -> {
            ArrayList<String> al = new ArrayList<>();
            for ( String string : strings ) {
                al.add( new StringBuilder( string ).reverse().toString() );
            }
            return al;
        };

        list = transform( arr, t2 );
        System.out.println( list ); // [om, id, im]

        /*
         * A3:
         */
        list = null;

        Transformable<ArrayList<String>> t3 = strings -> {
            ArrayList<String> al = new ArrayList<>();
            for ( String string : strings ) {
                al.add( string + "." );
            }
            return al;
        };

        list = transform( arr, t3 );
        System.out.println( list ); // [mo., di., mi.]

        list = null;

        Transformable<ArrayList<String>> t4 = strings -> {
            ArrayList<String> al = new ArrayList<>();
            for ( String string : strings ) {
                al.add( string + "(" + string.length() + ")" );
            }
            return al;
        };

        list = transform( arr, t4 );
        System.out.println( list ); // [mo(2), di(2), mi(2)]

        list = null;

        StringBuilder sb = new StringBuilder();
        transform( arr, t2, s -> sb.append( s ) );
        System.out.println( sb ); // [om, id, im]

        List<String> l = new ArrayList<>();
        l.add( "Mo" );
        l.add( "Die" );
        l.add( "Mi" );
        l.add( "Do" );
        l.add( "Fr" );
        l.add( "Sa" );
        l.add( "So" );

        List<String> l2 = new ArrayList<>();

        transform( l, s -> s.startsWith( "M" ), s -> l2.add( s.toUpperCase() ) );

        System.out.println( l2 );


        List<String> l3 = transform2( l, s -> s.toUpperCase() );
        System.out.println( l3 );

        l3 = transform2( l, s -> new StringBuilder( s ).reverse().toString() );
        System.out.println( l3 );
    }

    /*
     * A1:
     */
    static ArrayList<String> transform( String[] strs ) {
        ArrayList<String> tmp = new ArrayList<>();
        for ( String string : strs ) {
            tmp.add( string.toUpperCase() );
        }
        return tmp;
    }

    /*
     * A2:
     */
    static <T> T transform( String[] strs, Transformable<T> t ) {
        return t.transform( strs );
    }

    /*
     * A5:
     */
    static <T> void transform( String[] strs, Transformable<T> t, Consumer<T> consumer ) {
        consumer.accept( t.transform( strs ) );
    }

    /*
     * Own method:
     */
    static <T> void transform( List<T> l, Predicate<T> p, Consumer<T> c ) {
        for ( T t : l ) {
            if ( p.test( t ) )
                c.accept( t );
        }
    }

    static <T> List<T> transform2( List<T> l, Function<T, T> f ) {
        List<T> tList = new ArrayList<>();
        for ( T t : l ) {
            tList.add( f.apply( t ) );
        }
        return tList;
    }
}

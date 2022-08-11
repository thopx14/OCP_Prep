package collectionapi.aufgaben.set;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MyLittleTreeSet {

    public static SortedSet<Integer> randomTreeSet( int fromIncl, int toExcl, int howMayToSave,
                                                    Comparator<Integer> comp ) {
        SortedSet<Integer> set = new TreeSet<>( comp );

        for ( int i = 0; i < howMayToSave; i++ ) {
            int randomNr = ( int ) ( fromIncl + Math.round( Math.random() * ( toExcl - fromIncl ) ) );

            /*
             Nur einzigartige Elemente hinzufügen.
             */
            while ( !set.add( randomNr ) ) { // 'true' if the element is not in the list, 'false' otherwise!
                randomNr = ( int ) ( fromIncl + Math.round( Math.random() * ( toExcl - fromIncl ) ) );
            }

        }
        return set;
    }

    public static <T> SortedSet<T> getSubSetOf( SortedSet<T> set, T fromIncl, T toExcl, Comparator<T> cmp ) {
        if ( fromIncl == null || toExcl == null ) {
            throw new IllegalArgumentException( "fromIncl and/or toExcl cannot be null!" );
        }

        if ( cmp.equals( Comparator.reverseOrder() ) ) {
            /* Die Reihenfolge muss hier vertauscht werden, bei 'Comparator.reverseOrder()' */
            return set.subSet( toExcl, fromIncl );
        }

        return set.subSet( fromIncl, toExcl );
    }

    public static void main( String[] args ) {
        SortedSet<Integer> myRandomSet = randomTreeSet( 0, 1001, 100, Comparator.reverseOrder() );
        System.out.println( myRandomSet );

        System.out.println();

        SortedSet<Integer> mySubset = getSubSetOf( myRandomSet, 799, 900, Comparator.reverseOrder() );
        System.out.println( mySubset );

        mySubset.removeIf( i -> i > 800 );

        System.out.println( mySubset );     // Leere Liste
        System.out.println( myRandomSet );  // Liste wurde nicht angetastet!
    }
}

package collectionapi.aufgaben;

import java.util.SortedSet;
import java.util.TreeSet;

public class MyLittleTreeSet {

    public static SortedSet<Integer> randomTreeSet( int fromIncl, int toExcl, int howMayToSave ) {
        SortedSet<Integer> set = new TreeSet<>();

        for ( int i = 0; i < howMayToSave; i++ ) {
            int randomNr = ( int ) ( fromIncl + Math.round( Math.random() * ( toExcl - fromIncl ) ) );

            /*
             Add only unique numbers. To have at least 100 elements in the list then.
             */
            while ( ! set.add( randomNr ) ) { // 'true' if the element is not in the list, 'false' otherwise!
                randomNr = ( int ) ( fromIncl + Math.round( Math.random() * ( toExcl - fromIncl ) ) );
            }

        }
        return set;
    }

    public static <T> SortedSet<T> getSubSetOf( SortedSet<T> set, T fromIncl, T toExcl ) {
        if ( fromIncl == null || toExcl == null ) {
            throw new IllegalArgumentException( "fromIncl and/or toExcl cannot be null!" );
        }

        return set.subSet( fromIncl, toExcl );
    }

    public static void main( String[] args ) {
        SortedSet<Integer> myRandomSet = randomTreeSet( 0, 1001, 100 );
        System.out.println( myRandomSet );
        
        System.out.println();

        SortedSet<Integer> mySubset = getSubSetOf( myRandomSet, 800, 901 );
        System.out.println( mySubset );
    }
}

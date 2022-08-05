package collectionapi;

import java.util.ArrayList;
import java.util.Collection;

public class Tests {

    public static void main( String[] args ) {

        Collection<Integer> myColl = new ArrayList<>();

        myColl.add( 12 );
        myColl.add( 17 );
        myColl.add( 22 );

        Integer[] arr = myColl.toArray( new Integer[myColl.size()] );

        for ( Integer integer : arr ) {
            System.out.println( integer );
			/*
			 * 	12
				17
				22
			 */
        }

        System.out.println();

        Object[] arr2 = myColl.toArray();

        for ( Object object : arr2 ) {
            System.out.println( object.getClass() );
			/*
			 * 	class java.lang.Integer
				class java.lang.Integer
				class java.lang.Integer
			 */
        }

    }

}

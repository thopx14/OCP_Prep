package exceptions.aufgaben;

import java.util.Arrays;

public class Exceptions_ArrayListPositive {

    public static void main( String[] args ) {
        ArrayListPositive positivList = new ArrayListPositive();
//		positivList.addAll(Arrays.asList(1, 2, null, 3)); // NullArgumentException: No 'null' is allowed!
//		positivList.addAll(null); // NullArgumentException: No 'null' is allowed!
        positivList.addAll( Arrays.asList( 1, 2, 3, 4 ) ); // Okay!
        positivList.add( 1 ); // Okay!
//		positivList.add(-1); // NotPostivieArgumentException: Given Integer must be positive!
//		positivList.add(0, 0); // NotPostivieArgumentException: Given Integer must be positive!
//		positivList.addAll(Arrays.asList(1,2,0,0)); // NotPostivieArgumentException: Given Integer must be positive!
//		positivList.addAll(Arrays.asList(1,2,3,-10)); // NotPostivieArgumentException: Given Integer must be positive!

        // Assertions:

//		positivList.add(null); // java.lang.AssertionError
//		positivList.add(0); // java.lang.AssertionError
//		positivList.add(0, -1); // java.lang.AssertionError
//		positivList.add(0, 0); // java.lang.AssertionError
//		positivList.add(0, -1); // java.lang.AssertionError
//		positivList.addAll(Arrays.asList(1,2,0)); // java.lang.AssertionError
//		positivList.addAll(Arrays.asList(1,2,-1)); // java.lang.AssertionError

        positivList.add( 1 );
        positivList.add( 100 );
        positivList.add( 1, 200 );
        positivList.addAll( Arrays.asList( 1, 2, 3, 4 ) );
        System.out.println( positivList );

    }

}

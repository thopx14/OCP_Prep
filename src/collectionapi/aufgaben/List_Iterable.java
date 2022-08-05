package collectionapi.aufgaben;

import java.util.Iterator;

class MyList<T> implements Iterable<T> {

    Object[] arr;
    private int cnt;
    private static final int MAX_CONTAINER_SIZE = 10;

    MyList() {
        arr = new Object[MAX_CONTAINER_SIZE];
        cnt = 0;
    }


    /*
     * This constructor is there to test the behavior of unsafe varargs as mentioned
     * here: https://docs.oracle.com/javase/7/docs/api/java/lang/SafeVarargs.html
     *
     * Evil 😈
     */
    //	MyList(List<String>... el) {
    //		Object[] objArray = el;
    //		objArray[0] = Arrays.asList(42);
    //		String data = el[0].get(0);
    //		System.out.println(data); // ClassCastException
    //	}


    @SafeVarargs
    MyList( T... elements ) {
        this();

        if ( elements.length >= MAX_CONTAINER_SIZE ) {
            throw new IndexOutOfBoundsException( "Cannot add more than: " + MAX_CONTAINER_SIZE + " elements!" );
        }
        for ( T t : elements ) {
            add( t );
        }
    }

    private boolean checkBounds() {
        return cnt >= MAX_CONTAINER_SIZE;
    }


    void add( T element ) throws IndexOutOfBoundsException {
        if ( checkBounds() )
            throw new IndexOutOfBoundsException( "Cannot add more than: " + MAX_CONTAINER_SIZE + " elements!" );

        arr[cnt++] = element;
    }

    @SuppressWarnings("unchecked")
    void add( T... el ) throws IndexOutOfBoundsException {
        if ( ! checkBounds() )
            throw new IndexOutOfBoundsException( "Cannot add more than: " + MAX_CONTAINER_SIZE + " elements!" );

        for ( T t : el ) {
            add( t );
        }
    }

    int size() {
        return cnt;
    }

    @SuppressWarnings("unchecked")
    T get( int idx ) {
        if ( idx >= 0 && idx < MAX_CONTAINER_SIZE )
            return ( T ) arr[idx];

        throw new IndexOutOfBoundsException( "Index " + idx + " is out of bounds for size: " + MAX_CONTAINER_SIZE );
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int internCnt = 0;

            @Override
            public boolean hasNext() {
                return internCnt < MAX_CONTAINER_SIZE;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                return ( T ) arr[internCnt++];
            }
        };
    }
}

public class List_Iterable {

    public static void main( String[] args ) {

        MyList<String> myListString = new MyList<>( "Hallo", "1", "2", "3", "Welt" );
        myListString.add( "Blubb" );
        myListString.add( "Klaus" );
        myListString.add( "Haus" );
        myListString.add( "Bärbel" );
        myListString.add( "Hans" );
        //		myListString.add("asdasdasd"); // IndexOutOfBoundsException: Cannot add more than: 10 elements

        System.out.println( myListString.size() ); // 10
        System.out.println( myListString.get( 0 ) ); // Hallo
        //		System.out.println(myListString.get(10)); // IndexOutOfBoundsException
        //		System.out.println(myListString.get(-1)); // IndexOutOfBoundsException

        System.out.println();
        /*
         * 1.
         */
        for ( String string : myListString ) {
            System.out.println( string );
        }

        System.out.println();

        /*
         * 2.
         */
        Iterator<String> iter = myListString.iterator();
        while ( iter.hasNext() ) {
            System.out.println( iter.next() );
        }

        System.out.println();

        /*
         * 3.
         */
        iter.forEachRemaining( System.out::println );
    }
}

package collectionapi.aufgaben.list;

import java.util.Iterator;

public class MyList<T> implements Iterable<T> {

    private final Object[] arr;
    private int cnt;
    private static final int MAX_CONTAINER_SIZE = 10;

    public MyList() {
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
    public MyList( T... elements ) {
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


    public void add( T element ) throws IndexOutOfBoundsException {
        if ( checkBounds() )
            throw new IndexOutOfBoundsException( "Cannot add more than: " + MAX_CONTAINER_SIZE + " elements!" );

        arr[cnt++] = element;
    }

    @SuppressWarnings("unchecked")
    public void add( T... el ) throws IndexOutOfBoundsException {
        if ( checkBounds() )
            throw new IndexOutOfBoundsException( "Cannot add more than: " + MAX_CONTAINER_SIZE + " elements!" );

        for ( T t : el ) {
            add( t );
        }
    }

    public int size() {
        return cnt;
    }

    @SuppressWarnings("unchecked")
    public T get( int idx ) {
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
                return internCnt < MAX_CONTAINER_SIZE && arr[internCnt] != null;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                return ( T ) arr[internCnt++];
            }
        };
    }
}

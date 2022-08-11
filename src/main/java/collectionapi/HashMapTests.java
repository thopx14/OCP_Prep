package collectionapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Book {
    int price;

    public Book( int price ) {
        this.price = price;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        Book book = ( Book ) o;
        return price == book.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash( price );
    }
}

public class HashMapTests {

    public static void main( String[] args ) {

        Map<Book, Integer> books = new HashMap<>();
        books.put( new Book( 5 ), 10 );

        System.out.println( books.get( new Book( 5 ) ) ); // 10
        System.out.println( books.size() ); // 1
    }
}

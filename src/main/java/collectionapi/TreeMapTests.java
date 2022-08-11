package collectionapi;

import java.util.Map;
import java.util.TreeMap;

class Car {
    int speed;

    public Car( int speed ) {
        this.speed = speed;
    }
}

public class TreeMapTests {

    public static void main( String[] args ) {

        Map<Car, Integer> tMap = new TreeMap<>();

        tMap.put( new Car( 12 ), 1 );
        tMap.put( new Car( 100 ), 2 );
        tMap.put( new Car( 50 ), 3 );

        System.out.println( tMap.get( new Car( 22 ) ) ); // 1
    }
}

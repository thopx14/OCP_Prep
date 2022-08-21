package functional;

import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;

class Car {
    String model;
    int speed;

    public Car( String model, int speed ) {
        this.model = model;
        this.speed = speed;
    }
}

public class Comparatoren {

    public static void main( String[] args ) {

        Comparator<Car> cmp1 = new Comparator<Car>() {
            @Override
            public int compare( Car o1, Car o2 ) {
                return o1.speed - o2.speed;
            }
        };

        Car c1 = new Car( "Mercedes Benz AMG", 500 );
        Car c2 = new Car( "VW Polo", 180 );

        System.out.println( cmp1.compare( c1, c2 ) ); // positiv, c1 > c2!
        System.out.println( cmp1.compare( c2, c1 ) ); // negativ, c1 > c2!

        Function<Car, Integer> keyExtractor = car -> car.speed;
        Comparator<Car> cmp2 = Comparator.comparing( keyExtractor );

        int result = cmp2.compare( c1, c2 );
        System.out.println( result ); // positiv!

        /*
        public static <T, U extends Comparable<? super U>> Comparator<T>
            comparing(Function<? super T, ? extends U> keyExtractor
         */
        Comparator<Car> cmp3 = Comparator.comparing( car -> car.speed * -1 ); // reverseOrder!
        result = cmp3.compare( c1, c2 );
        System.out.println( result ); // negativ!

        Comparator<Car> cmp4 = Comparator.comparing( car -> car.model ); //lexikografisch
        result = cmp3.compare( c1, c2 );
        System.out.println( result ); // negativ

        class Person implements Comparable<Person> {
            String prename;
            String surname;

            public Person( String prename, String surname ) {
                this.prename = prename;
                this.surname = surname;
            }

            @Override
            public int compareTo( Person o ) {
                return String.CASE_INSENSITIVE_ORDER.compare( this.surname, o.surname );
            }

        } // end of Person

        Comparator<Person> cmpPerson1 = Comparator.naturalOrder();
        Comparator<Person> cmpPerson2 = Comparator.reverseOrder();
        Comparator<Person> cmpPerson3 = Collections.reverseOrder();

        Person p1 = new Person( "Hans", "Hugo" );
        Person p2 = new Person( "Klaus", "Kleber" );

        System.out.println( "cmpPerson1: " + cmpPerson1.compare( p1, p2 ) ); // negativ
        System.out.println( "cmpPerson2: " + cmpPerson2.compare( p1, p2 ) ); // positiv
        System.out.println( "cmpPerson3: " + cmpPerson3.compare( p1, p2 ) ); // positiv

        Comparator<Person> cmpSurname = Comparator.comparing( p -> p.surname );
        Comparator<Person> cmpPrename = Comparator.comparing( p -> p.prename );

        Comparator<Person> cmpCompared = cmpSurname.thenComparing( cmpPrename );

        result = cmpCompared.compare( p1, p2 );
        System.out.println( "cmpCompared: " + result ); // negativ


    } // end of main

} // end of Comparatoren

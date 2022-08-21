package functional;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

public class UebungenMitList {

    public static void main( String[] args ) {

        Person p1 = new Person( "Franz", "Zimmer", LocalDate.of( 1977, Month.JANUARY, 1 ) );
        Person p2 = new Person( "Klaus", "Kleber", LocalDate.of( 1956, Month.APRIL, 18 ) );
        Person p3 = new Person( "Alisa", "Müller", LocalDate.of( 1999, Month.MARCH, 22 ) );
        Person p4 = new Person( "Ulrike", "Weber", LocalDate.of( 1937, Month.AUGUST, 6 ) );

        List<Person> personList = new ArrayList<>();
        personList.addAll( List.of( p1, p2, p3, p4 ) );
        personList.forEach( System.out::println );

        System.out.println();
        personList.sort( Comparator.comparing( p -> p.getSurname() ) );
        personList.forEach( System.out::println );

        System.out.println();
        personList.sort( Comparator.comparing( p -> p.getDateOfBirth() ) );
        personList.forEach( System.out::println );

        System.out.println();
        /*
            Ersetzt alle Personen in der Liste mit "Max Mustermann"
         */
        personList.replaceAll( p -> new Person( "Max", "Mustermann", LocalDate.now() ) );
        personList.forEach( System.out::println );


        /*
        Übungen mit BiFunction:
         */
        //        doStuffWithBiFunction();
    }

    static void doStuffWithBiFunction() {
        BiFunction<String, String, Person> createNewPerson = new BiFunction<String, String, Person>() {
            @Override
            public Person apply( String s, String s2 ) {
                return new Person( s, s2 );
            }
        };

        Person person = createNewPerson.apply( "Hugo", "Strauss" );
        System.out.println( person );

        BiFunction<String, String, Person> createNewPerson2 = ( s1, s2 ) -> new Person( s1, s2 );
        Person person1 = createNewPerson2.apply( "Jannik", "Flannik" );
        System.out.println( person1 );

        BiFunction<String, String, Person> createNewPerson3 = Person::new;
        Person person2 = createNewPerson3.apply( "Ulrike", "Mike" );
        System.out.println( person2 );

        BiFunction<String, String, Person> createNewPerson4 = Person::of;
        Person person3 = createNewPerson4.apply( "Klaus", "Maus" );
        System.out.println( person3 );
    }
}

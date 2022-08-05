package inner.aufgaben;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class Person {
    private String prename;
    private String surname;
    private LocalDate yearOfBirth;

    public Person( String prename, String surname, LocalDate yearOfBirth ) {
        this.prename = prename;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
    }

    /*
     * Getter & Setter
     */
    public String getPrename() {
        return prename;
    }

    public void setPrename( String prename ) {
        this.prename = prename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname( String surname ) {
        this.surname = surname;
    }

    public LocalDate getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth( LocalDate yearOfBirth ) {
        this.yearOfBirth = yearOfBirth;
    }
    // end of 'Getter & Setter'

    @Override
    public String toString() {
        return prename + " " + surname + ", " + yearOfBirth.format( DateTimeFormatter.ofLocalizedDate( FormatStyle.FULL ) );
    }

} // end of Person

class PersonFilter implements Predicate<Person> {

    @Override
    public boolean test( Person p ) {
        return p.getYearOfBirth().isAfter( LocalDate.of( 1950, 1, 1 ) );
    }

} // end of PersonFilter

public class Nested_Predicate_Personen {

    private static class PersonFilter2 implements Predicate<Person> {

        @Override
        public boolean test( Person p ) {
            return p.getSurname().contains( "a" );
        }

    } // end of PersonFilter2

    public static void main( String[] args ) {

        Person p1 = new Person( "Steve", "Jobs", LocalDate.of( 1955, 2, 24 ) );
        Person p2 = new Person( "Bill", "Gates", LocalDate.of( 1955, 10, 28 ) );
        Person p3 = new Person( "Sylvester", "Stallone", LocalDate.of( 1946, 7, 6 ) );
        Person p4 = new Person( "Sebastian", "Vettel", LocalDate.of( 1987, 7, 3 ) );

        Person[] personen = { p1, p2, p3, p4 };

        List<Person> personenListe = filtern( personen, new PersonFilter() );

        for ( Person person : personenListe ) {
            System.out.println( person );
			/*
			 * Ausgabe:
			 	Steve Jobs, Donnerstag, 24. Februar 1955
				Bill Gates, Freitag, 28. Oktober 1955
				Sebastian Vettel, Freitag, 3. Juli 1987
			 */
        }

        System.out.println( "-----------------------------------------------" );
        personenListe = filtern( personen, new PersonFilter2() );

        for ( Person person : personenListe ) {
            System.out.println( person );
			
			/*
			 * Ausgabe:
			 	Bill Gates, Freitag, 28. Oktober 1955
				Sylvester Stallone, Samstag, 6. Juli 1946
			 */
        }

        System.out.println( "-----------------------------------------------" );

        class PersonFilter3 implements Predicate<Person> {

            @Override
            public boolean test( Person p ) {
                return p.getSurname().length() >= 4;
            }

        } // end of PersonFilter3

        personenListe = filtern( personen, new PersonFilter3() );

        for ( Person person : personenListe ) {
            System.out.println( person );
			
			/*
			 * Ausgabe:
			 	Steve Jobs, Donnerstag, 24. Februar 1955
				Bill Gates, Freitag, 28. Oktober 1955
				Sylvester Stallone, Samstag, 6. Juli 1946
				Sebastian Vettel, Freitag, 3. Juli 1987
			 */
        }

        Predicate<Person> personFilter4 = new Predicate<Person>() {

            @Override
            public boolean test( Person p ) {
                return new PersonFilter2().test( p ) && new PersonFilter3().test( p );
            }

        };

        System.out.println( "-----------------------------------------------" );

        personenListe = filtern( personen, personFilter4 );

        for ( Person person : personenListe ) {
            System.out.println( person );
			
			/*
			 * Ausgabe:
			 	Bill Gates, Freitag, 28. Oktober 1955
				Sylvester Stallone, Samstag, 6. Juli 1946
			 */
        }


        System.out.println( "-----------------------------------------------" );

        personenListe = filtern( personen, new PersonFilter2().and( new PersonFilter3() ) );

        for ( Person person : personenListe ) {
            System.out.println( person );
			
			/*
			 * Ausgabe:
			 	Bill Gates, Freitag, 28. Oktober 1955
				Sylvester Stallone, Samstag, 6. Juli 1946
			 */
        }


        System.out.println( "-----------------------------------------------" );

        Person p5 = new Person( "Max", "Mustermann", LocalDate.of( 1944, 7, 3 ) ); // Schaltjahr!

        personen = null;
        personen = new Person[]{ p1, p2, p3, p4, p5 };

        personenListe = filtern( personen, p -> p.getYearOfBirth().isLeapYear() );

        for ( Person person : personenListe ) {
            System.out.println( person );
			
			/*
			 * Ausgabe:
				Max Mustermann, Montag, 3. Juli 1944
			 */
        }

    } // end of main

    static List<Person> filtern( Person[] personen, Predicate<Person> p ) {
        List<Person> personList = new ArrayList<>();

        for ( Person person : personen ) {
            if ( p.test( person ) ) {
                personList.add( person );
            }
        }

        return personList;
    }

} // end of Nested_Predicate_Personen

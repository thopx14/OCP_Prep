package functional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Person {

    private String prename;
    private String surname;
    private LocalDate dateOfBirth;

    // Constructor
    public Person( String prename, String surname, LocalDate dateOfBirth ) {
        this.prename = prename;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public Person( String prename, String surname ) {
        this.prename = prename;
        this.surname = surname;
        this.dateOfBirth = LocalDate.now();
    }
    // end of Constructor

    public static Person of( String prename, String surname ) {
        return new Person( prename, surname, LocalDate.now() );
    }

    // Getter & Setter
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth( LocalDate dateOfBirth ) {
        this.dateOfBirth = dateOfBirth;
    }
    // end Getter & Setter

    // Overriden
    @Override
    public String toString() {
        return "Person: " +
                "prename='" + prename + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth.format( DateTimeFormatter.ofLocalizedDate( FormatStyle.SHORT ) );
    }
    // end of Overriden

} // end of Person

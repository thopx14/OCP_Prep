package jdbc;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode
public class User {

    private @Getter @Setter int id;
    private @Getter @Setter String prename;
    private @Getter @Setter String surname;
    private @Getter @Setter byte[] password;
    private @Getter @Setter LocalDate dayOfBirth;
    private @Getter @Setter String streetName;
    private @Getter @Setter int houseNumber;
    private @Getter @Setter int postalCode;
    private @Getter @Setter String city;

    public User( String prename,
                 String surname,
                 byte[] password,
                 LocalDate dayOfBirth,
                 String streetName,
                 int houseNumber,
                 int postalCode,
                 String city ) {

        this.prename = prename;
        this.surname = surname;
        this.password = password;
        this.dayOfBirth = dayOfBirth;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

}

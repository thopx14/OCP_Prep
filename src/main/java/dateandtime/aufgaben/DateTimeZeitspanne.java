package dateandtime.aufgaben;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

public class DateTimeZeitspanne {
    public static void main( String[] args ) {
        // A1:
        ZonedDateTime depart = ZonedDateTime.of( 2022, 9, 4,
                12, 0, 0, 0, ZoneId.of( "UTC+1" ) );

        ZonedDateTime arrive = ZonedDateTime.of( 2022, 9, 4,
                16, 0, 0, 0, ZoneId.of( "UTC+2" ) );

        /*
        Departure: 12 Uhr UTC+1     --> 11 Uhr GMT
        Arrival: 16 Uhr UTC+2       --> 14 Uhr GMT
                                    ----------------
        Duration:                       3 h
         */
        long result = ChronoUnit.HOURS.between( depart, arrive );
        System.out.println( result ); // 3

        //A2:
        // Start: 08 Uhr (UTC+2) -> Dauer: 3h -> Ankunft 10 Uhr (UTC+1)
        ZonedDateTime depart2 = ZonedDateTime.of(
                LocalDateTime.of( 2022, 9, 4, 8, 0 ),
                ZoneId.of( "UTC+2" ) );

        // Sonntag, 4. September 2022 um 08:00:00 UTC+02:00
        System.out.println( localizeDate( depart2 ) );

        ZonedDateTime arrival2 = depart2.withZoneSameInstant( ZoneId.of( "UTC+1" ) );
        arrival2 = arrival2.plus( Duration.ofHours( 3 ) );

        // Sonntag, 4. September 2022 um 10:00:00 UTC+01:00
        System.out.println( localizeDate( arrival2 ) );

        // A3:
        ZonedDateTime berlin = ZonedDateTime.of(
                LocalDateTime.of( 2018, 3, 25, 1, 0 ),
                ZoneId.of( "Europe/Berlin" )
        );
        // Sonntag, 25. März 2018 um 01:00:00 Mitteleuropäische !!Normalzeit!!
        System.out.println( localizeDate( berlin ) );

        ZonedDateTime berlin2 = ZonedDateTime.of(
                LocalDateTime.of( 2018, 3, 25, 4, 0 ),
                ZoneId.of( "Europe/Berlin" )
        );
        // Sonntag, 25. März 2018 um 04:00:00 Mitteleuropäische !!Sommerzeit!!
        System.out.println( localizeDate( berlin2 ) );

        Duration between = Duration.between( berlin, berlin2 );
        long berlinByNight = between.toHours(); // 2: Umstellung auf Sommerzeit!
        System.out.println( berlinByNight );

    }

    static String localizeDate( ZonedDateTime dt ) {
        return dt.format( DateTimeFormatter.ofLocalizedDateTime( FormatStyle.FULL ) );
    }
}

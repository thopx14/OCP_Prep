package functional;

import java.util.Optional;

public class UebungenMitOptional {

    public static void main( String[] args ) {

        Optional<Integer> optional = Optional.of( 122 );
        System.out.println( optional.get() ); //122

        optional = Optional.empty();

        if ( optional.isPresent() )
            System.out.println( optional.get() );

        optional.ifPresent( System.out::println ); // Keine Ausgabe!

        optional = Optional.of( 133 );
        optional.ifPresent( System.out::println ); // 133
    }
}

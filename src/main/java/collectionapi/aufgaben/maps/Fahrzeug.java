package collectionapi.aufgaben.maps;

import java.util.Objects;

public class Fahrzeug {
    private final String modell;
    private final String hersteller;

    public Fahrzeug( String modell, String hersteller ) {
        this.modell = modell;
        this.hersteller = hersteller;
    }

    @Override
    public String toString() {
        return hersteller + ", " + modell;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        Fahrzeug fahrzeug = ( Fahrzeug ) o;
        return Objects.equals( modell, fahrzeug.modell ) && Objects.equals( hersteller, fahrzeug.hersteller );
    }

    @Override
    public int hashCode() {
        return Objects.hash( modell, hersteller );
    }
}

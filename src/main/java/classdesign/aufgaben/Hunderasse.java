package classdesign.aufgaben;

public enum Hunderasse {
    DACKEL( 0.5 ), COLLIE( 1.0 ), DOGGE( 1.5 );

    private final double maxGroesse;

    /* private */ Hunderasse( double maxGroesse ) {
        this.maxGroesse = maxGroesse;
    }

    @Override
    public String toString() {
        return this.name() + ", " + "max. Größe: " + this.maxGroesse;
    }

}

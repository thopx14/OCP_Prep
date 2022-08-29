package streams.aufgaben.warenkorb;

import java.util.HashMap;
import java.util.Map;

public class Produkt {

    private final String name;
    private final int preis;

    private static class Preise {
        private static final Map<String, Integer> preise = new HashMap<>();

        static {
            preise.put( "Brot", 129 );
            preise.put( "Wurst", 230 );
            preise.put( "Milch", 99 );
            preise.put( "Unbekannt", 44 );
        }

        static int getPreis( String name ) {
            Integer preis = preise.get( name );
            if ( preis == null )
                return preise.get( "Unbekannt" );

            return preise.get( name );
        }
    }

    public Produkt( String name, int anzahl ) {
        this.name = name;
        this.preis = Preise.getPreis( name ) * anzahl;
    }

    public int getPreis() {
        return preis;
    }

    public String getName() {
        return name;
    }
}

package streams.aufgaben.warenkorb;

import java.util.ArrayList;
import java.util.List;

public class Bestellung {

    private final List<Produkt> produkte;

    public Bestellung( Produkt... p ) {
        this.produkte = new ArrayList<>( List.of( p ) );
    }

    public List<Produkt> getProdukte() {
        return new ArrayList<>( produkte );
    }
}

package streams.aufgaben.warenkorb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Warenkorb {

    private final List<Bestellung> warenkorb;

    public Warenkorb() {
        this.warenkorb = new ArrayList<>();
    }

    public void addProdukt( Bestellung p ) {
        this.warenkorb.add( p );
    }

    public List<Bestellung> getWarenkorb() {
        return new ArrayList<>( this.warenkorb ); // Make a copy here!
    }

    public List<Produkt> getAlleProdukte() {
        return this.warenkorb.stream()
                .map( Bestellung::getProdukte )
                .flatMap( Collection::stream )
                .collect( Collectors.toList() );
    }

    public int getGesamtPreis() {
        return this.warenkorb.stream()
                .map( Bestellung::getProdukte )
                .flatMap( Collection::stream )
                .mapToInt( p -> p.getPreis() * p.getAnzahl() )
                .sum();
    }
}

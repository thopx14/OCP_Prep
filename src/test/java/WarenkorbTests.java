import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import streams.aufgaben.warenkorb.Bestellung;
import streams.aufgaben.warenkorb.Produkt;
import streams.aufgaben.warenkorb.Warenkorb;

import java.util.List;

public class WarenkorbTests {

    private static final Warenkorb warenkorb = new Warenkorb();

    @BeforeEach
    void init() {
        warenkorb.addProdukt( new Bestellung( new Produkt( "Brot", 3 ) ) );
        warenkorb.addProdukt( new Bestellung( new Produkt( "Wurst", 1 ) ) );
        warenkorb.addProdukt( new Bestellung( new Produkt( "Milch", 2 ) ) );

    }

    @Test
    void testGetGesamtPreisOfWarenkorb() {
        int expected = 0;
        for ( Bestellung bestellung : warenkorb.getWarenkorb() ) {
            for ( Produkt produkt : bestellung.getProdukte() ) {
                expected += produkt.getPreis();
            }
        }

        int result = warenkorb.getGesamtPreis();

        Assertions.assertEquals( expected, result );
    }

    @Test
    void testGetGesamtPreisOfWarenkorbMitUnbekanntemProdukt() {
        warenkorb.addProdukt( new Bestellung( new Produkt( "Käse", 2 ) ) );

        int expected = 0;
        for ( Bestellung bestellung : warenkorb.getWarenkorb() ) {
            for ( Produkt produkt : bestellung.getProdukte() ) {
                expected += produkt.getPreis();
            }
        }

        int result = warenkorb.getGesamtPreis();

        Assertions.assertEquals( expected, result );
    }

    @Test
    void testGetAlleProdukte() {
        List<Produkt> expected = warenkorb.getAlleProdukte();
        List<Produkt> result = warenkorb.getAlleProdukte();

        Assertions.assertIterableEquals( expected, result );
    }
}

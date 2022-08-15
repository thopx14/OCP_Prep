package generics.aufgaben;

import java.util.Collection;

interface KannBehandeltWerden {
    void setGesund( boolean b );

    boolean isGesund();
}

class Mensch implements KannBehandeltWerden {

    @Override
    public void setGesund( boolean b ) {}

    @Override
    public boolean isGesund() {
        return false;
    }
}
/*
Passen Sie die Klasse 'Arzt' so an, dass sich mit ihr folgende Aufgaben lösen lassen:
*/

//Es ist möglich, einen Arzt zu erzeugen, der nur Affen behandeln kann (aber keine anderen Tiere)
class ArztV1<T extends Affe> extends Mensch { }

//Es ist möglich, einen Arzt zu erzeugen, der beliebige Tiere behandeln kann (aber keine Menschen)
class ArztV2<T extends Tier> extends Mensch { }

//Es ist möglich, einen Arzt zu erzeugen, der sowohl Tiere als auch Menschen behandeln kann
class ArztV3<T extends KannBehandeltWerden> extends Mensch { }

abstract class Tier implements KannBehandeltWerden {
}

class Zebra extends Tier {

    @Override
    public void setGesund( boolean b ) {}

    @Override
    public boolean isGesund() {
        return false;
    }
}

class Affe extends Tier {

    @Override
    public void setGesund( boolean b ) { }

    @Override
    public boolean isGesund() {
        return false;
    }
}

class Zoo<T extends Tier> {
    
    Collection<T> tiereImZoo;

    void addTier( T t ) {
        tiereImZoo.add( t );
    }
    
    Collection<T> getAlleTiere() {
        return tiereImZoo;
    }

}

public class Generics_Zoo_Arzt {

    public static void main( String[] args ) {

        Tier t1 = new Affe();
        Tier t2 = new Zebra();
        
        Affe affe = new Affe();
        Zebra zebra = new Zebra();
        
        Zoo<Tier> zoo = new Zoo<>();
        
        zoo.addTier( t1 );
        zoo.addTier( t2 );
        zoo.addTier( affe );
        zoo.addTier( zebra );
        
        KannBehandeltWerden kb1 = new Affe();
//        zoo.addTier( kb1 ); // cf!
        
        ArztV1<Affe> a1 = new ArztV1<>();
//        ArztV1<Tier> a2 = new ArztV1<>(); // cf!
        
        ArztV2<Affe> a3 = new ArztV2<>();
        ArztV2<Zebra> a4 = new ArztV2<>();
        ArztV2<Tier> a5 = new ArztV2<>();
//        ArztV2<Mensch> a5 = new ArztV2<>(); // cf!
        
        ArztV3<Tier> a6 = new ArztV3<>();
        ArztV3<Affe> a7 = new ArztV3<>();
        ArztV3<Zebra> a8 = new ArztV3<>();
        ArztV3<KannBehandeltWerden> a9 = new ArztV3<>();
             
    }

}

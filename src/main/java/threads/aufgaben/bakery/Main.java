package threads.aufgaben.bakery;

public class Main {
    public static void main( String[] args ) {
        Bakery bakery1 = new Bakery( "Stimpfle" );

        new Person( "Hansi", bakery1 );
        new Person( "Klausi", bakery1 );
        new Person( "Franz", bakery1 );
        new Person( "Manfred", bakery1 );
    }

}

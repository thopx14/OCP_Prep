package collectionapi.aufgaben;

public class Deque_Mirror {
    public static void main( String[] args ) {
        Mirror m = new Mirror();

        for ( char ch = 'a'; ch < 'g'; ch++ ) {
            m.add( ch );
            System.out.println( m );
            /*
            Ausgabe:
            ---------
            a|a
            ba|ab
            cba|abc
            dcba|abcd
            edcba|abcde
            fedcba|abcdef
             */
        }

        System.out.println();
        while ( ! m.isEmpty() ) {
            System.out.println( m );
            m.remove();
            /*
            Ausgabe:
            	fedcba|abcdef
                edcba|abcde
                dcba|abcd
                cba|abc
                ba|ab
                a|a
             */
        }
    }
}

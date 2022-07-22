package ClassDesign.Aufgaben;

import java.util.Arrays;
import java.util.Comparator;

public class OS implements Comparable<OS> {

    private String name;
    private int version;

    public OS( String name, int version ) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion( int version ) {
        this.version = version;
    }

    public static void printOs( OS[] osArray ) {
        for ( int i = 0; i < osArray.length; i++ ) {
            System.out.printf( "|%2d.| %-10s|%2d|%n", i + 1, osArray[i].getName(), osArray[i].getVersion() );
        }
    }

    public static void shuffle( OS[] osArray ) {
        for ( int i = 0; i < osArray.length; i++ ) {
            int randomIdx = ( int ) ( Math.random() * osArray.length );
            OS temp = osArray[i];
            osArray[i] = osArray[randomIdx];
            osArray[randomIdx] = temp;
        }
    }

    @Override
    public int compareTo( OS o ) {
        int cmp = this.name.compareTo( o.getName() );
        if ( cmp == 0 ) {
            cmp = this.version - o.version;
        }
        return cmp;
    }

    static class OSComparator implements Comparator<OS> {

        @Override
        public int compare( OS o1, OS o2 ) {
            int cmp = o1.getName().compareTo( o2.getName() );
            if ( cmp == 0 ) {
                cmp = o1.getVersion() - o2.getVersion();
            }
            return cmp;
        }

        @Override
        public Comparator<OS> reversed() {
            return Comparator.super.reversed();
        }

    }


    public static void main( String[] args ) {
        OS[] osses = {
                new OS( "Linux", 3 ),
                new OS( "Windows", 95 ),
                new OS( "MAC", 9 ),
                new OS( "Linux", 1 )
        };

        OS.printOs( osses );
        System.out.println( "Sorting Array in ascending order:" );
        Arrays.sort( osses, new OSComparator() );
        System.out.println( "----------------" );
        System.out.println( "Sorted ClassDesign.Aufgaben.OS:" );
        System.out.println( "----------------" );
        OS.printOs( osses );

        int key = Arrays.binarySearch( osses, new OS( "Linux", 1 ) );
        System.out.println();
        if ( key >= 0 ) {
            System.out.println( "Found \"Linux 1\" at position: " + key );
        }

        System.out.println();

        Arrays.sort( osses, new OSComparator().reversed() );
        System.out.println( "----------------" );
        System.out.println( "Reversed sorted ClassDesign.Aufgaben.OS:" );
        System.out.println( "----------------" );
        OS.printOs( osses );
        int key2 = Arrays.binarySearch( osses, new OS( "Windows", 95 ), new OS.OSComparator().reversed() );
        if ( key2 >= 0 ) {
            System.out.println( "Found \"Windows 95 \" at position: " + key2 );
        }

        System.out.println();
        System.out.println( "Shuffled ClassDesign.Aufgaben.OS:" );
        OS.shuffle( osses );
        OS.printOs( osses );
    }
}

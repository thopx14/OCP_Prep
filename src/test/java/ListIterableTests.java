import collectionapi.aufgaben.list.MyList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class ListIterableTests {

    private static MyList<String> myListString;
    private static MyList<Integer> myListInteger;

    @Test
    void addingMoreThan10ElementsStatic() {
        myListString = new MyList<>();

        Executable exc = () -> {
            myListString.add( "Hans" );
            myListString.add( "Hans" );
            myListString.add( "Hans" );
            myListString.add( "Hans" );
            myListString.add( "Hans" );
            myListString.add( "Hans" );
            myListString.add( "Hans" );
            myListString.add( "Hans" );
            myListString.add( "Hans" );
            myListString.add( "Hans" );
            myListString.add( "Hans" );
        };
        assertThrows( IndexOutOfBoundsException.class, exc );
    }

    @Test
    void addingMoreThan10ElementsVarargs() {
        myListString = new MyList<>();

        Executable exc = () -> myListString.add( "Hans", "Hans", "Hans", "Hans", "Hans", "Hans", "Hans", "Hans",
                "Hans", "Hans", "Hans" );

        Exception ex = assertThrows( IndexOutOfBoundsException.class, exc );

        assertInstanceOf( IndexOutOfBoundsException.class, ex );
    }

    @Test
    void size0Test() {
        myListInteger = new MyList<>();

        assertEquals( 0, myListInteger.size() );
    }

    @Test
    void size5Test() {
        myListInteger = new MyList<>();
        myListInteger.add( 1 );
        myListInteger.add( 1 );
        myListInteger.add( 1 );
        myListInteger.add( 1 );
        myListInteger.add( 1 );

        assertEquals( 5, myListInteger.size() );
    }


    @Test
    void size10Test() {
        myListInteger = new MyList<>();
        myListInteger.add( 1 );
        myListInteger.add( 1 );
        myListInteger.add( 1 );
        myListInteger.add( 1 );
        myListInteger.add( 1 );
        myListInteger.add( 1 );
        myListInteger.add( 1 );
        myListInteger.add( 1 );
        myListInteger.add( 1 );
        myListInteger.add( 1 );

        assertEquals( 10, myListInteger.size() );
    }


    @Test
    void iterate3TimesOverList() {
        myListString = new MyList<>();
        myListString.add( "Hallo" );
        myListString.add( "Welt" );
        myListString.add( "Duda!" );

        int cnt = 0;
        for ( String s : myListString ) {
            cnt++;
        }
        assertEquals( 3, cnt );
    }

    @Test
    void get3ValuesOutOfListAndCompare() {
        myListString = new MyList<>();
        myListString.add( "Hallo" );
        myListString.add( "Welt" );
        myListString.add( "Duda!" );

        assertEquals( "Hallo", myListString.get( 0 ) );
        assertEquals( "Welt", myListString.get( 1 ) );
        assertEquals( "Duda!", myListString.get( 2 ) );
    }
}

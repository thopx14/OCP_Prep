package generics;

import java.util.ArrayList;
import java.util.Collection;

class C1 {
}

class C2 extends C1 {
}

public class Tests {

    public static void main( String[] args ) {

        Collection<C1> myColl = new ArrayList<>();

        myColl.add( new C1() );
        myColl.add( new C2() );
//		myColl.add(new Integer(12)); // cf!

        myColl.remove( new C1() );
        myColl.remove( new C2() );

    }

}

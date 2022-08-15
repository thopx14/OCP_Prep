package generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.text.diff.StringsComparator;

public class TypeErasing {

    static class Person implements Comparable<Person> {
        
        String prename;
        String surname;
        
        public Person( String prename, String surname ) {
            super();
            this.prename = prename;
            this.surname = surname;
        }

        /*
         * Nach 'typ erasing' steht hier:
         * public int compareTo(Object obj) {
         *      Person o = (Person) obj;
         *      ...
         * }
         */
        @Override
        public int compareTo( Person o ) {          
            return 0;
        }

        public String getPrename() {
            return prename;
        }

        public void setPrename( String prename ) {
            this.prename = prename;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname( String surname ) {
            this.surname = surname;
        }     
        
    }
    
    public static void main( String[] args ) {
        
        List<String> list1 = new ArrayList<>();
        list1.add( "Hallo" );
        list1.add( "Welt" );
        
        System.out.println( list1.getClass() ); // class java.util.ArrayList -> ohne Typ!
        
       Collections.sort( list1, Comparator.reverseOrder() );
       
       System.out.println( list1 );

    }

}

package generics.aufgaben;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class Generics_Generische_Methoden_Functional {

    static <T> int count(Collection<T> c, Predicate<T> p) {
	int sum = 0;
	
	for(T t : c) {
	    if(p.test(t)) {
		sum++;
	    }
	}
	
	return sum;
    }
    
    
    public static void main(String[] args) {
	
	List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
	Predicate<Integer> isEven = i -> i % 2 == 0;
	System.out.println( count(list1, isEven) ); // 2  

	Predicate<Number> isNotNull = b -> b.intValue() % 2 != 0;
	List<Number> list2 = Arrays.asList(1, 1.2, 3, 3.4);
	System.out.println( count(list2, isNotNull) ); // 4  
	
	Predicate<String> isEmpty = String::isEmpty;
	List<String> list3 = Arrays.asList("a", "", "b", "", "c");
	System.out.println( count(list3, isEmpty) ); // 2 

    } // end of main
    
} // end of Generics_Generische_Methoden_Functional

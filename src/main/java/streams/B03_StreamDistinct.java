package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class B03_StreamDistinct {

    public static void main( String[] args ) {

        Stream.of( 1, 2, 4, 4, 5, 5, 6, 6, 8, 1 )
                .distinct()
                .forEach( System.out::println );


        UnaryOperator<Integer> op = i -> ++i;

        List<Integer> generatedList = new ArrayList<>();

        ArrayList<Integer> collect = Stream.iterate( 0, i -> ++i )
                .limit( 11 )
                //  <R> R collect(Supplier<R> supplier,
                //                  BiConsumer<R, ? super T> accumulator,
                //                  BiConsumer<R, R> combiner);
                .collect( ArrayList::new, ArrayList::add, ArrayList::addAll );

        System.out.println( collect );

    }

}

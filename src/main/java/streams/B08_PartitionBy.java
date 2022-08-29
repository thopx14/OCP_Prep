package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class B08_PartitionBy {

    public static void main(String[] args) {
        
        /*
         * PartitionBy:
         * public static <T, D, A>
                Collector<T, ?, Map<Boolean, D>> partitioningBy(Predicate<? super T> predicate,
                                                 Collector<? super T, A, D> downstream) {
         */
        
       
        List<Integer> ints = Arrays.asList(1, 3, 4, 6, 77, -11, 88, -22, 101);
        
        Predicate<Integer> predicate = x -> x > 0;
        Collector<Integer, ?, List<Integer>> downstream = Collectors.toList();
        
        Collector<Integer, ?, Map<Boolean, List<Integer>>> collector =
                Collectors.partitioningBy(predicate, downstream);
        
        Map<Boolean, List<Integer>> group1 = ints.stream()
                                                .collect(collector);
        
        group1.forEach((k, v) -> System.out.println(k + ": " + group1.get(k)));

    }

}

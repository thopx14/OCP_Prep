package functional.aufgaben;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class StringTransform {

    // This list is there for collecting the function calls given to 'addTransformation(..)'. 
    private List<UnaryOperator<String>> functionList = new ArrayList<>();

    /**
     * This function adds a new transformation function as {@link UnaryOperator}
     * and adds this to the functionList for transformation purposes. 
     * 
     * @param func - adds a new transformation
     * @return this
     */
    public StringTransform addTransformation(UnaryOperator<String> func) {
        functionList.add(func);
        return this;
    }

    /**
     * processes the 's' and returning the result as String.
     * 
     * @param s
     * @return the result transformed with {@link UnaryOperator} from {@link addTransformation}
     */
    public String process(String s) {

        /*
         * Take the string s as in- and output parameter (in-place).
         */
        for (Function<String, String> function : functionList) {
            s = function.apply(s);
        }

        return s;
    }
}

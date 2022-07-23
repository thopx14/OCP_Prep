package classdesign.aufgaben;

import java.util.ArrayList;
import java.util.function.Consumer;


interface Transformable<T> {
	 T transform(String[] arr); // T wird als Rückgabewerte definiert.
}

public class Interfaces_String_Transform {

	public static void main(String[] args) {

		/*
		 * A1:
		 */
		String[] arr = { "mo", "di", "mi" };
				
		ArrayList<String> list = transform(arr);
		
		System.out.println(list); // [MO, DI, MI]
		
		/*
		 * A2:
		 */
		list = null;
		
		Transformable<ArrayList<String>> t = strings -> {
			ArrayList<String> al = new ArrayList<>();
			for (String string : strings) {
				al.add(string.toUpperCase());
			}
			return al;
		};
		
		list = transform(arr, t);
		System.out.println(list); // [MO, DI, MI]
		
		list = null;
		
		Transformable<ArrayList<String>> t2 = strings -> {
			ArrayList<String> al = new ArrayList<>();
			for (String string : strings) {
				al.add(new StringBuilder(string).reverse().toString());
			}
			return al;
		};
		
		list = transform(arr, t2);
		System.out.println(list); // [om, id, im]
		
		/*
		 * A3: 
		 */
		list = null;
		
		Transformable<ArrayList<String>> t3 = strings -> {
			ArrayList<String> al = new ArrayList<>();
			for (String string : strings) {
				al.add(string + ".");
			}
			return al;
		};
		
		list = transform(arr, t3);
		System.out.println(list); // [mo., di., mi.]
		
		list = null;
		
		Transformable<ArrayList<String>> t4 = strings -> {
			ArrayList<String> al = new ArrayList<>();
			for (String string : strings) {
				al.add(string + "(" + string.length() + ")");
			}
			return al;
		};
		
		list = transform(arr, t4);
		System.out.println(list); // [mo(2), di(2), mi(2)]
		
		list = null;
		
		StringBuilder sb = new StringBuilder();
		transform(arr, t2, s -> sb.append(s));
		System.out.println(sb); // [om, id, im]
		
	}
	
	/*
	 * A1:
	 */
	static ArrayList<String> transform(String[] strs) {
		ArrayList<String> tmp = new ArrayList<>();
		for (String string : strs) {
			tmp.add(string.toUpperCase());
		}
		return tmp;
	}
	
	/*
	 * A2:
	 */
	static <T> T transform(String[] strs, Transformable<T> t) {
		return t.transform(strs);
	}
	
	/*
	 * A5:
	 */
	static <T> void transform(String[] strs, Transformable<T> t, Consumer<T> consumer) {
		consumer.accept(t.transform(strs));
	}
}

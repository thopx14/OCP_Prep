package exceptions.aufgaben;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("serial")
class NullArgumentException extends RuntimeException {

	public NullArgumentException(String message) {
		super(message);
	}

}

@SuppressWarnings("serial")
class NotPostivieArgumentException extends RuntimeException {

	public NotPostivieArgumentException(String message) {
		super(message);
	}
}

@SuppressWarnings("serial")
public class ArrayListPositive extends ArrayList<Integer> {

	@Override
	public boolean add(Integer e) throws NullArgumentException {
		assert e != null;
		assert e > 0;
		
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		assert c != null;
		for (Integer integer : c) {
			assert integer > 0;
		}
		
		return super.addAll(c);
	}

	@Override
	public void add(int index, Integer element) {
		assert element != null;
		assert element > 0;
		
		super.add(index, element);
	}

}

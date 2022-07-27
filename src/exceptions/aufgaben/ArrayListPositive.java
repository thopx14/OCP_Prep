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
		if (e == null) {
			throw new NullArgumentException("No \'null\' is allowed!");
			
		} else if(e <= 0) {
			throw new NotPostivieArgumentException("Given Integer must be positive!");
		}
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		for (Integer integer : c) {
			if (integer == null) {
				throw new NullArgumentException("No \'null\' is allowed!");
				
			} else if(integer <= 0) {
				throw new NotPostivieArgumentException("Given Integer must be positive!");
			}
		}
		return super.addAll(c);
	}

	@Override
	public void add(int index, Integer element) {
		if (element == null) {
			throw new NullArgumentException("No \'null\' is allowed!");
			
		} else if(element <= 0) {
			throw new NotPostivieArgumentException("Given Integer must be positive!");
		}
		super.add(index, element);
	}

}

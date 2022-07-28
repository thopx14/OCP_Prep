package exceptions;

class MyInput implements AutoCloseable {

	@Override
	public void close() throws Exception {
		System.out.println("MyInput.close()");
		System.out.println(1 / 0);
	}
	
}

class MyOutput implements AutoCloseable {

	@Override
	public void close() throws Exception {
		System.out.println("MyOutput.close()");
		System.out.println(1 / 0);
	}
	
}

public class TryWithRessources {

	public static void main(String[] args) {
		
		/*
		 * Ausgabe:
		 	MyInput.close()
			MyOutput.close()
			java.lang.ArithmeticException: / by zero
				at exceptions.TryWithRessources.main(TryWithRessources.java:45)
		-->		Suppressed: java.lang.ArithmeticException: / by zero
					at exceptions.MyInput.close(TryWithRessources.java:8)
					at exceptions.TryWithRessources.main(TryWithRessources.java:47)
		--> 	Suppressed: java.lang.ArithmeticException: / by zero
					at exceptions.MyOutput.close(TryWithRessources.java:18)
					at exceptions.TryWithRessources.main(TryWithRessources.java:47)

		 */
		try(MyOutput o = new MyOutput();
				MyInput i = new MyInput()) {
			
			System.out.println(1/0);
			
		} catch(Exception e) {
			System.out.println(e.getMessage()); // / by zero
			for (Throwable t : e.getSuppressed()) {
				System.out.println(t.getMessage()); // / by zero
			}
			/*
			 * Ausgabe:
			 	/ by zero
				/ by zero
				/ by zero
			 */
		}
	}

}

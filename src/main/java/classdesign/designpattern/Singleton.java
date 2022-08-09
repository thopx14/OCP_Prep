package classdesign.designpattern;

public class Singleton {

	private static Singleton INSTANCE;
	private static int instanceCount = 0;
	
	/*
	 * Wenn der Block auskommentiert wird,
	 * dann entstehen mehr als eine Instanz in einer Multithread-Umgebung!
	 */
	static {
		instanceCount++;
		INSTANCE = new Singleton();
	}
	
	public static Singleton getInstance() {
		if(INSTANCE == null) {
			instanceCount++;
			INSTANCE = new Singleton();
		}
		return INSTANCE;
	}
	
	private Singleton() { }
	
	public static int getInstanceCount() {
		return instanceCount;
	}
	
	
	@Override
	public String toString() {
		return "Eine Instanz, juhu!!!";
	}
	
}

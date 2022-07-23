package classdesign.designpattern;

public class Singleton {

	public static final Singleton INSTANCE;
	
	static {
		INSTANCE = new Singleton();
	}
	
	private Singleton() { }
	
	@Override
	public String toString() {
		return "Eine Instanz, juhu!!!";
	}
	
}

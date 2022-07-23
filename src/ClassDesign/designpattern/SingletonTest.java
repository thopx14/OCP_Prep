package classdesign.designpattern;

public class SingletonTest {

	public static void main(String[] args) {
//		Singleton s = new Singleton(); // CF!
		Singleton s = Singleton.INSTANCE;
		System.out.println(s);
	}

}

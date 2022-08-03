package generics.aufgaben;

interface Fahrzeug { }

interface Garage<T extends Fahrzeug> {	
	void reinfahren(T fahrzeug);
}

class PKW implements Fahrzeug { }
class LKW implements Fahrzeug { }


class PKWGarage implements Garage<PKW> {
	
	@Override
	public void reinfahren(PKW fahrzeug) {
		System.out.println("PKW drin!");		
	}
}

class LKWGarage implements Garage<LKW> {

	@Override
	public void reinfahren(LKW fahrzeug) {
		System.out.println("LKW drin!");		
	}	
}


public class Generics_Garage {

	public static void main(String[] args) {
		Garage<LKW> lkwGarage = new LKWGarage();
		Garage<PKW> pkwGarage = new PKWGarage();

//		Garage<PKW> pkwGarage2 = new LKWGarage(); // cf
//		Garage<LKW> lkwGarage2 = new PKWGarage(); // cf
		
		lkwGarage.reinfahren(new LKW());
		pkwGarage.reinfahren(new PKW());	
		
//		pkwGarage.reinfahren(new LKW()); // cf!		
//		lkwGarage.reinfahren(new PKW()); // cf!		
	}

}

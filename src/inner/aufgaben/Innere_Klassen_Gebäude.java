package inner.aufgaben;

import java.util.ArrayList;
import java.util.List;

import inner.aufgaben.Gebaeude.Stockwerk;

class Gebaeude {

	/*
	 * Innere Klassen:
	 */

	// Stockwerk
	public class Stockwerk {

		private ArrayList<Raum> raeume;
		private int stockwerkNummer;

		public Stockwerk(int nr, int anzRaeume) {
			this.stockwerkNummer = nr;
			this.raeume = new ArrayList<>();

			for (int i = 0; i < anzRaeume; i++) {
				this.raeume.add(new Raum(i));
			}
		}

		public List<Raum> getAlleRaeume() {
			return new ArrayList<>(raeume);
		}

		// Raum
		public class Raum {

			private int raumNr;

			public Raum(int nr) {
				this.raumNr = nr;
			}

			@Override
			public String toString() {
				return "Raum " + Stockwerk.this.stockwerkNummer + "." + this.raumNr + " / "
						+ Gebaeude.this.strassenName;
			}

		} // end of Raum

	} // end of Stockwerk

	// Gebaeude
	private ArrayList<Stockwerk> stockwerke;
	private String strassenName;

	public Gebaeude(String strassenName, int anzStockwerke, int anzRaeume) {
		this.strassenName = strassenName;
		this.stockwerke = new ArrayList<>();

		for (int i = 0; i < anzStockwerke; i++) {
			this.stockwerke.add(new Stockwerk(i, anzRaeume));
		}
	}

	public Stockwerk getStockwerk(int nr) {
		if (nr >= this.stockwerke.size()) {
			throw new IllegalArgumentException("Stockwerk " + nr + " nicht gefunden!");
		}
		return this.stockwerke.get(nr);
	}

	public Stockwerk.Raum getRaum(int stockwerkNr, int raumNr) {
		if (stockwerkNr >= this.stockwerke.size()) {
			throw new IllegalArgumentException("Stockwerk " + stockwerkNr + " nicht gefunden!");
		}

		ArrayList<Stockwerk.Raum> raeume = this.stockwerke.get(stockwerkNr).raeume;

		if (raumNr >= raeume.size()) {
			throw new IllegalArgumentException("Raum " + raumNr + " nicht gefunden!");
		}

		return raeume.get(raumNr);
	}

	public List<Stockwerk> getAlleStockwerke() {
		return new ArrayList<>(this.stockwerke); // Return a freh copy!
	}

} // end of Gebaeude

public class Innere_Klassen_Gebäude {

	public static void main(String[] args) {

		int stockwerke = 3;
		int raeume = 10;

		Gebaeude g1 = new Gebaeude("Hauptstr. 45", stockwerke, raeume);
		System.out.println(g1.getRaum(0, 2)); // Raum 0.2 / Hauptstr. 45
//		System.out.println(g1.getRaum(4, 2)); // IllegalArgumentException: Stockwerk 4 nicht gefunden!
		System.out.println(g1.getRaum(1, 3)); // Raum 1.3 / Hauptstr. 45
//		System.out.println(g1.getRaum(3, 11)); // IllegalArgumentException: Raum 11 nicht gefunden!

		System.out.println("\nRäume mit getRaum(..) ausgeben:");

		// Alle Räume ausgeben:
		for (int i = 0; i < stockwerke; i++) {
			for (int j = 0; j < raeume; j++) {
				System.out.println(g1.getRaum(i, j));
			}
		}		

		List<Stockwerk> stockwerkListe = g1.getAlleStockwerke();

		/*
		 * Hier soll getestet werden, ob die Elemente in der Liste die
		 * eigentliche Datenstruktur verändern (Stichwort: Immutable)!
		 */
		
		stockwerkListe.clear();
		System.out.println("Liste leeren!");
		
		System.out.println("\nRäume mit getAlleStockwerke() ausgeben:");

		stockwerkListe = g1.getAlleStockwerke();
		int cnt = 0;

		for (Stockwerk stockwerk : stockwerkListe) {
			List<Stockwerk.Raum> raumListe = stockwerk.getAlleRaeume();

			for (Stockwerk.Raum raum : raumListe) {
				System.out.println(raum); // Räume sind noch da!
				cnt++;
			}
		}
		
		if(cnt > 0) {
			System.out.println("Alle Räume sind noch da! :-)");
		}

	}

} // end of main

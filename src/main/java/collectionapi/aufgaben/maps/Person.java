package collectionapi.aufgaben.maps;

public class Person {
    private final String vorname;
    private final String nachname;

    public Person( String vorname, String nachname ) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    @Override
    public String toString() {
        return vorname + ", " + nachname;
    }
}

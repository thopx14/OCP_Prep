package inner.aufgaben;

class Rennwagen {

    private String name;
    private Fahrer fahrer;
    private Motor motor;


    public Rennwagen( String name ) {
        this.name = name;
        this.fahrer = null;
        this.motor = new Motor( MOTOR_TYPE.TYPE1 );
    }

    public void setFahrer( Fahrer f ) {
        this.fahrer = f;
    }

    public Motor getMotor() {
        return this.motor;
    }

    //Nested classes:
    static class Fahrer {

        String prename;
        String surname;

        public Fahrer( String prename, String surname ) {
            this.prename = prename;
            this.surname = surname;
        }

        @Override
        public String toString() {
            return "Fahrer: " + this.prename + " " + this.surname;
        }
    }


    public enum MOTOR_TYPE {
        TYPE1, TYPE2, TYPE3, E_TYPE;

        @Override
        public String toString() {
            /*
             * First character should be uppercase, the rest should be lowercase!
             */
            String thisName = this.name();
            String out = thisName.charAt( 0 ) + thisName.substring( 1, thisName.length() ).toLowerCase();

            return out;
        }

    } // end of MOTOR_TYPE

    class Motor {

        private MOTOR_TYPE mType;


        public Motor( MOTOR_TYPE mType ) {
            this.mType = mType;
        }

        @Override
        public String toString() {
            return "Motor " + this.mType + " aus dem Rennwagen " + Rennwagen.this.name;
        }

    } // end of Motor


    @Override
    public String toString() {

        return "Rennwagen: " + this.name + " " + this.fahrer.toString();
    }

    // end of nested classes

} // end of Rennwagen

public class Innere_Klassen_Autos {

    public static void main( String[] args ) {

        Rennwagen rw = new Rennwagen( "Mercedes" );

        Rennwagen.Fahrer f = new Rennwagen.Fahrer( "M.", "Schuhmacher" );
        rw.setFahrer( f );

        Rennwagen.Motor m = rw.getMotor();

        System.out.println( rw );        //Zeile A
        System.out.println( m );        //Zeile B
    }

} // end of main

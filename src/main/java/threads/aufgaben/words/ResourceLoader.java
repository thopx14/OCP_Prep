package threads.aufgaben.words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

class ResourceLoader {
    public static void load( String resource, Consumer<String> c ) throws IllegalStateException {
        try {
            InputStream is = ResourceLoader.class.getResourceAsStream( resource );

            ZipInputStream zis = new ZipInputStream( is );
            ZipEntry ze = zis.getNextEntry();

            if ( ze.isDirectory() ) {
                throw new IllegalStateException( "Keine Textdatei in Zipdatei gefunden" );
            }

            try ( BufferedReader in = new BufferedReader( new InputStreamReader( zis ) ) ) {

                String sourceLine = in.readLine(); // Quellenangaben in der 1. Zeile ignorieren
                if ( !sourceLine.startsWith( "Quelle:" ) ) {
                    throw new IllegalStateException( "Falsche Datei: die Textdatei muss in der ersten Zeile die Quellenangabe haben" );
                }

                String line;
                while ( ( line = in.readLine() ) != null ) {
                    c.accept( line );
                }
            }

        } catch ( IOException e ) {
            throw new IllegalStateException( "Die Ressource kann nicht gefunden werden: " + resource, e );
        }

    }
}

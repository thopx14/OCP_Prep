package io.aufgaben;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static void copyTextFile( Path src, Path dest, boolean append ) throws IOException {
        if ( Files.notExists( src ) ) {
            throw new FileNotFoundException( "File " + src + " not found!" );
        }
        /*
        Opens or creates a file for writing, returning a BufferedWriter
        that may be used to write text to the file in an efficient manner.
        The options parameter specifies how the file is created or opened.
        If no options are present then this method works as if the
        CREATE, TRUNCATE_EXISTING, and WRITE options are present.
        In other words, it opens the file for writing, creating the file if it doesn't exist,
        or initially truncating an existing regular-file to a size of 0 if it exists.

        https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/nio/file/Files.html#newBufferedWriter(java.nio.file.Path,java.nio.charset.Charset,java.nio.file.OpenOption...)
         */
        List<StandardOpenOption> openOptionList = new ArrayList<>();
        openOptionList.add( StandardOpenOption.CREATE );
        openOptionList.add( StandardOpenOption.TRUNCATE_EXISTING );
        openOptionList.add( StandardOpenOption.WRITE );
        if ( append ) {
            openOptionList.remove( StandardOpenOption.TRUNCATE_EXISTING );
            openOptionList.add( StandardOpenOption.APPEND );
        }

        try ( BufferedReader reader = Files.newBufferedReader(
                src,
                StandardCharsets.UTF_8 );

                BufferedWriter writer = Files.newBufferedWriter(
                        dest,
                        StandardCharsets.UTF_8,
                        openOptionList.toArray( StandardOpenOption[]::new ) ) ) {

            String line;
            while ( ( line = reader.readLine() ) != null ) {
                writer.write( line );
                writer.newLine();
            }
        }
    }

    public static void main( String[] args ) throws IOException, URISyntaxException {
        URL UrlOftestTxt = FileUtils.class.getResource( "/test.txt" );
        Path testTxtString = Path.of( UrlOftestTxt.toURI() );
        copyTextFile( testTxtString, Path.of( "./testCopy.txt" ), true );
    }
}

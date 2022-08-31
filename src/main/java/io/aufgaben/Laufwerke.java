package io.aufgaben;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class Laufwerke {
    public static void main( String[] args ) throws IOException {
        printRoots();
    }

    public static void printRoots() throws IOException {
        Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
        System.out.printf( "|%5s\t|\t %s|\t %s|%n", "LW", "usable (MB)", "total (MB)" );
        class MyContainer implements Comparable<MyContainer> {
            final long total;
            final long usable;

            MyContainer( long total, long usable ) {
                this.total = total / 1024 / 1024; // MB!
                this.usable = usable / 1024 / 1024; // MB!
            }

            @Override
            public int compareTo( MyContainer o ) {
                return Long.compare( o.usable, usable );
            }
        }
        Map<MyContainer, Path> usableMapping = new TreeMap<>();
        for ( Path dir : dirs ) {
            FileStore store = Files.getFileStore( dir );
            long total = store.getTotalSpace();
            long usable = store.getUsableSpace();
            usableMapping.put( new MyContainer( total, usable ), dir );
        }
        usableMapping.forEach( ( k, v ) ->
                System.out.printf( "|%5s\t|\t %11d|\t %11d|%n", v, k.usable, k.total )
        );
    }
}
